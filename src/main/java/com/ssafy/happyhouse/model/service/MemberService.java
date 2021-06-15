package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.Member;

public interface MemberService {
	Member login(Member inputMember) throws Exception;
	Member getMember(String userId) throws SQLException;
	Member getMemberWithSalt(String userId) throws Exception;
	int signup(Member inputMember) throws Exception;
	int modify(Member inputMember) throws SQLException;
	int delete(String userId) throws SQLException;
	
}
