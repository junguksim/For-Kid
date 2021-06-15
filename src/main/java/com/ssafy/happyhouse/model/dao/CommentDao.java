package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Comment;

@Mapper
public interface CommentDao {
	
	List<Comment> getCommentAboutApt(String aptNo) throws Exception;
	int registComment(Comment comment) throws Exception;
	int deleteComment(String commentNo) throws Exception;
}
