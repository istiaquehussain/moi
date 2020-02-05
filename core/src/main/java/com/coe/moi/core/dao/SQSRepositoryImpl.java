package com.coe.moi.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.coe.moi.core.entity.QueMessage;
import com.coe.moi.core.util.QueMessageMapper;

@Repository
public class SQSRepositoryImpl implements SQSRepository{
	@Value("${aws.iam.user.accessKey}")
	//@Value("${spring.profiles.active}")
	String accessKey;
	@Value("${aws.iam.user.secretKey}")
	String secretKey;
	@Value("${aws.sqs.queueUrl}")
	String queueUrl;
	@Value("${aws.sqs.delayInSeconds}")
	Integer delayInSeconds;
	
	//Regions region = Regions.EU_WEST_1;
	
	@Value("${aws.region}")
	Regions region;
	
	
	BasicAWSCredentials awsCreds = null;
	AmazonSQS sqs =null;
	
	private BasicAWSCredentials createAwsCredential(String accessKey, String secretKey)
	{
		if(awsCreds==null)
			awsCreds=new BasicAWSCredentials(accessKey, secretKey);
		return awsCreds;
	}
	private AmazonSQS createSqsClient(AWSCredentials credentials, Regions region) {
		  
		if(sqs==null)
		 sqs= AmazonSQSClientBuilder 
		      .standard() 
		      .withCredentials(new AWSStaticCredentialsProvider(credentials)) 
		      .withRegion(region) 
		      .build();
		return sqs;
		}
	
	@PostConstruct
	private void setSQS()
	{
		createSqsClient(createAwsCredential(accessKey,secretKey),region);
	}
	
	public List<Message> receiveMessage() {
		//if(sqs==null)
		//	createSqsClient(createAwsCredential(accessKey,secretKey),region);
		List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        return messages;
	}
	
	public List<Message> receiveAndRemoveMessage() {
		//if(sqs==null)
		//	createSqsClient(createAwsCredential(accessKey,secretKey),region);
		List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
		messages.stream().forEach(message->sqs.deleteMessage(queueUrl, message.getReceiptHandle()));
		return messages;
	}
	
	@Override
	public List<Message> readMessagesForId(String Id) {
		List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
		
		List<Message> userMessages = messages.stream().filter(message-> {
				String authId ="";
				try {
					 authId=QueMessageMapper.getQueMessageMapper().getObjectMessage(message.getBody()).getAuthId();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			
			return (authId!=null)&& authId.equals(Id);
		}
		).collect(Collectors.toList());
		
		userMessages.stream().forEach(message->sqs.deleteMessage(queueUrl, message.getReceiptHandle()));
		
		
		return userMessages;
	}
	
	
	public Optional<String> writeMessage(String message) {
		//if(sqs==null)
		//	createSqsClient(createAwsCredential(accessKey,secretKey),region);
		String returnMessage =null;
		try {
			SendMessageRequest send_msg_request = new SendMessageRequest()
			        .withQueueUrl(queueUrl)
			        .withMessageBody(message)
			        .withDelaySeconds(delayInSeconds);
			sqs.sendMessage(send_msg_request);
			returnMessage=message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.of(returnMessage);
	}
}
