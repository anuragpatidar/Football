package com.in.app.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;

import com.in.app.model.CountryDetail;
import com.in.app.model.StandingDetail;
import com.in.app.model.StandingResponse;

public interface StandingDetailService {

	List<StandingDetail> getStandingDetail(String action, String league_id, String aPIkey) throws MalformedURLException, IOException, ProtocolException;

	List<StandingResponse> generateResponseForStanding(List<StandingDetail> standingDetails, Map<String, String> countryDetails);

}
