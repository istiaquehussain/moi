package com.coe.moi.core.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amazonaws.ClientConfiguration;
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

@Service
public class SQServiceImpl implements SQService {
	//final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
	BasicAWSCredentials awsCreds = new BasicAWSCredentials("PP", "LLL");
	Regions region = Regions.EU_WEST_1;
	final AmazonSQS sqs=createSqsClient(awsCreds, region);
	final String queueUrl="http://www.cosj/pql";
	@Override
	public Optional<QueMessage> readMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QueMessage> readMessages() {
		// receive messages from the queue
        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        List<QueMessage> returnMessage= new ArrayList<QueMessage>();
        // delete messages from the queue
        messages.stream().forEach(message->
        		{
        			sqs.deleteMessage(queueUrl, message.getReceiptHandle());
        			try {
						returnMessage.add(QueMessageMapper.getQueMessageMapper().getObjectMessage(message.getBody()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		);
        
		return returnMessage;
	}

	@Override
	public Optional<QueMessage> writeMessage(QueMessage message) {
		QueMessage returnMessage =null;
		try {
			String msg = QueMessageMapper.getQueMessageMapper().getStringMessage(message);
			SendMessageRequest send_msg_request = new SendMessageRequest()
			        .withQueueUrl(queueUrl)
			        .withMessageBody(msg)
			        .withDelaySeconds(5);
			sqs.sendMessage(send_msg_request);
			returnMessage=message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.of(returnMessage);
	}
	
	public void XX()
	{
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");
		Regions region = Regions.EU_WEST_1;
		
	}
	public static AmazonSQS createSqsClient(AWSCredentials credentials, Regions region) {
		  return AmazonSQSClientBuilder //
		      .standard() //
		      .withCredentials(new AWSStaticCredentialsProvider(credentials)) //
		      .withRegion(region) //
		      .build();
		}

}
