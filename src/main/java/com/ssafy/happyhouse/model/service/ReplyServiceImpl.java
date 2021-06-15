package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Reply;
import com.ssafy.happyhouse.model.dao.ReplyDao;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public List<Reply> selectReplyAll(int boardNo) throws Exception {
		return replyDao.selectReplyAll(boardNo);
	}

	@Override
	public Reply selectReply(int replyNo) throws Exception {
		return replyDao.selectReply(replyNo);
	}

	@Override
	public int registReply(Reply reply) throws Exception {
		return replyDao.registReply(reply);
	}

	@Override
	public int updateReply(Reply reply) throws Exception {
		return replyDao.updateReply(reply);
	}

	@Override
	public int deleteReply(int replyNo) throws Exception {
		return replyDao.deleteReply(replyNo);
	}
	
}
