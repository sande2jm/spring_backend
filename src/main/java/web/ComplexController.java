package web;


import com.amazonaws.services.sqs.model.Message;
import web.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.service.DynamoService;
import web.service.SQSConsumer;
import web.service.SQSPublisher;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/complex")
public class ComplexController {
    @Autowired
    DataService dataService;
    @Autowired
    DynamoService dynamoService;
    @Autowired
    SQSPublisher sqsPublisher;
    @Autowired
    SQSConsumer sqsConsumer;

    @RequestMapping(value = "/requestMapping",method = RequestMethod.GET, produces = "application/json")
    public Map<String,String> map_request() {
        return dataService.getData();
    }

    @RequestMapping(value = "/add_user",method = RequestMethod.GET)
    public void add_user(){
        dynamoService.insertItem();
    }

    @RequestMapping(value = "/remove_user",method = RequestMethod.GET)
    public void remove_user(){
        dynamoService.deleteItem();
    }

    @RequestMapping(value = "/publish_sqs",method = RequestMethod.GET)
    public void sqs_test_publish(){
        sqsPublisher.publish("test.fifo", "test_message");
    }

    @RequestMapping(value = "/consume_sqs",method = RequestMethod.GET, produces = "application/json")
    public List<Message> sqs_test_consume(){
        sqsPublisher.publish("test.fifo", "test_message");
        List<Message> messages = sqsConsumer.consume("test.fifo");
        return messages;
    }



    }
