package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="HouseInfo: 아파트 기본 정보")
public class HouseInfo {
	@ApiModelProperty(value = "아파트 정보 번호(PK)")
	private String no;
	@ApiModelProperty(value = "동 이름")
	private String dong;
	@ApiModelProperty(value = "아파트 명")
	private String aptName;
	@ApiModelProperty(value = "구/군 코드")
	private String guguncode;
	@ApiModelProperty(value = "건축일")
	private String buildYear; 
	@ApiModelProperty(value = "지번")
	private String jibun; 
	@ApiModelProperty(value = "위도")
	private String lat; 
	@ApiModelProperty(value = "경도")
	private String lng; 
	@ApiModelProperty(value = "이미지")
	private String img; 
	// 테이블에 없는 내용
	@ApiModelProperty(value = "주소 풀네임")
	private String addressName;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
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

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getAddressName() {
		return addressName;
	}
	
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@Override
	public String toString() {
		return "HouseInfo [no=" + no + ", dong=" + dong + ", aptName=" + aptName + ", guguncode=" + guguncode
				+ ", buildYear=" + buildYear + ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", img=" + img
				+ "]";
	}
}

