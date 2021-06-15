package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.happyhouse.model.HouseDeal;
import com.ssafy.happyhouse.model.HouseInfo;
import com.ssafy.happyhouse.model.HouseList;
import com.ssafy.happyhouse.model.HousePrice;

@Mapper
public interface HouseDao {
	
	// HouseList 정보 목록 얻어오기
	List<HouseList> getAllHouseList(String guguncode) throws Exception;
	// 해당 아파트 정보 얻어오기
	HouseInfo getHouseInfo(String aptName, String guguncode);
	// 해당 아파트의 거래정보 목록 얻어오기
	List<HouseDeal> getAllHouseDeal(String aptNo);
	// 해당 아파트의 타입-평수에 따른 가격 얻어오기
	List<HousePrice> getAllHousePrice(String aptNo);
	// 좌표얻어오기
	HouseInfo getLatLng(String guguncode, String dong);
}
