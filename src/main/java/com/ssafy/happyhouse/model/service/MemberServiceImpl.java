package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.SecurityUtil;
import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private SecurityUtil securityUtil;

	@Override
	public Member login(Member inputMember) throws Exception {
		Member member = getMemberWithSalt(inputMember.getUserId());
		
		String originSalt = member.getSalt();
		String originPwd = member.getUserPwd();
		String inputPwd = securityUtil.Hashing(inputMember.getUserPwd().getBytes(), originSalt);

		System.out.println(inputMember);
		System.out.println(originPwd);
		System.out.println(inputPwd);
		
		if(!originPwd.equals(inputPwd)) { 
			return null;
		}
		inputMember.setUserPwd(inputPwd);
		return sqlSession.getMapper(MemberDao.class).login(inputMember);
	}
	
	@Override
	public Member getMember(String userId) throws SQLException {
		return sqlSession.getMapper(MemberDao.class).getMember(userId);
	}

	@Override
	public Member getMemberWithSalt(String userId) throws SQLException {
		return sqlSession.getMapper(MemberDao.class).getMemberWithSalt(userId);
	}
	
	@Override
	public int signup(Member inputMember) throws Exception {
		String salt = securityUtil.getSalt();
		String newPassword = securityUtil.Hashing(inputMember.getUserPwd().getBytes(), salt);
		inputMember.setUserPwd(newPassword);
		inputMember.setSalt(salt);
		return sqlSession.getMapper(MemberDao.class).signup(inputMember);
	}

	@Override
	public int modify(Member inputMember) throws SQLException {
		return sqlSession.getMapper(MemberDao.class).modify(inputMember);
	}

	@Override
	public int delete(String userId) throws SQLException {
		return sqlSession.getMapper(MemberDao.class).delete(userId);
	}
	
}
