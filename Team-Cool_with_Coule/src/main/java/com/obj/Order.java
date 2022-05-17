package main.java.com.obj;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
//import main.java.com.obj.dao.model.CustomerModel;

import java.util.ArrayList;
import java.util.Date;
import lombok.*;

@Setter

@DynamoDBTable(tableName = "Cool-With-Coule-Order")
public class Order {
    private String orderId;
    //TODO
    //private CustomerModel customerModel;
    private ArrayList<String> productNames;
    private Date orderDate;

    @DynamoDBHashKey(attributeName = "orderId")
    public String getOrderId() {return orderId;}

    @DynamoDBAttribute(attributeName = "productNames")
    public ArrayList<String> getProductNames() {return productNames;}

    @DynamoDBAttribute(attributeName = "orderDate")
    public Date getOrderDate() {return this.orderDate;}


}
