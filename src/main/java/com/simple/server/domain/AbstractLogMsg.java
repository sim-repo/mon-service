package com.simple.server.domain;

import com.simple.server.domain.contract.IContract;

public abstract class AbstractLogMsg implements IContract {
	protected String clazz;

	@Override
	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

}
