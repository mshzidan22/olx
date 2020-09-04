package com.zidan.ads;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zidan.city.City;
import com.zidan.img.Img;
import com.zidan.img.ImgService;

@Service
public class AdsService {
	@Autowired
	private AdsRepository adsRepository;
	@Autowired
	private ImgService imgService;

	
	public List<Ads> getAllAds(){
		List<Ads> ads = new ArrayList<Ads>();
		adsRepository.findTop20ByOrderByTimeDesc().forEach(ads::add);
		return ads;
	}
	
	// this method may need null exeption
	public Optional<Ads> getAdsById(Integer adsId) {
		return adsRepository.findById(adsId);
		
	}
	
	public List<Ads> getAdsByUserId(Integer userId){
		return adsRepository.findByUserUserId(userId);
		
	}
	
	public List<Ads> getAdsByCategoryName(String categoryName){
		return adsRepository.findByCategoryCategoryName(categoryName);
	}
	
	public List<Ads> getAdsByCity(City city){
		return adsRepository.findAllByCity(city);
	}
	
	public void addAds(Ads ads) {
		adsRepository.save(ads);
		
	}
	
	public void updateAds(Ads ads) {
		adsRepository.save(ads);
	}
	
	public void deleteAds(Ads ads) {
		adsRepository.delete(ads);
		
	}
	public List<Ads> getLatest12Ads() {
	  return adsRepository.findTop20ByOrderByTimeDesc();
	}

	public Ads SaveImage(MultipartFile[] imageFile , Ads ads) throws Exception {
		//the problem is here if not resolved skip it
		Integer lastId = imgService.getLastId().getImgId();
		 System.out.println(lastId);
		 String folder = System.getProperty("user.dir")+"/src/main/resources/static/images/";
		 for(MultipartFile imgFile : imageFile) {
			 lastId++;
		     byte[] bytes = imgFile.getBytes();
		     String ext = FilenameUtils.getExtension(imgFile.getOriginalFilename());
			 Path path = Paths.get(folder+lastId+"."+ext);
			 Files.write(path, bytes);
             ads.getImg().add(new Img(ext,ads));
         
		 }
		return ads;
		
	}
	
  public List<Ads> getAdsByTitle(String titleSearch){
	  return adsRepository.findByTitleContainingIgnoreCase(titleSearch);
  }
  
  public List<Ads> getAdsByTitleAndCity(String titleSearch, Integer cityId){
	  return adsRepository.findAllByTitleContainingIgnoreCaseAndCityCityId(titleSearch, cityId);
  }
  
  public List<Ads> getAdsbyGovid(Integer govId){
	  return adsRepository.findAdsByGovId(govId);
  }
  
  public List<Ads> getAdsByCityId(Integer cityId){
	return  adsRepository.findAllByCityCityId(cityId);
  }
  
  public List<Ads> getAdsByTitleAndGovId(String title , Integer govId){
	  return adsRepository.findAdsByTitleAndGovId(title, govId);
  }
  
  public Page<Ads> getAdsByTitleAndLocId( String title, Integer locId, Pageable pageable){
	  	  if(locId.equals(0)) {
	  		  return adsRepository.findByTitle(title, pageable);
	  	  }
	  	  else return adsRepository.findByTitleAndLoction(title, locId, pageable);
	  	  }
}
