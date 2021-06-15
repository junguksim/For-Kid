package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="HouseDeal: 아파트 거래 정보")
public class HouseDeal {
	@ApiModelProperty(value = "아파트 정보 번호")
	String aptNo;
	@ApiModelProperty(value = "동 이름")
	String dong;
	@ApiModelProperty(value = "아파트명")
	String aptName;
	@ApiModelProperty(value = "구/군 코드")
	String guguncode;
	@ApiModelProperty(value = "거래금액 (xx만원)")
	String dealAmount;
	@ApiModelProperty(value = "전세 비용")
	String guarantyMoney;
	@ApiModelProperty(value = "건설일")
	String buildYear;
	@ApiModelProperty(value = "거래년도")
	String dealYear;
	@ApiModelProperty(value = "거래 월")
	String dealMonth;
	@ApiModelProperty(value = "거래 일")
	String dealDay;
	@ApiModelProperty(value = "평당미터")
	String area;
	@ApiModelProperty(value = "층수")
	String floor;
	@ApiModelProperty(value = "지번")
	String jibun;
	@ApiModelProperty(value = "타입 (1: 매매, 2: 전월세)")
	String type;
	@ApiModelProperty(value = "평수")
	String areaSize;

	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
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
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	public String getDealDay() {
		return dealDay;
	}
	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGuarantyMoney() {
		return guarantyMoney;
	}
	public void setGuarantyMoney(String guarantyMoney) {
		this.guarantyMoney = guarantyMoney;
	}
	public String getAreaSize() {
		return areaSize;
	}
	public void setAreaSize(String areaSize) {
		this.areaSize = areaSize;
	}
	@Override
	public String toString() {
		return "HouseDeal [aptNo=" + aptNo + ", dong=" + dong + ", aptName=" + aptName + ", guguncode=" + guguncode
				+ ", dealAmount=" + dealAmount + ", buildYear=" + buildYear + ", dealYear=" + dealYear + ", dealMonth="
				+ dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", jibun=" + jibun
				+ ", type=" + type + ", guarantyMoney=" + guarantyMoney + ", areaSize=" + areaSize + "]";
	}
}
