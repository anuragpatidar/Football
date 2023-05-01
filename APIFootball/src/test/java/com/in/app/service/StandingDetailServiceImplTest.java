package com.in.app.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.in.app.model.StandingDetail;
import com.in.app.service.impl.StandingDetailServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StandingDetailServiceImplTest {

	
	@InjectMocks
    StandingDetailServiceImpl standingDetailService;
	
	@Autowired
	RestTemplate rs;
	
	@Test
    public void shouldReturnListOfStandings() {
        List<StandingDetail> expectedStandings = getExpectedStandings();
        String url ="";
        when(rs.getForEntity(url, Object[].class)).thenReturn((ResponseEntity<Object[]>) expectedStandings);
        List<StandingDetail> actualStandings = standingDetailService.getStandingDetail(any(), any(), any());
        Assertions.assertEquals(expectedStandings, actualStandings);
    }

	 private List<StandingDetail> getExpectedStandings() {
	        List<StandingDetail> expectedStandings = new ArrayList<>();
	        expectedStandings.add(new StandingDetail());
	        return expectedStandings;
	    }
	
}
