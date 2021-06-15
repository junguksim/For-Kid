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
@ContextConfiguration(classes = EncryptTest.class)
class EncryptTest {

	private static final Logger logger = LoggerFactory.getLogger(EncryptTest.class);
	
	@Test
	public void apiTest() throws Exception {
		String userId = "ssafy";
		String userPwd = "ssafy";
		
		SecurityUtil util = new SecurityUtil();
		
		String salt = util.getSalt();
		logger.info(util.Hashing(userPwd.getBytes(), salt));
		
		String userPwd2 = "ssafy";
		logger.info(util.Hashing(userPwd2.getBytes(), salt));
		logger.info(salt);
	}
}
