package com.zidan.city;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.zidan.gov.Gov;

@Entity
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cityId;
	private String cityName;
	@ManyToOne
	@JoinColumn(name = "govId")
	private Gov gov;

	public City() {
	}
	

	public City(Integer cityId) {
		super();
		this.cityId = cityId;
	}


	public City(Integer cityId, String cityName, Gov gov) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.gov = gov;
	}


	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Gov getGov() {
		return gov;
	}

	public void setGov(Gov gov) {
		this.gov = gov;
	}

}
