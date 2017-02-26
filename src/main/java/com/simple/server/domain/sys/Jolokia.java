package com.simple.server.domain.sys;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Jolokia{
	JolokiaRequest request;
	JolokiaValue value;
	String timestamp;
	String status;
	public JolokiaRequest getRequest() {
		return request;
	}
	public void setRequest(JolokiaRequest request) {
		this.request = request;
	}
	public JolokiaValue getValue() {
		return value;
	}
	public void setValue(JolokiaValue value) {
		this.value = value;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
