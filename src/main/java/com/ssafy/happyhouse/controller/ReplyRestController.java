package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.happyhouse.StatusMessage;
import com.ssafy.happyhouse.model.Reply;
import com.ssafy.happyhouse.model.Result;
import com.ssafy.happyhouse.model.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="SSAFY-REPLY")
public class ReplyRestController {
	@Autowired
	private ReplyService replyService;
	//private static final Logger logger = LoggerFactory.getLogger(ReplyRestController.class);
	
	@ApiOperation(value = "해당 게시글에 대한 모든 댓글을 반환한다.", response=Reply.class)
	@RequestMapping(value = "/reply/{boardNo}/all", method = RequestMethod.GET)
	public ResponseEntity<Result> getAllReply(@PathVariable int boardNo, Result result) throws Exception {
		List<Reply> replyList = replyService.selectReplyAll(boardNo);
		if(replyList.isEmpty()) {
			result.setMsg("등록된 댓글이 없습니다. 새로운 댓글을 달아보세요!");
			result.setStatus(StatusMessage.NOT_FOUND);
		}else {
			result.setData(replyList);
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 게시글에 댓글을 등록한다")
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public ResponseEntity<Result> registReply(@RequestBody Reply reply, Result result) throws Exception {
		int total = replyService.registReply(reply);
		if(total <= 0) {
			result.setMsg("등록에 실패하였습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
		}else {
			result.setMsg("등록되었습니다!");
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 댓글을 삭제한다")
	@RequestMapping(value = "/reply/{replyNo}", method = RequestMethod.DELETE)
	public ResponseEntity<Result> registReply(@PathVariable int replyNo, Result result) throws Exception {
		int total = replyService.deleteReply(replyNo);
		if(total <= 0) {
			result.setMsg("삭제에 실패하였습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
		}else {
			result.setMsg("삭제되었습니다!");
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 댓글을 수정한다")
	@RequestMapping(value = "/reply", method = RequestMethod.PUT)
	public ResponseEntity<Result> updateReply(@RequestBody Reply reply, Result result) throws Exception {
		int total = replyService.updateReply(reply);
		if(total <= 0) {
			result.setMsg("수정에 실패하였습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
		}else {
			result.setMsg("수정되었습니다!");
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
}
