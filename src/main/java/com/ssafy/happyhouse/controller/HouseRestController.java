package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.StatusMessage;
import com.ssafy.happyhouse.api.CategoryGroupCode;
import com.ssafy.happyhouse.api.MapRestApi;
import com.ssafy.happyhouse.model.HouseDeal;
import com.ssafy.happyhouse.model.HouseInfo;
import com.ssafy.happyhouse.model.HouseList;
import com.ssafy.happyhouse.model.HousePrice;
import com.ssafy.happyhouse.model.Location;
import com.ssafy.happyhouse.model.Result;
import com.ssafy.happyhouse.model.School;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.SchoolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("map/apt")
@Api(value="SSAFY-APT")
public class HouseRestController {

	@Autowired
	private HouseService houseService;
	@Autowired
	private SchoolService schoolService; 
	@Autowired
	private MapRestApi mapApi; // kakao map api 관련 메소드
	
	private Logger logger = LoggerFactory.getLogger(HouseRestController.class);
	
	@ApiOperation(value = "키워드를 기준으로 검색한 주소와 주위 아파트 목록을 반환한다. ", response=HouseList.class)
	@GetMapping("/{keyword}/{lat}/{lng}/{radius}")
	public ResponseEntity<Result> searchApt(@PathVariable("keyword") String keyword, 
											@PathVariable("lat") String lat, 
											@PathVariable("lng") String lng, 
											@PathVariable("radius") int radius, Result result) throws Exception {
		
		Location loc = mapApi.getSearchPlaceByKeyword(keyword, lat, lng, radius);
		if(loc == null) { 
			result.setMsg("키워드에 대한 검색 결과가 없습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		}
		String guguncode = mapApi.getGuguncodeByLat(loc.getLat(), loc.getLng());
		if(guguncode == null) {
			result.setMsg("해당 장소에 대한 구/군 코드를 찾지 못하였습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		}
		else loc.setGuguncode(guguncode);

		// 아파트 목록 중 특정 거리 안에 있는 아파트 목록을 가져온다
		Map<String, Object> map = new HashMap<String, Object>();
		List<HouseList> list = houseService.getAllHouseListWithin(loc, radius);
		if(list.size() == 0) {
			result.setMsg("아파트 검색 결과가 없습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
		}else {
			map.put("aptList", list); // 주변 아파트 정보 목록
			result.setStatus(StatusMessage.OK);
		}
		map.put("origin", loc); // 기준 검색지 정보
		result.setData(map);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 아파트의 기본정보를 반환한다.", response= HouseDeal.class)
	@GetMapping("/title/{guguncode}/{aptName}")
	public ResponseEntity<Result> searchAptTitle(@PathVariable("guguncode") String guguncode, 
												@PathVariable("aptName") String aptName,
												Result result) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		HouseInfo houseInfo = houseService.getHouseInfo(aptName, guguncode);
		List<HousePrice> priceList = houseService.getAllHousePrice(houseInfo.getNo());

		// areaSize(평수)에 따른 매매/전세 가격 정보 		
		Map<String, List<HousePrice>> areaPrice = new HashMap<String, List<HousePrice>>();
		for(HousePrice price : priceList) {
			if(!areaPrice.containsKey(price.getAreaSize())) // 해당 평수에 대한 정보가 없으면 새로 배열 만들어서 저장
				areaPrice.put(price.getAreaSize(), new ArrayList<HousePrice>());
				
			areaPrice.get(price.getAreaSize()).add(price);
		}
		map.put("houseInfo", houseInfo);
		map.put("areaPrice", areaPrice);	
		result.setData(map);
		result.setStatus(StatusMessage.OK);
	
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "좌표를 반환한다.", response= HouseDeal.class)
	@GetMapping("/latlng/{guguncode}/{dong}")
	public ResponseEntity<Result> getLatLng(@PathVariable("guguncode") String guguncode, 
												@PathVariable("dong") String dong, 
												Result result) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		HouseInfo houseInfo = houseService.getLatLng(guguncode, dong);
		map.put("houseInfo", houseInfo);	
		result.setData(map);
		result.setStatus(StatusMessage.OK);

		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 아파트의 거래 정보를 반환한다.", response= HouseDeal.class)
	@GetMapping("/deal/{aptNo}")
	public ResponseEntity<Result> searchAptDeal(@PathVariable("aptNo") String aptNo,
												Result result) throws Exception {
		
		List<HouseDeal> dealList = houseService.getAllHouseDeal(aptNo);
		if(dealList.size()  == 0) {
			result.setMsg("검색 결과가 없습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
		}else {
			List<HouseDeal> saleList = new ArrayList<HouseDeal>();
			List<HouseDeal> rentList = new ArrayList<HouseDeal>();
			for(HouseDeal deal : dealList) {
				if(deal.getType().equals("1")) saleList.add(deal);
				else if(deal.getType().equals("2")) rentList.add(deal);
			}
			Map<String, List<HouseDeal>> map = new HashMap<String, List<HouseDeal>>();
			map.put("saleList", saleList);
			map.put("rentList", rentList);
			logger.info(saleList.toString());
			result.setData(map);
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 아파트 근처의 학교 목록을 가져온다", response=School.class)
	@GetMapping("/school/{guguncode}/{lat}/{lng}/{radius}")
	public ResponseEntity<Result> searchAptSchool(@PathVariable("guguncode") String guguncode,
												  @PathVariable("lat") String lat,
												  @PathVariable("lng") String lng,
												  @PathVariable("radius") int radius, Result result) throws Exception {
		// 특정 거리 안에 있는 학교 목록을 가져온다
		Location loc = new Location(lat, lng, guguncode);
		List<School> schoolList = schoolService.getAllSchoolWithin(loc, radius);
		if(schoolList.size()  == 0) {
			result.setMsg("근처 학교 검색 결과가 없습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
		}else {
			result.setData(schoolList);
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 아파트 근처의 주변 상권 정보를 가져온다", response=School.class)
	@GetMapping("/around/{lat}/{lng}/{radius}")
	public ResponseEntity<Result> searchAptAround(@PathVariable("lat") String lat,
												  @PathVariable("lng") String lng, 
												  @PathVariable("radius") int radius, Result result) throws Exception{
		
		Map<String, List<Location>> map = new HashMap<String, List<Location>>();
		List<Location> hospital = mapApi.getSearchPlaceByCategory(CategoryGroupCode.병원, lat, lng, radius);
		List<Location> subway = mapApi.getSearchPlaceByCategory(CategoryGroupCode.지하철역, lat, lng, radius);
		List<Location> mart = mapApi.getSearchPlaceByCategory(CategoryGroupCode.대형마트, lat, lng, radius);
		List<Location> kindergarden = mapApi.getSearchPlaceByCategory(CategoryGroupCode.어린이집유치원, lat, lng, radius);
		List<Location> academy = mapApi.getSearchPlaceByCategory(CategoryGroupCode.학원, lat, lng, radius);

		map.put("hospital", hospital);
		map.put("subway", subway);
		map.put("mart", mart);
		map.put("kindergarden", kindergarden);
		map.put("academy", kindergarden);
		result.setData(map);
		result.setStatus(StatusMessage.OK);
		
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "교육지수를 구하기 위한 학원, 어린이집 데이터를 받아온다", response=School.class)
	@GetMapping("/academy/{lat}/{lng}/{radius}")
	public ResponseEntity<Result> getAcademyPoint(@PathVariable("lat") String lat,
												  @PathVariable("lng") String lng, 
												  @PathVariable("radius") int radius, Result result) throws Exception{
		
		Map<String, List<Location>> map = new HashMap<String, List<Location>>();
		List<Location> kindergarden = mapApi.getSearchPlaceByCategory(CategoryGroupCode.어린이집유치원, lat, lng, radius);
		List<Location> academy = mapApi.getSearchPlaceByCategory(CategoryGroupCode.학원, lat, lng, radius);

		map.put("kindergarden", kindergarden);
		map.put("academy", kindergarden);
		result.setData(map);
		result.setStatus(StatusMessage.OK);
		
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
}
