package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.StatusMessage;
import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.Comment;
import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.Result;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.CommentService;
import com.ssafy.happyhouse.model.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="House-Comment")
public class CommentRestController {
	
	@Autowired
	private CommentService commentService;

	private static final Logger logger = LoggerFactory.getLogger(CommentRestController.class);

	@ApiOperation(value = "해당 아파트에 대한 모든 코멘트을 반환한다", response=Board.class)
	@RequestMapping(value = "/comment/{aptNo}", method = RequestMethod.GET)
	public ResponseEntity<Result> getAllComment(@PathVariable("aptNo") String aptNo, Result result) throws Exception{
		List<Comment> comList = commentService.getCommentAboutApt(aptNo);
		if(comList.isEmpty()) {
			result.setMsg("해당 아파트에 대한 코멘트가 존재하지 않습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
		}else {
			result.setData(comList);
			result.setStatus(StatusMessage.OK);
		}	
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "코멘트를 등록한다")
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public ResponseEntity<Result> registComment(@RequestBody Comment comment, Result result) throws Exception{
		int total = commentService.registComment(comment);
		if(total <= 0) {
			result.setMsg("오류가 발생하여 코멘트가 등록되지 않았습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
		}else {
			result.setMsg("코멘트가 등록되었습니다!");
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "코멘트를 삭제한다")
	@RequestMapping(value = "/comment/{commentNo}", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteComment(@PathVariable String commentNo, Result result) throws Exception{
		int total = commentService.deleteComment(commentNo);
		if(total <= 0) {
			result.setMsg("오류가 발생하여 코멘트가 삭제되지 않았습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
		}else {
			result.setMsg("코멘트가 삭제되었습니다!");
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
}
