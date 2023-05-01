package com.in.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.app.model.StandingDetail;
import com.in.app.model.StandingResponse;
import com.in.app.service.CountryDetailService;
import com.in.app.service.StandingDetailService;

@RestController
//@RequestMapping("/football")
public class FootballController {

	Logger log = LoggerFactory.getLogger(FootballController.class);

	@Autowired
	private CountryDetailService countryDetailService;

	@Autowired
	private StandingDetailService standingDetailService;

	@GetMapping("/football/standingdetail/")
	public ResponseEntity<CollectionModel<StandingResponse>> getFootballDetails(
			@RequestParam(required = false) String action, @RequestParam(required = false) String league_id,
			@RequestParam(required = false) String APIkey) {
		log.info("FootballAPIController : Inside getFootballDetails id :" + league_id + "APIKey :" + APIkey + "action "
				+ action);
		try {
			Map<String, String> countryDetails = countryDetailService.getCountryDetail(action, league_id, APIkey);
			List<StandingDetail> standingDetails = standingDetailService.getStandingDetail(action, league_id, APIkey);
			List<StandingResponse> standings = standingDetailService.generateResponseForStanding(standingDetails,
					countryDetails);
			return new ResponseEntity<CollectionModel<StandingResponse>>(addSelfLink(action, league_id, APIkey,standings),
					HttpStatus.OK);
		} catch (Exception e) {
			log.error("FootballAPIController : Inside catch of getFootballDetails " + e.toString());
			return new ResponseEntity<CollectionModel<StandingResponse>>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * adds a new self link by making a fake calle
	 *
	 * @param leagueId    to make call
	 * @param countryName
	 * @param teamName
	 * @param standings   this will be updated
	 * @return returns a collectionModel
	 */
	private CollectionModel<StandingResponse> addSelfLink(String action, String league_id, String APIkey,List<StandingResponse> standings) {
		Link link = linkTo(methodOn(FootballController.class).getFootballDetails(action, league_id, APIkey))
				.withSelfRel();
		return CollectionModel.of(standings, link);
	}
}
