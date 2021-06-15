package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<Board> selectBoardAll() throws Exception {
		return boardDao.selectBoardAll();
	}

	@Override
	public Board selectBoard(int no) throws Exception {
		return boardDao.selectBoard(no);
	}

	@Override
	public int registBoard(Board board) throws Exception {
		return boardDao.registBoard(board);
	}

	@Override
	public int updateBoard(Board board) throws Exception {
		return boardDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(int no) throws Exception {
		return boardDao.deleteBoard(no);
	}

	@Override
	public int updateViewCount(int no) throws Exception {
		return boardDao.updateViewCount(no);
	}

}
