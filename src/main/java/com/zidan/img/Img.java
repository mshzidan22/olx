package com.zidan.img;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.zidan.ads.Ads;

@Entity
public class Img implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer imgId;
	private String imgExtention;
    @ManyToOne(fetch = FetchType.LAZY)
    private Ads ads;
	@Transient
	private String imgUrl;
	
	
	public Img() {
		super();
	}
	public Img(String imgExtention, Ads ads) {
		super();
		this.imgExtention = imgExtention;
		this.ads = ads;
	}
	

	public Img(Integer imgId, String imgExtention) {
		super();
		this.imgId = imgId;
		this.imgExtention = imgExtention;
	}
	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
	public String getImgExtention() {
		return imgExtention;
	}
	public void setImgExtention(String imgExtention) {
		this.imgExtention = imgExtention;
	}
	public Ads getAds() {
		return ads;
	}
	public void setAds(Ads ads) {
		this.ads = ads;
	}

	public String getImgUrl() {
		return "~/images/"+this.imgId+"."+this.imgExtention;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
