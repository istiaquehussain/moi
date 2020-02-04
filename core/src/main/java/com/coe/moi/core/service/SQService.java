package com.coe.moi.core.service;

import java.util.List;
import java.util.Optional;

import com.coe.moi.core.entity.Employee;
import com.coe.moi.core.entity.QueMessage;

public interface SQService {
	
	Optional<QueMessage> readMessage();
	
	List<QueMessage> readMessages();
	
	Optional<QueMessage> writeMessage(QueMessage message);
	
	
}
