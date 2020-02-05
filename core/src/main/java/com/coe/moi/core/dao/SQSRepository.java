package com.coe.moi.core.dao;

import java.util.List;
import java.util.Optional;

import com.amazonaws.services.sqs.model.Message;
import com.coe.moi.core.entity.QueMessage;

public interface SQSRepository {
	public List<Message> receiveMessage() ;
	
	public List<Message> receiveAndRemoveMessage() ;
	
	public Optional<String> writeMessage(String message) ;

	public List<Message> readMessagesForId(String Id);
}
