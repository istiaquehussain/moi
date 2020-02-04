package com.coe.moi.core.util;

import com.coe.moi.core.entity.QueMessage;
import com.fasterxml.jackson.databind.ObjectMapper;


public class QueMessageMapper {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private static QueMessageMapper instance = null;
	
	private QueMessageMapper()
	{
		super();
	}
	
	public static synchronized QueMessageMapper getQueMessageMapper()
	{
		if (instance == null) 
			instance= new QueMessageMapper(); 
  
        return instance; 
	}
	
	public String getStringMessage(QueMessage message) throws Exception
	{
		return mapper.writeValueAsString(message);
		
	}
	
	public QueMessage getObjectMessage(String message) throws Exception
	{
		return mapper.readValue(message,QueMessage.class);
		
	}
	
	
	
}
