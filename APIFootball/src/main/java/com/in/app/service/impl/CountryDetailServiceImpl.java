package com.in.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.app.exception.CountryNotFoundException;
import com.in.app.exception.NoDataFoundException;
import com.in.app.model.CountryDetail;
import com.in.app.model.StandingResponse;
import com.in.app.service.CountryDetailService;

@Service
public class CountryDetailServiceImpl implements CountryDetailService {

	Logger log = LoggerFactory.getLogger(StandingDetailServiceImpl.class);
	
	@Autowired
	private RestTemplate rs;

	@Value("${football-detail-api-url}")
	private String baseUrl;

	@Override
	public Map<String, String> getCountryDetail(String action, String league_id, String APIkey) {
		try {
			String url = baseUrl + "?action=get_countries"+"&APIkey=" + APIkey;
			log.info("CountryDetailServiceImpl : Inside getCountryDetail and url is : "+ url);
			ObjectMapper o = new ObjectMapper();
			ResponseEntity<Object[]> forEntity = rs.getForEntity(url, Object[].class);
			List<Object> listOfObject = Arrays.asList(forEntity.getBody());
			if(listOfObject.isEmpty()) {
				log.error("CountryDetailServiceImpl : No Country data found for the apiKey : "+ APIkey);
				throw new NoDataFoundException("No Country data found for the apiKey "+ APIkey +" and league id ="+ league_id);
			}  
			Map<String, String> map = new HashMap<>();
			for (Object object : listOfObject) {
				CountryDetail country = o.convertValue(object, CountryDetail.class);
				map.put(country.getCountry_name(), country.getCountry_id());
			}
			return map;
		} catch (Exception e) {
			log.error("CountryDetailServiceImpl : Exception in getCountryDetail "+ e.getMessage());
			throw new CountryNotFoundException(e.getMessage());
		}
	}

	@Override
	public void generateResponseFromCountryDetail(List<CountryDetail> countryDetails) {
		log.info("CountryDetailServiceImpl : inside the generateResponseFromCountryDetail");
		List<StandingResponse> list = new ArrayList<>();
		for (CountryDetail standingResponse : countryDetails) {
			StandingResponse standing = new StandingResponse();
			standing.setCountry_id(standingResponse.getCountry_id());
			list.add(standing);
		}
	}

}
