package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.ContentType;
import com.simple.server.util.UniMinJsonDeserializer;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "clazz" })
@JsonInclude(Include.NON_EMPTY)
@JsonDeserialize(using = UniMinJsonDeserializer.class, as = UniMinMsg.class)
public class UniMinMsg {
	
	private int id;
	
	protected String clazz;
	protected String juuid;
	protected String eventId;
	protected String body;
	protected String url;
	protected String datetime;
	protected String contentType;
	protected String originalRequestBody;
	
	@JsonGetter("clazz")
	public String getClazz() {
		return  UniMinMsg.class.getName();
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJuuid() {
		return juuid;
	}
	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public ContentType getContentType() {
		return ContentType.valueOf(contentType);
	}
	public void setContentType(ContentType contentType) {
		this.contentType = contentType.toValue();
	}
	
	public String getOriginalRequestBody() {
		return originalRequestBody;
	}
	public void setOriginalRequestBody(String originalRequestBody) {
		this.originalRequestBody = originalRequestBody;
	}
	@Override
	public String toString() {
		return "UniMinMsg [eventId=" + eventId + ", body=" + body + "]";
	}
	
	
	
}
