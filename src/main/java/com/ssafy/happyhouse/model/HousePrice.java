package com.ssafy.happyhouse.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="HousePrice: 아파트 가격 정보 - 매매/전월세-평수에 따른 가격")
public class HousePrice {
	private String type;
	private String area;
	private String areaSize;
	private String dealAmount;
	private String guarantyMoney;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getGuarantyMoney() {
		return guarantyMoney;
	}
	public void setGuarantyMoney(String guarantyMoney) {
		this.guarantyMoney = guarantyMoney;
	}
	@Override
	public String toString() {
		return "HousePrice [type=" + type + ", area=" + area + ", areaSize=" + areaSize + ", dealAmount=" + dealAmount
				+ ", guarantyMoney=" + guarantyMoney + "]";
	}
}
