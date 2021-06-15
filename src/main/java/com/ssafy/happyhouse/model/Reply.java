package com.ssafy.happyhouse.model;

import java.sql.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Reply : 댓글 정보")
public class Reply {
	@ApiModelProperty(value = "댓글 번호")
	private int replyNo;
	@ApiModelProperty(value = "게시글 번호")
	private int boardNo;
	@ApiModelProperty(value = "작성자 아이디")
	private String writerId;
	@ApiModelProperty(value = "댓글 내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private Date registDate;
	
	// 테이블 외의 데이터
	@ApiModelProperty(value = "작성자 닉네임")
	private String writerName;
	
	public Reply(int replyNo, int boardNo, String writerId, String writerName, String content, Date registDate) {
		super();
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.writerId = writerId;
		this.writerName = writerName;
		this.content = content;
		this.registDate = registDate;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	
}
