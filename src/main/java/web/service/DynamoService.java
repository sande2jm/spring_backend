package web.service;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DynamoService {
    private static AmazonDynamoDB client = null;

    public void insertItem(){
        createClient();
        Table table = new Table(client, "test");
        Item item = new Item().with("id", "123412341").with("name","jacob").with("IQ", "75");
        table.putItem(item);
    }

    public void deleteItem(){
        createClient();
        Table table = new Table(client, "test");
        Item item = new Item().with("id", "123412341").with("name","jacob").with("IQ", "75");
        table.deleteItem(new PrimaryKey().addComponent("id", "123412341"));
    }

    public void createClient(){
        if (client == null){
            client = AmazonDynamoDBClientBuilder.standard().build();
        }
    }



}
