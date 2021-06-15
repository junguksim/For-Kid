package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Reply;

public interface ReplyService {
	List<Reply> selectReplyAll(int boardNo) throws Exception;
	Reply selectReply(int replyNo) throws Exception;
	int registReply(Reply reply) throws Exception;
	int updateReply(Reply reply) throws Exception;
	int deleteReply(int replyNo) throws Exception;
}
