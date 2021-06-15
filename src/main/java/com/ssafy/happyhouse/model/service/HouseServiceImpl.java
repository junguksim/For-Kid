package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.api.MapRestApi;
import com.ssafy.happyhouse.controller.HouseRestController;
import com.ssafy.happyhouse.model.HouseDeal;
import com.ssafy.happyhouse.model.HousePrice;
import com.ssafy.happyhouse.model.HouseInfo;
import com.ssafy.happyhouse.model.HouseList;
import com.ssafy.happyhouse.model.Location;
import com.ssafy.happyhouse.model.dao.HouseDao;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private HouseDao houseDao;

	private Logger logger = LoggerFactory.getLogger(HouseServiceImpl.class);
	
	public List<HouseList> getAllHouseListWithin(Location loc, int radius) throws Exception {
		List<HouseList> allList = houseDao.getAllHouseList(loc.getGuguncode());
		List<HouseList> newList = new ArrayList<HouseList>(); // 특정 거리안에 있는 아파트 목록
		
		logger.info(loc.getGuguncode());
		logger.info(allList.toString());
		for(HouseList info : allList) {
			if(MapRestApi.getDistance(loc.getLat(), loc.getLng(), info.getLat(), info.getLng()) < radius) {
				newList.add(info);
			}
		}
		return newList;
	}

	@Override
	public HouseInfo getHouseInfo(String aptName, String guguncode) {
		return houseDao.getHouseInfo(aptName, guguncode);
	}
	
	@Override
	public List<HouseDeal> getAllHouseDeal(String aptNo) throws Exception {
		return houseDao.getAllHouseDeal(aptNo);
	}

	@Override
	public List<HousePrice> getAllHousePrice(String aptNo) throws Exception {
		return houseDao.getAllHousePrice(aptNo);
	}

	@Override
	public HouseInfo getLatLng(String guguncode, String dong) {
		// TODO Auto-generated method stub
		return houseDao.getLatLng(guguncode, dong);
	}
}
