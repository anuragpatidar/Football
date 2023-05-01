package com.in.app.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.in.app.exception.NoDataFoundException;
import com.in.app.model.StandingDetail;
import com.in.app.model.StandingResponse;
import com.in.app.service.StandingDetailService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Anurag Patidar
 *
 */
@Service
public class StandingDetailServiceImpl implements StandingDetailService {

	Logger log = LoggerFactory.getLogger(StandingDetailServiceImpl.class);
	
	@Autowired
	private RestTemplate rs;

	@Value("${football-detail-api-url}")
	private String baseUrl;

	@Override
	public List<StandingDetail> getStandingDetail(String action, String league_id, String APIkey) {
		List<StandingDetail> standingDetails = new ArrayList<>();
		String url = baseUrl + "?action=" + action + "&league_id="+ league_id + "&APIkey=" + APIkey;
		log.info("StandingDetailServiceImpl : Inside getStandingDetail and url is : "+ url);
		ObjectMapper o = new ObjectMapper();
		ResponseEntity<Object[]> forEntity = rs.getForEntity(url, Object[].class);
		List<Object> listOfObject = Arrays.asList(forEntity.getBody());
		convertListOfObjectIntoListOfStandings(league_id, APIkey, standingDetails, o, listOfObject);
		log.info("StandingDetailServiceImpl : Returning the stadingDetails "+ standingDetails);
		return standingDetails;
	}

	private void convertListOfObjectIntoListOfStandings(String league_id, String APIkey, List<StandingDetail> standingDetails, ObjectMapper o,
			List<Object> listOfObject) {
		if(listOfObject.isEmpty()) {
			log.info("StandingDetailServiceImpl : No standing data found for the apiKey "+ APIkey);
			throw new NoDataFoundException("No standing data found for the apiKey "+ APIkey +" and league id ="+ league_id);
		}
		for (Object object : listOfObject) {
			StandingDetail country = o.convertValue(object, StandingDetail.class);
			standingDetails.add(country);
		}
	}

	@Override
	public List<StandingResponse> generateResponseForStanding(List<StandingDetail> standingDetails,
			Map<String, String> countryDetails) {
		log.info("StandingDetailServiceImpl : inside generateResponseForStanding ");
		List<StandingResponse> standingResponses = new ArrayList<>();
		standingDetails.stream().forEach(team -> {
			StandingResponse standing = new StandingResponse();
			standing.setCountry_name(team.getCountry_name());
			
				if (countryDetails.get(team.getCountry_name()) != null) {
					standing.setCountry_id(countryDetails.get(team.getCountry_name()));
				} else {
					standing.setCountry_id(null);
				}
			standing.setLeague_id(team.getLeague_id());
			standing.setLeague_name(team.getLeague_name());
			standing.setOverall_league_position(team.getOverall_league_position());
			standing.setTeam_id(team.getTeam_id());
			standing.setTeam_name(team.getTeam_name());
			standingResponses.add(standing);
		});
		log.info("StandingDetailServiceImpl : returning standings from generateResponseForStanding and size is "+ standingResponses.size());
		return standingResponses;
	}
}
