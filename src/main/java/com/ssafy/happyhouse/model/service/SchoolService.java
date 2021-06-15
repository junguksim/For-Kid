package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Location;
import com.ssafy.happyhouse.model.School;

public interface SchoolService {

	// 특정 위치 주변에 있는 모든 학교목록 가져오기
	public List<School> getAllSchoolWithin(Location loc, int radius) throws Exception;
	// 학교에 대한 상세 정보 가져오기
	public School getSchoolDetail(String schoolCode) throws Exception;
}
