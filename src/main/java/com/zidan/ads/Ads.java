package com.zidan.ads;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.zidan.category.Category;
import com.zidan.city.City;
import com.zidan.img.Img;
import com.zidan.info.Info;
import com.zidan.user.User;

@Entity
public class Ads implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adsId;
	private String title;
	private String descreption;
	private LocalDateTime time;
	@ManyToOne(cascade =CascadeType.PERSIST )
	@JoinColumn(name = "cityId")
	private City city;
	@ManyToOne()
	@JoinColumn(name = "categoryId")
	private Category category;
	@Embedded
	private Info info;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@OneToMany(mappedBy = "ads",cascade = CascadeType.ALL,orphanRemoval = true , fetch = FetchType.EAGER)
	private List<Img> img = new ArrayList<Img>();
	
	
	@Transient
	private Integer cityId;
	@Transient
	private Integer categoryId;
	@Transient
	private String timeFromNow;
	@Transient 
	private String mainImg = (img.isEmpty())?"/images/nophoto.png" :img.get(0).getImgUrl();

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Ads() {
	}

	public Integer getAdsId() {
		return adsId;
	}

	public void setAdsId(Integer adsId) {
		this.adsId = adsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescreption() {
		return descreption;
	}

	public void setDescreption(String desc) {
		this.descreption = desc;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTimeFromNow() {
		return timeFromNow;
	}

	public void setTimeFromNow(String timeFromNow) {
		this.timeFromNow = timeFromNow;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public List<Img> getImg() {
		return img;
	}

	public void setImg(List<Img> img) {
		this.img = img;
	}

	
}
