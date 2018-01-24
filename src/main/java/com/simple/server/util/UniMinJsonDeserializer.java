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
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.UniMinMsg;

@Service("uniMinJsonDeserializer")
public class UniMinJsonDeserializer extends JsonDeserializer<UniMinMsg>{

	public static final String NAME = "clazz";
    public static final String SRV_MSG = UniMinMsg.class.getName();
	
    @Override
	public UniMinMsg deserialize(JsonParser jp, DeserializationContext context)throws IOException, JsonProcessingException {		
		 				
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		ObjectNode root = mapper.readTree(jp);
		if (root.has(NAME)) {
	            JsonNode clazzNode = root.get(NAME);	            	            	        	            	        	            	  		            		
	            UniMinMsg msg = null;
				try {
					msg = (UniMinMsg)mapper.readValue(root.toString(), getClass(clazzNode.asText(),UniMinMsg.class));
				} catch (ClassNotFoundException e) {								
					e.printStackTrace();
				}		            		 		            		 		            				            		 		            		
	            return msg;		    	            			    	           
	    }
		throw context.mappingException("mon-service: failed to de-serialize message.");	    		
	}
		
	public static <T> Class<T> getClass(final String className, Class<T> ifaceClass)  throws ClassNotFoundException {			    			
				final Class<T> clazz = (Class<T>) Class.forName(className).asSubclass(ifaceClass);
			    return clazz; 
	}
		
	

}
