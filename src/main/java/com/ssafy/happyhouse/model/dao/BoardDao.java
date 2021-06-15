package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Board;

@Mapper
public interface BoardDao {

	// 전체 목록 얻어오기
	List<Board> selectBoardAll() throws Exception;
	// 특정 게시글 얻어오기 
	Board selectBoard(int no) throws Exception;
	// 게시글 등록
	int registBoard(Board board) throws Exception;
	// 게시글 수정
	int updateBoard(Board board) throws Exception;
	// 게시글 삭제
	int deleteBoard(int no) throws Exception;
	// 조회수 증가
	int updateViewCount(int no) throws Exception;
}
