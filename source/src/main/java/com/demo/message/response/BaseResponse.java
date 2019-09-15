package com.demo.message.response;

import com.demo.model.Enums;

public class BaseResponse {
	private boolean success = false;
	private Enums.RESPONSE_TYPE responseType = Enums.RESPONSE_TYPE.ERROR;
	private Enums.RESPONSE_CODE responseCode = Enums.RESPONSE_CODE.RES_9000;
	private String responseMsg;
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Enums.RESPONSE_TYPE getResponseType() {
		return responseType;
	}

	public void setResponseType(Enums.RESPONSE_TYPE responseType) {
		this.responseType = responseType;
	}

	public Enums.RESPONSE_CODE getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Enums.RESPONSE_CODE responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}