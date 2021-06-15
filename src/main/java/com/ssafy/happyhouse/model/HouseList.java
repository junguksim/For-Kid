package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="HouseList: 아파트 목록 정보")
public class HouseList {
	@ApiModelProperty(value = "아파트  정보 번호")
	private String aptNo;
	@ApiModelProperty(value = "아파트 명")
	private String aptName; 
	@ApiModelProperty(value = "구/군코드")
	private String guguncode; 
	@ApiModelProperty(value = "위도")
	private String lat; 
	@ApiModelProperty(value = "경도")
	private String lng; 
	@ApiModelProperty(value = "가격 (아파트 최저가격)")
	private String dealAmount; 
	@ApiModelProperty(value = "면적 (최저 면적)")
	private String area; 
	@ApiModelProperty(value = "평수 (최저 면적)")
	private String areaSize; 
	@ApiModelProperty(value = "타입 (1: 매매, 2: 전월세)")
	private String type; 
	@ApiModelProperty(value = "동 이름")
	private String dong;
	@ApiModelProperty(value = "주소 풀네임")
	private String addressName;

	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getGuguncode() {
		return guguncode;
	}
	public void setGuguncode(String guguncode) {
		this.guguncode = guguncode;
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
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaSize() {
		return areaSize;
	}
	public void setAreaSize(String areaSize) {
		this.areaSize = areaSize;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	@Override
	public String toString() {
		return "HouseList [aptNo=" + aptNo + ", aptName=" + aptName + ", guguncode=" + guguncode + ", lat=" + lat
				+ ", lng=" + lng + ", dealAmount=" + dealAmount + ", area=" + area + ", areaSize=" + areaSize
				+ ", type=" + type + ", dong=" + dong + ", addressName=" + addressName + "]";
	}
}
