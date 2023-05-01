package com.in.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.in.app.model.CountryDetail;
import com.in.app.model.StandingDetail;
import com.in.app.model.StandingResponse;
import com.in.app.service.CountryDetailService;
import com.in.app.service.StandingDetailService;

@WebMvcTest(controllers = FootballController.class)
public class FootballControllerTest {

	@MockBean
	StandingDetailService standingService;

	@MockBean
	private CountryDetailService countryDetailService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void shouldReturnListOfStandings() throws Exception {
		List<StandingDetail> standings = getStandingList();
		when(standingService.getStandingDetail(any(), any(), any())).thenReturn(standings);
		MvcResult mvcResult = mockMvc.perform(get(
				"http://localhost:8080/football/standingdetail/?action=get_standings&league_id=302&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978"))
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnListOfCountryDetail() throws Exception {
		Map<String, String> countries = getCountryDetailList();
		when(countryDetailService.getCountryDetail(any(), any(), any())).thenReturn(countries);
		MvcResult mvcResult = mockMvc.perform(get(
				"http://localhost:8080/football/standingdetail/?action=get_standings&league_id=302&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978"))
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturnListOfStandingResponse() throws Exception {
		List<StandingResponse> standings = getStandingResponseList();
		when(standingService.generateResponseForStanding(any(), any())).thenReturn(standings);
		MvcResult mvcResult = mockMvc.perform(get(
				"http://localhost:8080/football/standingdetail/?action=get_standings&league_id=302&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978"))
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	private List<StandingResponse> getStandingResponseList() {
		List<StandingResponse> standings = new ArrayList<>();
		return standings;
	}

	private Map<String, String> getCountryDetailList() {
		Map<String, String> countryDetails = new HashMap<>();
		return countryDetails;
	}

	private List<StandingDetail> getStandingList() {
		List<StandingDetail> standings = new ArrayList<>();
		StandingDetail s = new StandingDetail();
		standings.add(s);
		return standings;
	}
}
