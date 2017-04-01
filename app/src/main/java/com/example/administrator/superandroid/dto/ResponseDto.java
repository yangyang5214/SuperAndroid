package com.example.administrator.superandroid.dto;

import java.io.Serializable;

public class ResponseDto<T> implements Serializable {

	private Boolean success = true;

	private String message;

	private T obj;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

}
