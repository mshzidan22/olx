package com.zidan.ads;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zidan.city.City;
@Repository
public interface AdsRepository extends CrudRepository<Ads, Integer> {
 public List<Ads> findTop20ByOrderByTimeDesc();
 public List<Ads> findByUserUserId(Integer userId);
 public List<Ads> findByCityCityId(Integer cityId);
 public List<Ads> findByCityCityName(String cityName);
 public List<Ads> findByCategoryCategoryId(Integer categoryId);
 public List<Ads> findByCategoryCategoryName(String categroyName);
 // may not work  should be change to take cityId
 public List<Ads> findByCityIn(List<City> city);
 
 public List<Ads> findByCategoryCategoryNameAndCityCityName(String category,String city);
 //may not work
 public List<Ads> findByCategoryCategoryNameAndCityIn(String Category,List<City> city);
 //not sure if work
 public List<Ads> findAllByCity(City city);
 public List<Ads> findAllByCityCityId(Integer cityId);
 public List<Ads> findByTitleContainingIgnoreCase(String titleSearch);
 public List<Ads> findAllByTitleContainingIgnoreCaseAndCityCityId(String titleSearch, Integer cityId);
 
	public final static String govQuery = "select * from ads where city_id In (select city_id from city where gov_id = :govId)";
    @Query(value = govQuery,nativeQuery = true)
	public List<Ads> findAdsByGovId(@Param("govId") Integer govId);
    
	public final static String govAndTitleQuery = "select * from ads where city_id In (select city_id from city where gov_id = :govId) and title like %:title%";
    @Query(value = govAndTitleQuery,nativeQuery = true)
	public List<Ads> findAdsByTitleAndGovId(@Param("title") String Title,@Param("govId") Integer govId);
    
    @Query(value="select * from ads where title like %:title% and city_id in(select city_id from city where gov_id = :locId) or city_id = :locId ",nativeQuery=true)
    public Page<Ads> findByTitleAndLoction(@Param("title") String title , @Param("locId") Integer locId , Pageable pageable);
    
    @Query(value="select * from ads where title like %:title%",nativeQuery=true)
    public Page<Ads> findByTitle(@Param("title") String title , Pageable pageable);
    
}
