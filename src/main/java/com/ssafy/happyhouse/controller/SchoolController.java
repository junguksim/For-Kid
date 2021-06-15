package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.StatusMessage;
import com.ssafy.happyhouse.model.Location;
import com.ssafy.happyhouse.model.Result;
import com.ssafy.happyhouse.model.School;
import com.ssafy.happyhouse.model.service.SchoolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("map/school")
@Api(value="SSAFY-SCHOOL")
public class SchoolController {

	@Autowired
	private SchoolService schoolService; 
	
	@ApiOperation(value = "특정 학교의 상세 정보를 가져온다", response=School.class)
	@GetMapping("/school/{schoolCode}")
	public ResponseEntity<Result> searchAptSchool(@PathVariable("schoolCode") String schoolCode,
												  Result result) throws Exception {

		School school = null;
		school = schoolService.getSchoolDetail(schoolCode);
		if(school == null) {
			result.setMsg("해당 학교의 상세 정보를 찾을 수 없습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
		}else {
			result.setData(school);
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
}
