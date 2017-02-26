package com.simple.server.domain;

import com.simple.server.domain.contract.IContract;

@SuppressWarnings("serial")
public abstract class AContract implements IContract {

	static final long serialVersionUID = 1L;
	
	protected Integer id;

	protected String juuid;

	protected String endPointId;

	protected String serviceIdFrom;

	protected String outcomingDatetime;

	protected String serviceIdTo;

	protected String incomingDatetime;

	protected String messageHeaderValue;

	protected String messageBodyValue;

	protected String logDatetime;

	protected String loggerId;

	protected String methodHandler;

	protected String serviceRoleFrom;

	protected String serviceRoleTo;

	protected String operationType;

	protected String clazz;

	protected String responseURI;

	protected String responseContentType;

	protected String responseContractClass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJuuid() {
		return juuid;
	}

	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}

	public String getEndPointId() {
		return endPointId;
	}

	public void setEndPointId(String endPointId) {
		this.endPointId = endPointId;
	}

	public String getServiceIdFrom() {
		return serviceIdFrom;
	}

	public void setServiceIdFrom(String serviceIdFrom) {
		this.serviceIdFrom = serviceIdFrom;
	}

	public String getOutcomingDatetime() {
		return outcomingDatetime;
	}

	public void setOutcomingDatetime(String outcomingDatetime) {
		this.outcomingDatetime = outcomingDatetime;
	}

	public String getServiceIdTo() {
		return serviceIdTo;
	}

	public void setServiceIdTo(String serviceIdTo) {
		this.serviceIdTo = serviceIdTo;
	}

	public String getIncomingDatetime() {
		return incomingDatetime;
	}

	public void setIncomingDatetime(String incomingDatetime) {
		this.incomingDatetime = incomingDatetime;
	}

	public String getMessageHeaderValue() {
		return messageHeaderValue;
	}

	public void setMessageHeaderValue(String messageHeaderValue) {
		this.messageHeaderValue = messageHeaderValue;
	}

	public String getMessageBodyValue() {
		return messageBodyValue;
	}

	public void setMessageBodyValue(String messageBodyValue) {
		this.messageBodyValue = messageBodyValue;
	}

	public String getLogDatetime() {
		return logDatetime;
	}

	public void setLogDatetime(String logDatetime) {
		this.logDatetime = logDatetime;
	}

	public String getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId;
	}

	public String getMethodHandler() {
		return methodHandler;
	}

	public void setMethodHandler(String methodHandler) {
		this.methodHandler = methodHandler;
	}

	public String getServiceRoleFrom() {
		return serviceRoleFrom;
	}

	public void setServiceRoleFrom(String serviceRoleFrom) {
		this.serviceRoleFrom = serviceRoleFrom;
	}

	public String getServiceRoleTo() {
		return serviceRoleTo;
	}

	public void setServiceRoleTo(String serviceRoleTo) {
		this.serviceRoleTo = serviceRoleTo;
	}

	public String getResponseURI() {
		return responseURI;
	}

	public void setResponseURI(String responseURI) {
		this.responseURI = responseURI;
	}

	public String getResponseContractClass() {
		return responseContractClass;
	}

	public void setResponseContractClass(String responseContractClass) {
		this.responseContractClass = responseContractClass;
	}

	public String getClazz() {
		return clazz;
	}

	public String getResponseContentType() {
		return responseContentType;
	}

	public void setResponseContentType(String responseContentType) {
		this.responseContentType = responseContentType;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

}
