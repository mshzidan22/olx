package com.zidan.img;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends CrudRepository<Img, Integer> {

	public Img findTopByOrderByImgIdDesc();
	public List<Img> findByAdsAdsId(Integer adsId);
}
