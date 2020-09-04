package com.zidan.info;

import javax.persistence.Embeddable;

@Embeddable
public class Info {

	
	
	private String brand;
	private String condtion;
	private String Salary;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCondtion() {
		return condtion;
	}
	public void setCondtion(String condtion) {
		this.condtion = condtion;
	}
	public String getSalary() {
		return Salary;
	}
	public void setSalary(String salary) {
		Salary = salary;
	}

	
	
	
}
