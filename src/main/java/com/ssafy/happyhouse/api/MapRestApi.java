package com.ssafy.happyhouse.api;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ssafy.happyhouse.model.Location;
import com.ssafy.happyhouse.model.Result;

@Component
public class MapRestApi {

	private static String apiKey = "c36fb2a41fa83ccb470c05c86071c74b";
	private static final String API_SERVER_HOST = "https://dapi.kakao.com";
	private static final String SEARCH_PLACE_KEYWORD_PATH = "/v2/local/search/keyword.json";
	private static final String SEARCH_REGION_CODE_PATH = "/v2/local/geo/coord2regioncode.json";
	private static final String SEARCH_PLACE_CATEGORY_PATH = "/v2/local/search/category.json";
	
	private static Logger logger = LoggerFactory.getLogger(MapRestApi.class);
	
	// 키워드를 통해 (경도, 위도, place_name, address_name, category) 검색
	public Location getSearchPlaceByKeyword(String keyword, String lat, String lng, int radius) throws Exception {
		logger.info(keyword);
		String queryString = "?query="+URLEncoder.encode(keyword, "UTF-8")
									+"&page="+1+"&size="+5+"&y="+lat + "&x="+lng+"&radius="+radius;
		logger.info(queryString);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "KakaoAK " + apiKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH+queryString);
        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<String> re = restTemplate.exchange(rq, String.class);
        
        logger.info(re.getBody().toString());

        Location loc = null;
        if(re.getStatusCode() == HttpStatus.OK) 
        	loc = getLocationParseKeyword(re.getBody().toString());

        return loc;
	}
	
	// 카테고리 + 경도/위도를 이용하여 상권 구하기
	public List<Location> getSearchPlaceByCategory(CategoryGroupCode groupcode, String lat, String lng, int radius) throws Exception {
		logger.info(groupcode.groupcode);
		String queryString = "?category_group_code=" + groupcode.groupcode 
								+"&page="+1+"&size="+15+"&y="+lat + "&x="+lng+"&radius="+radius;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "KakaoAK " + apiKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_CATEGORY_PATH+queryString);
        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<String> re = restTemplate.exchange(rq, String.class);
        
        logger.info(re.getBody().toString());

        List<Location> locList = null;
        if(re.getStatusCode() == HttpStatus.OK) 
        	locList = getLocationParseCategory(re.getBody().toString());

        return locList;
	}
	
	// 경도/위도를 통해 guguncode 검색
	public String getGuguncodeByLat(String lat, String lng) throws Exception {
		String queryString = "?x=" + lng + "&y=" + lat;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "KakaoAK " + apiKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        URI url = URI.create(API_SERVER_HOST+SEARCH_REGION_CODE_PATH+queryString);
        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<String> re = restTemplate.exchange(rq, String.class);
        
        logger.info(re.getBody().toString());
        
        String guguncode = null;
        if(re.getStatusCode() == HttpStatus.OK)
        	guguncode = getGuguncodeParse(re.getBody().toString());
        
        return guguncode;
	}

	// 위도/경도, 장소명, 주소 파싱
	public static Location getLocationParseKeyword(String json) throws Exception {
      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
      JSONArray docuArray = (JSONArray) jsonObject.get("documents"); 
    
      if(docuArray.size() == 0) // 검색 결과가 없을 때는 null 반환
    	  return null;
   
      JSONObject docuObject = (JSONObject) docuArray.get(0);
      Location loc = new Location();
      loc.setLat(docuObject.get("y").toString()); // 경도 
      loc.setLng(docuObject.get("x").toString()); // 위도
      loc.setPlaceName(docuObject.get("place_name").toString()); // 장소명
      loc.setAddressName(docuObject.get("address_name").toString()); // 주소명
      loc.setDistance(docuObject.get("distance").toString()); // 기준 주소부터의 거리
      loc.setCategory(docuObject.get("category_name").toString()); // 카테고리
      
      return loc;
	}
	
	// 위도/경도, 장소명, 주소 파싱
	public static List<Location> getLocationParseCategory(String json) throws Exception {
      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
      JSONArray docuArray = (JSONArray) jsonObject.get("documents"); 
      int size = docuArray.size();
      
      if(size == 0) // 검색 결과가 없을 때는 null 반환
    	  return null;
   
      List<Location> locList = new ArrayList<Location>();
      for(int i = 0; i < size; i++) {
    	  JSONObject docuObject = (JSONObject) docuArray.get(i);
          Location loc = new Location();
          loc.setLat(docuObject.get("y").toString()); // 경도 
          loc.setLng(docuObject.get("x").toString()); // 위도
          loc.setPlaceName(docuObject.get("place_name").toString()); // 장소명
          loc.setAddressName(docuObject.get("address_name").toString()); // 주소명
          loc.setDistance(docuObject.get("distance").toString()); // 기준 주소부터의 거리
          loc.setCategory(docuObject.get("category_name").toString()); // 카테고리
          locList.add(loc);
      }
     
      return locList;
	}
	
	// guguncode 파싱
	public String getGuguncodeParse(String json) throws Exception {
      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
      JSONArray docuArray = (JSONArray) jsonObject.get("documents"); 
      
      if(docuArray.size() == 0) // 검색 결과가 없을 때는 null 반환
    	  return null;
      
      JSONObject docuObject = (JSONObject) docuArray.get(0);
      String dongcode = docuObject.get("code").toString();
      String guguncode = dongcode.substring(0, 5);
      
      return guguncode;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	// 두 좌표의 경도, 위도를 이용해서 거리 구하기 (반환값 : meter)
	public static double getDistance(String lat1, String lng1, String lat2, String lng2) {
		double start_lat = Double.parseDouble(lat1);
		double start_lng = Double.parseDouble(lng1);
		double dest_lat = Double.parseDouble(lat2);
		double dest_lng = Double.parseDouble(lng2);
	
		double theta = start_lng - dest_lng;
		double dist = Math.sin(deg2rad(start_lat)) * Math.sin(deg2rad(dest_lat)) 
						+ Math.cos(deg2rad(start_lat)) * Math.cos(deg2rad(dest_lat)) * Math.cos(deg2rad(theta));
	
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1609.344; // meter 단위로 환산
		
		return dist;
	}
	
	// degree to radian
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	// radian to degree
	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI );
	}
}
