package com.zidan.img;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImgService {
     @Autowired
	private ImgRepository imgRepository;
	
	
     public Img getLastId() {
    	 
    	return imgRepository.findTopByOrderByImgIdDesc();
     }
     
    public void saveImg(Img img) {
    	imgRepository.save(img);
    }
    

    
    
    public List<Img> getImagesOfAds(Integer adsId){
    	return imgRepository.findByAdsAdsId(adsId);
    	
    }
}
