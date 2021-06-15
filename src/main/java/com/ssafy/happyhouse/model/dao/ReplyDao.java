package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Reply;

@Mapper
public interface ReplyDao {
	List<Reply> selectReplyAll(int boardNo) throws Exception;
	Reply selectReply(int replyNo) throws Exception;
	int registReply(Reply reply) throws Exception;
	int updateReply(Reply reply) throws Exception;
	int deleteReply(int replyNo) throws Exception;
	
}
