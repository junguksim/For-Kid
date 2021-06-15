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
import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.Result;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="SSAFY-BOARD")
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	@ApiOperation(value = "모든 게시글을 반환한다", response=Board.class)
	@RequestMapping(value = "/board/all", method = RequestMethod.GET)
	public ResponseEntity<Result> getAllBoard(Result result) throws Exception{
		List<Board> boardList = boardService.selectBoardAll();
		if(boardList.isEmpty()) {
			result.setMsg("게시글이 존재하지 않습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
		}else {
			result.setData(boardList);
			result.setStatus(StatusMessage.OK);
		}	
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 번호와 일치하는 게시글 정보를 반환한다", response=Board.class)
	@RequestMapping(value = "/board/{no}", method = RequestMethod.GET)
	public ResponseEntity<Result> getBoard(@PathVariable int no, Result result) throws Exception{
		Board board = boardService.selectBoard(no);
		if(board == null) {
			result.setMsg("게시글이 존재하지 않습니다.");
			result.setStatus(StatusMessage.NOT_FOUND);
		}else {
			boardService.updateViewCount(no); // 조회수 +1 갱신
			result.setData(board);
			result.setStatus(StatusMessage.OK);
		}	
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글을 등록한다")
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	public ResponseEntity<Result> registBoard(@RequestBody Board board, HttpServletRequest request, Result result) throws Exception{
		int total = boardService.registBoard(board);
		if(total <= 0) {
			result.setMsg("오류가 발생하여 게시글이 등록되지 않았습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
		}else {
			result.setMsg("게시글이 등록되었습니다!");
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글을 수정한다")
	@RequestMapping(value = "/board/{no}", method = RequestMethod.PUT)
	public ResponseEntity<Result> updateBoard(@RequestBody Board board, @PathVariable int no, Result result) throws Exception{
		board.setNo(no);
		int total = boardService.updateBoard(board);
		if(total <= 0) {
			result.setMsg("오류가 발생하여 게시글이 수정되지 않았습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
		}else {
			result.setMsg("게시글이 수정되었습니다!");
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글을 삭제한다")
	@RequestMapping(value = "/board/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteBoard(@PathVariable int no, Result result) throws Exception{
		int total = boardService.deleteBoard(no);
		if(total <= 0) {
			result.setMsg("오류가 발생하여 게시글이 삭제되지 않았습니다.");
			result.setStatus(StatusMessage.INTERNAL_SERER_ERROR);
		}else {
			result.setMsg("게시글이 삭제되었습니다!");
			result.setStatus(StatusMessage.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
}
