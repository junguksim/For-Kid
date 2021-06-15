package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="School : 학교 데이터")
public class School {
	@ApiModelProperty(value = "학교 코드 (PK)")
	private String schoolCode;
	@ApiModelProperty(value = "카테고리 (초등학교, 중학교, 고등학교, 특수학교)")
	private String category;
	@ApiModelProperty(value = "교육청")
	private String eduOffice;
	@ApiModelProperty(value = "학교 이름")
	private String name;
	@ApiModelProperty(value = "주소")
	private String address;
	@ApiModelProperty(value = "지번")
	private String jibun;
	@ApiModelProperty(value = "설립구분")
	private String classification;
	@ApiModelProperty(value = "설립유형")
	private String type;
	@ApiModelProperty(value = "개교기념일")
	private String anniversary;
	@ApiModelProperty(value = "학교설립일")
	private String estabilshDate;
	@ApiModelProperty(value = "법정동코드")
	private String dongcode;
	@ApiModelProperty(value = "우편번호")
	private String zipcode;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "전화번호")
	private String phoneNum;
	@ApiModelProperty(value = "학교사이트 URL")
	private String url;
	@ApiModelProperty(value = "남녀공학 분류")
	private String coed;
	@ApiModelProperty(value = "학급수")
	private String classCount;
	@ApiModelProperty(value = "학생수")
	private String studentCount;
	@ApiModelProperty(value = "학급당 학생수")
	private String studentPerClass;
	
	// 테이블에 없는 데이터
	private Double distance;
	
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEduOffice() {
		return eduOffice;
	}
	public void setEduOffice(String eduOffice) {
		this.eduOffice = eduOffice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAnniversary() {
		return anniversary;
	}
	public void setAnniversary(String anniversary) {
		this.anniversary = anniversary;
	}
	public String getEstabilshDate() {
		return estabilshDate;
	}
	public void setEstabilshDate(String estabilshDate) {
		this.estabilshDate = estabilshDate;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCoed() {
		return coed;
	}
	public void setCoed(String coed) {
		this.coed = coed;
	}
	public String getClassCount() {
		return classCount;
	}
	public void setClassCount(String classCount) {
		this.classCount = classCount;
	}
	public String getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(String studentCount) {
		this.studentCount = studentCount;
	}
	public String getStudentPerClass() {
		return studentPerClass;
	}
	public void setStudentPerClass(String studentPerClass) {
		this.studentPerClass = studentPerClass;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
