package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.StatusMessage;
import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.Result;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member")
@Api(value="SSAFY-MEMBER")
public class MemberRestController {

	@Autowired
	private MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(MemberRestController.class);
	
	@ApiOperation(value = "로그인 성공 시 로그인한 유저의 정보를, 실패 시 null을 반환한다", response=Member.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	public ResponseEntity<Result> login(@RequestParam Map<String, String> map, @RequestBody Member inputMember, HttpSession session, HttpServletResponse response, Result result) throws Exception{
		Member member = memberService.login(inputMember);
		if(member != null) {
			session.setAttribute("userinfo", member);
			Cookie cookie = new Cookie("userid", member.getUserId());
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
			response.addCookie(cookie);
			result.setData(member);
			result.setStatus(StatusMessage.OK);
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		}
		result.setMsg("로그인 오류! 아이디 혹은 비밀번호를 확인해주세요.");
		result.setStatus(StatusMessage.NOT_FOUND);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원가입을 시도한 후, 성공시 SUCCESS를 반환한다", response=String.class)
	@RequestMapping(value = "", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	public ResponseEntity<Result> signup(@RequestBody Member inputMember, Result result) throws Exception{
		int total = memberService.signup(inputMember);
		if(total <= 0) {
			result.setMsg("회원가입에 실패하였습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		}
		result.setMsg("회원가입이 완료되었습니다");
		result.setStatus(StatusMessage.OK);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원정보 수정 후, 성공시 SUCCESS를 반환한다", response=String.class)
	@RequestMapping(value = "", method = RequestMethod.PUT, headers = {"Content-type=application/json"})
	public ResponseEntity<Result> modify(@RequestBody Member inputMember, HttpSession session, Result result) throws SQLException{
		int total = memberService.modify(inputMember);
		if(total <= 0) {
			result.setMsg("회원정보 수정에 실패하였습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		}
		// 회원정보 수정 후, session 값 변경 
		session.setAttribute("userinfo", memberService.getMember(inputMember.getUserId()));
		result.setMsg("회원정보 수정이 완료되었습니다");
		result.setData(memberService.getMember(inputMember.getUserId()));
		result.setStatus(StatusMessage.OK);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 삭제 후, 성공시 SUCCESS를 반환한다", response=String.class)
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, headers = {"Content-type=application/json"})
	public ResponseEntity<Result> delete(@PathVariable("userId") String userId, HttpSession session, Result result) throws SQLException{
		int total = memberService.delete(userId);
		if(total <= 0) {
			result.setMsg("회원탈퇴에 실패하였습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		}
		session.invalidate();
		result.setMsg("회원탈퇴 되었습니다.");
		result.setStatus(StatusMessage.OK);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "로그아웃 시, session에 있던 유저 정보를 삭제한 후 SUCCESS를 반환한다", response=String.class)
	@RequestMapping(value = "/logout", method = RequestMethod.POST,  headers = {"Content-type=application/json"})
	public ResponseEntity<Result> logout(HttpSession session, Result result) {
		session.invalidate();
		result.setMsg("로그아웃 되었습니다!");
		result.setStatus(StatusMessage.OK);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
}
