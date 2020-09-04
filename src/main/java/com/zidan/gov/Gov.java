package com.zidan.gov;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.zidan.city.City;
@Entity
public class Gov implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer govID;
	private String govName;
	@OneToMany(mappedBy = "gov")
	private List<City> city=new ArrayList<City>();

	public Gov() {
	}

	public Integer getGovID() {
		return govID;
	}

	public void setGovID(Integer govID) {
		this.govID = govID;
	}

	public String getGovName() {
		return govName;
	}

	public void setGovName(String govName) {
		this.govName = govName;
	}

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}

}
