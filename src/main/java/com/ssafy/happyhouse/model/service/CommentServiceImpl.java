package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Comment;
import com.ssafy.happyhouse.model.dao.CommentDao;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Comment> getCommentAboutApt(String aptNo) throws Exception {
		return commentDao.getCommentAboutApt(aptNo);
	}

	@Override
	public int registComment(Comment comment) throws Exception {
		return commentDao.registComment(comment);
	}

	@Override
	public int deleteComment(String commentNo) throws Exception {
		return commentDao.deleteComment(commentNo);
	}
	
}
