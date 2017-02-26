package com.simple.server.domain.sys;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


public class JolokiaRequest {
	String mbean;
	String attribute;
	String type;
	public String getMbean() {
		return mbean;
	}
	public void setMbean(String mbean) {
		this.mbean = mbean;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
