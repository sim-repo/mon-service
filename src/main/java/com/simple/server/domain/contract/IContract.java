package com.simple.server.domain.contract;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.util.BusMsgJsonDeserializer;

@JsonPropertyOrder({ "clazz" })
@JsonInclude(Include.NON_EMPTY)
@JsonDeserialize(using = BusMsgJsonDeserializer.class)
public interface IContract extends Serializable {
	static final long serialVersionUID = 1L;
	
	@JsonGetter("clazz")
	String getClazz();
}
