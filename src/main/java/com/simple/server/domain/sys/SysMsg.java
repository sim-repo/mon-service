package com.simple.server.domain.sys;


import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
@Table(name = "[sys msg]")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = SysMsg.class)
public class SysMsg implements SysMessage{

	@Transient
	protected String clazz;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "[id]")	
	protected Integer id;	
	
	@Column(name = "[uuid]")
	protected String juuid;
	
	@Column(name= "[logger_id]")
	protected String loggerId;		
	
	@Column(name = "[service_id_from]")
	protected String serviceIdFrom;
			
	@Column(name = "[service_id_to]")
	protected String serviceIdTo;		
	
	@Column(name= "[outcoming_datetime]")
	protected String outcomingDatetime;
	
	@Column(name= "[incoming_datetime]")
	protected String incomingDatetime;
	
	@Column(name = "[message_header_value]")
	protected String messageHeaderValue;
	
	@Column(name = "[message_body_value]")
	protected String messageBodyValue;
	
	@Column(name= "[log_datetime]")
	protected String logDatetime;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

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

	public String getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId;
	}

	public String getServiceIdFrom() {
		return serviceIdFrom;
	}

	public void setServiceIdFrom(String serviceIdFrom) {
		this.serviceIdFrom = serviceIdFrom;
	}

	public String getServiceIdTo() {
		return serviceIdTo;
	}

	public void setServiceIdTo(String serviceIdTo) {
		this.serviceIdTo = serviceIdTo;
	}

	public String getOutcomingDatetime() {
		return outcomingDatetime;
	}

	public void setOutcomingDatetime(String outcomingDatetime) {
		this.outcomingDatetime = outcomingDatetime;
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
	
		
	
}
