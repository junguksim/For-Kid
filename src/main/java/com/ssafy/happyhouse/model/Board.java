package com.ssafy.happyhouse.model;

import java.sql.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Board : 게시글 정보")
public class Board {
	@ApiModelProperty(value = "게시글 번호(PK)")
	private int no;
	@ApiModelProperty(value = "게시글 제목")
	private String title;
	@ApiModelProperty(value = "게시글 내용")
	private String content;
	@ApiModelProperty(value = "작성자 아이디")
	private String writerId;
	@ApiModelProperty(value = "등록일")
	private Date registDate;
	@ApiModelProperty(value = "조회수")
	private int viewCount;
	
	// Table에 없는 정보
	@ApiModelProperty(value = "작성자 이름")
	private String writerName;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}
