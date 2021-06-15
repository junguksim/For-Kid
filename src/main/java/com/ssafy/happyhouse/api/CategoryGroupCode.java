package com.ssafy.happyhouse.api;

public enum CategoryGroupCode {
	
	// 카테고리 그룹 코드(MT1=대형마트 / CS2=편의점 / PS3=어린이집, 유치원 / SC4=학교 / AC5=학원 / PK6=주차장 / OL7=주유소, 충전소 / SW8=지하철역 / BK9=은행 / CT1=문화시설 / AG2=중개업소 / PO3=공공기관 / AT4=관광명소 / AD5=숙박 / FD6=음식점 / CE7=카페 / HP8=병원 / PM9=약국)
	
	대형마트("MT1", "대형마트"),
	편의점("CS2", "편의점"),
	어린이집유치원("PS3", "어린이집유치원"),
	학교("SC4", "학교"),
	학원("AC5", "학원"),
	지하철역("SW8", "지하철역"),
	병원("HP8", "병원");	
	
	String groupcode;
	String group;
	
	CategoryGroupCode(String groupcode, String group) {
		this.groupcode = groupcode;
		this.group = group;
	}
}
