package com.ssafy.happyhouse;

public enum StatusMessage {

	OK(200, "OK"),
	BAD_REQUEST(400, "BAD_REQUEST"),
	NOT_FOUND(404, "NOT_FOUND"),
	INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");

    int status;
    String statusMsg;

    StatusMessage(int status, String statusMsg) {
       this.status = status;
       this.statusMsg = statusMsg;
    }
	
}
