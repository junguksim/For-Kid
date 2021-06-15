package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDeal;
import com.ssafy.happyhouse.model.HousePrice;
import com.ssafy.happyhouse.model.HouseInfo;
import com.ssafy.happyhouse.model.HouseList;
import com.ssafy.happyhouse.model.Location;

public interface HouseService {

	List<HouseList> getAllHouseListWithin(Location loc, int radius) throws Exception;
	HouseInfo getHouseInfo(String aptName, String guguncode);
	List<HouseDeal> getAllHouseDeal(String aptNo) throws Exception;
	List<HousePrice> getAllHousePrice(String aptNo) throws Exception;
	HouseInfo getLatLng(String guguncode, String dong);
}
