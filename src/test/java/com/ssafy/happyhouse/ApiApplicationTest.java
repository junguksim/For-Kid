package com.ssafy.happyhouse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssafy.happyhouse.api.AptSearchApi;
import com.ssafy.happyhouse.api.MapRestApi;
import com.ssafy.happyhouse.model.HouseInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ApiApplicationTest.class)
class ApiApplicationTest {

	private static final Logger logger = LoggerFactory.getLogger(ApiApplicationTest.class);
	
	@Test
	public void apiTest() throws Exception {
		//MapRestApi mapApi = new  MapRestApi();
		//System.out.println(mapApi.getSearchPlaceByKeyword("동춘초등학교").toString());
		
		// AptSearchApi searchApi = new AptSearchApi();
	}
}
