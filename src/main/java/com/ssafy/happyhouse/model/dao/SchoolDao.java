package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.School;

@Mapper
public interface SchoolDao {

	// 특정 위치에 주변에 있는 모든 학교목록 가져오기
	List<School> getAllSchool(String guguncode) throws Exception;
	// 학교에 대한 상세 정보 가져오기
	School getSchoolDetail(String schoolCode) throws Exception;
}
