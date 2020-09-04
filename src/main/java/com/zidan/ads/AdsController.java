package com.zidan.ads;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zidan.category.Category;
import com.zidan.category.CategoryService;
import com.zidan.city.City;
import com.zidan.city.CityService;

import com.zidan.user.User;
import com.zidan.util.MyUtil;
import com.google.gson.Gson;

@Controller
public class AdsController {
	@Autowired
	private AdsService adsService;
	@Autowired
	private CityService cityService;
	@Autowired
	private CategoryService categoryService;


	
	@GetMapping("/")
	public String test () {
		System.out.println(System.getProperty("user.dir"));
		return "index";
	}
	
	@GetMapping("/add")
	public String addingAds(Model model){
		model.addAttribute("category" ,categoryService.getAllcategory());
		model.addAttribute("ads", new Ads());

		
		return "add";
	}
	@ResponseBody
	@GetMapping("/loadCityByGov/{govName}")
	  public String loadCityByGov(@PathVariable("govName") String govName) {
		Gson gson = new Gson();
		List<City> cites=cityService.getCityByGovName(govName);
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		for(City city : cites) {
		map.put(city.getCityId(),city.getCityName());
			
		}

		return gson.toJson(map);
		
	}
	
	
	
	@PostMapping("/add")
	public String adding(@ModelAttribute("ads") Ads ads , HttpSession session ,@RequestParam("imageFile") MultipartFile[] imageFile , @RequestParam("cityId") Integer cityId) {
		Optional<City> city = cityService.getCityById(cityId);
		Optional<Category> category = categoryService.getCategoryById(ads.getCategoryId());
		ads.setCity(city.get());
		ads.setCategory(category.get());
		ads.setTime(LocalDateTime.now());
		ads.setUser((User)session.getAttribute("user"));
		System.out.println("you have uploaded to the ad images"+imageFile.length);
		try {
		
		adsService.addAds(adsService.SaveImage(imageFile ,ads));
		} catch (Exception e) {
            System.out.println("error when uplaod image");
			e.printStackTrace();
		}
		
		
		return "redirect:home";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Ads> ads =adsService.getAllAds();
	  for(Ads ad :ads) {
		  //it's better to make this with javascript
	  ad.setTimeFromNow(MyUtil.getTimeFromNow(ad.getTime()));

	  }
	  
		model.addAttribute("ads", ads);
		return "home";
	}
	
	
	@GetMapping("img")
	public String fun1() {
		return "img";
	}
	
	@GetMapping("/ads/{adsId}")
	public String showAds(Model model , @PathVariable Integer adsId) {
		Optional<Ads> ads=adsService.getAdsById(adsId);
		model.addAttribute("ads", ads.get());
		return "ads";
	}
	
	
	@GetMapping("/search")
	public String searchAds(Model model ,@RequestParam("adsSearch") Optional<String> title ,@RequestParam("loc") Optional<Integer> locId ,Optional<Integer> page ,RedirectAttributes ra) {
       Integer i = null ;
		if(page.isPresent()) {
        	 i = page.get();
        	 i--;
        }
        Pageable pageable = PageRequest.of((i == null)?0:i,5);
        Page<Ads> adsPage = adsService.getAdsByTitleAndLocId(title.orElse("_"), locId.orElse(0), pageable);
        model.addAttribute("ads", adsPage);
        model.addAttribute("adsSearch", title.orElse(""));
        model.addAttribute("loc", locId.orElse(null));
        //ra.addFlashAttribute("ads", adsPage);
		return "searching-page";
	}
	
	
	@GetMapping("/searchPage")
	public String searchPage (Model model , @ModelAttribute("ads") List<Ads> ads) {
		
		return "searching-page";
	}
	
	
	
}
