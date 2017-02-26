package com.simple.server.domain.sys;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.util.SysMsgJsonDeserializer;

@JsonPropertyOrder({"clazz"})
@JsonInclude(Include.NON_EMPTY)
@JsonDeserialize(using=SysMsgJsonDeserializer.class)
public interface SysMessage  extends Serializable{
	@JsonGetter("clazz")
	String getClazz();
	
}
