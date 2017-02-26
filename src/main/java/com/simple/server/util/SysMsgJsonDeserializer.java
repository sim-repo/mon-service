package com.simple.server.util;

import java.io.IOException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.simple.server.domain.sys.SysMessage;
import com.simple.server.domain.sys.SysMsg;

@Service("sysMsgJsonDeserializer")
public class SysMsgJsonDeserializer extends JsonDeserializer<SysMessage> {

	public static final String NAME = "clazz";
    public static final String SRV_MSG = SysMsg.class.getSimpleName();
	
	@Override
	public SysMessage deserialize(JsonParser jp, DeserializationContext context)throws IOException, JsonProcessingException {		
		 
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		ObjectNode root = mapper.readTree(jp);
		if (root.has(NAME)) {
	            JsonNode jsonNode = root.get(NAME);
	            if(jsonNode.asText().equals(SRV_MSG)) {
	                return mapper.readValue(root.toString(), SysMsg.class);
	            }	            	            	       
	    }
		throw context.mappingException("log-service: failed to de-serialize message");	    		
	}
}

