package web.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SQSConsumer {

    private static AmazonSQS client = null;
    public List<Message> consume(String queue){
        createClient();
        GetQueueUrlResult response = client.getQueueUrl("test.fifo");
        String url = response.getQueueUrl();
        List<Message> messages = client.receiveMessage(url).getMessages();
        for(Message message: messages){
            client.deleteMessage(url, message.getReceiptHandle());
        }
        return messages;
    }

    public void createClient(){
        if (client == null){
            client = AmazonSQSClientBuilder.standard().build();
        }
    }
}
