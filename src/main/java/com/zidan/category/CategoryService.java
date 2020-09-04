package com.zidan.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidan.city.City;


@Service
public class CategoryService {
    @Autowired
	private CategoryRepository categoryRepository;
	
	
	public List<Category> getAllcategory(){
		
		List<Category> category = new ArrayList<Category>();
		categoryRepository.findAll().forEach(category::add);
		return category;
	}
	
	
	public Optional<Category> getCategoryById(Integer categoryId) {
		
		return categoryRepository.findById(categoryId);
	}
}


