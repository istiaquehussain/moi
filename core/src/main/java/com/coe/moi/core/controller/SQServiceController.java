package com.coe.moi.core.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coe.moi.core.entity.QueMessage;
import com.coe.moi.core.service.SQService;

@RestController
public class SQServiceController {
	@Autowired
	SQService service;
	
	
	@GetMapping("/sqs/messages")
	public ResponseEntity<?>  readMessages(){
		List<QueMessage> messages=service.readMessages();
		return new ResponseEntity<>(messages,((messages!=null) && (messages.size()>0))?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/sqs/new")
	public ResponseEntity<?>  createMessage(@RequestBody QueMessage message){
		Optional<QueMessage> newMessage=service.writeMessage(message);
		return new ResponseEntity<>(message,(newMessage.isPresent())?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	
}
