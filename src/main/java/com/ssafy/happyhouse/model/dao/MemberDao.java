package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Member;

@Mapper
public interface MemberDao {
	Member login(Member inputMember) throws Exception;
	Member getMember(String userId) throws SQLException;
	Member getMemberWithSalt(String userId) throws SQLException;
	int signup(Member inputMember) throws Exception;
	int modify(Member inputMember) throws SQLException;
	int delete(String userId) throws SQLException;
}
