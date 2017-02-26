package com.simple.server.domain.sys;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


public class JolokiaValue {
	String init;
	String committed;
	String max;
	String used;
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public String getCommitted() {
		return committed;
	}
	public void setCommitted(String committed) {
		this.committed = committed;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	
	
	
}
