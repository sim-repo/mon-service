package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonAutoDetect
@JsonDeserialize(as = StatusMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusMsg extends AContract{
	
	private static final long serialVersionUID = 1L;
			
	private String code;
	private String message;
	
	public StatusMsg() {
	}

	public StatusMsg(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getClazz() {
		return this.getClass().getName();
	}
	
	public void set(String senderId, String eventId, String juuid, String code, String message) {
		this.setSenderId(senderId);
		this.setEventId(eventId);
		this.setJuuid(juuid);
		this.setCode(code);
		this.setMessage(message);
	}

	@Override
	public String toString() {
		return "StatusMsg [code=" + code + ", message=" + message + ", methodHandler=" + methodHandler
				+ ", operationType=" + operationType + ", responseURI=" + responseURI + ", responseContentType="
				+ responseContentType + "]";
	}
	
	
	
}
