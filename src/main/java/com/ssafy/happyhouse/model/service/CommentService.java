package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Comment;

public interface CommentService {

	List<Comment> getCommentAboutApt(String aptNo) throws Exception;
	int registComment(Comment comment) throws Exception;
	int deleteComment(String commentNo) throws Exception;
}
