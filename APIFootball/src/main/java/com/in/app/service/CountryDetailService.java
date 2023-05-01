package com.in.app.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;

import com.in.app.model.CountryDetail;

public interface CountryDetailService {

	Map<String, String> getCountryDetail(String action, String league_id, String aPIkey) throws IOException;

	void generateResponseFromCountryDetail(List<CountryDetail> countryDetails);

//	void generateResponseFromCountryDetail(List<CountryDetail> countryDetails);

}
