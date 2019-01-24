package web.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.stereotype.Component;

@Component
public class SQSPublisher {
    private static AmazonSQS client = null;
    public void publish(String queue, String message){
        createClient();
        GetQueueUrlResult response = client.getQueueUrl(queue);
        String url = response.getQueueUrl();
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(url)
                .withMessageBody(message)
                .withMessageGroupId("1");
        client.sendMessage(send_msg_request);
    }

    public void createClient(){
        if (client == null){
            client = AmazonSQSClientBuilder.standard().build();
        }
    }
}
