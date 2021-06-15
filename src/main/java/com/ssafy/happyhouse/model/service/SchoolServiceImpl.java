package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.api.MapRestApi;
import com.ssafy.happyhouse.model.Location;
import com.ssafy.happyhouse.model.School;
import com.ssafy.happyhouse.model.dao.SchoolDao;

@Service
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	SchoolDao schoolDao;

	private Logger logger = LoggerFactory.getLogger(HouseServiceImpl.class);

	@Override
	public List<School> getAllSchoolWithin(Location loc, int radius) throws Exception {
		List<School> schoolList = schoolDao.getAllSchool(loc.getGuguncode());
		List<School> newList = new ArrayList<School>();
		
		for(School school : schoolList) {
			double dist = MapRestApi.getDistance(loc.getLat(), loc.getLng(), school.getLat(), school.getLng());
			if(dist < radius) {
				school.setDistance(dist);
				newList.add(school);
			}
		}
		Collections.sort(newList, new Comparator<School>() {
			@Override
			public int compare(School o1, School o2) {
				return Double.compare(o1.getDistance(), o2.getDistance()); // 거리가 가까운순으로 정렬
			}
		});
		return newList;
	}

	@Override
	public School getSchoolDetail(String schoolCode) throws Exception {
		return schoolDao.getSchoolDetail(schoolCode);
	}
}
