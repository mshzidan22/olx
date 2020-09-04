package com.zidan.gov;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GovService {
    @Autowired
	private GovRepository govRepository;
	
	public List<Gov> getAllGov(){
		
		List<Gov> gov = new ArrayList<Gov>();
		govRepository.findAll().forEach(gov::add);
		return gov;
	}
	
	
}
