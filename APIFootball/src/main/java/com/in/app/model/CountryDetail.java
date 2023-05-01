package com.in.app.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@ToString
public class CountryDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7213746674190515903L;
	
	public CountryDetail(){
		
	}
	
	@JsonProperty("country_id")
	private String country_id;
	@JsonProperty("country_name")
	private String country_name;
	@JsonProperty("country_logo")
	private String country_logo;

	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getCountry_logo() {
		return country_logo;
	}
	public void setCountry_logo(String country_logo) {
		this.country_logo = country_logo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
