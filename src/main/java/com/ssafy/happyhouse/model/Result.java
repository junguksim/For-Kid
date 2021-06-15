package com.ssafy.happyhouse.model;

import com.ssafy.happyhouse.StatusMessage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Result : 응답 데이터")
public class Result {
	@ApiModelProperty(value = "상태코드", notes = "200:OK, 400:Bad Request, 404:Not Found, 500:INTERNAL_SERVER_ERROR")
	StatusMessage status; 
	@ApiModelProperty(value = "응답메세지")
	String msg; 
	@ApiModelProperty(value = "응답데이터")
	Object data; 
	
	public StatusMessage getStatus() {
		return status;
	}
	public void setStatus(StatusMessage status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseObject [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
}
