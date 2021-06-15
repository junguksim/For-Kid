package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.happyhouse.model.service.HouseService;

import io.swagger.annotations.ApiOperation;

@Controller
public class MemberController {
	
	@ApiOperation(value = "회원가입 페이지로 이동한다.")
	@RequestMapping(value = "/member/signupForm", method = RequestMethod.GET, headers = {"Content-type=application/json"})
	public String signupForm() {
		return "signup";
	}
	
	@ApiOperation(value = "회원정보 수정 페이지로 이동한다. ")
	@RequestMapping(value = "/member/modifyForm", method = RequestMethod.GET, headers = {"Content-type=application/json"})
	public String modfiyForm(HttpSession session) {
		if(session.getAttribute("userinfo") == null)
			return "error/405";
		return "modify";
	}
	@ApiOperation(value = "마이페이지로 이동한다.")
	@RequestMapping(value = "/member/memberInfo", method = RequestMethod.GET, headers = {"Content-type=application/json"})
	public String memberInfo(HttpSession session) {
		if(session.getAttribute("userinfo") == null)
			return "error/405";
		return "member";
	}
}
