package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Location : 위치정보")
public class Location {
	
	@ApiModelProperty(value = "장소명")
	private String placeName; 
	@ApiModelProperty(value = "주소")
	private String addressName; 
	@ApiModelProperty(value = "위도")
	private String lat; 
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "구/군 코드")
	private String guguncode;
	
	@ApiModelProperty(value = "카테고리 (region, apartment, school)")
	private String category;
	@ApiModelProperty(value = "중심좌표로 부터 거리")
	private String distance; 
	
	public Location() {}
	
	public Location(String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	public Location(String lat, String lng, String guguncode) {
		this.lat = lat;
		this.lng = lng;
		this.guguncode = guguncode;
	}

	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getGuguncode() {
		return guguncode;
	}
	public void setGuguncode(String guguncode) {
		this.guguncode = guguncode;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Location [placeName=" + placeName + ", addressName=" + addressName + ", lat=" + lat + ", lng=" + lng
				+ ", guguncode=" + guguncode + "]";
	}
}
