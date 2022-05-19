package main.java.com.obj;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import main.java.com.obj.model.CustomerModel;

import java.util.Date;
import java.util.List;

import lombok.*;
//import main.java.com.obj.model.CustomerModel;

@Setter

@DynamoDBTable(tableName = "Cool-With-Coule-Order")
public class Order {
    private String orderId;
    private String customerId;
    private List<String> productNames;
    private Date orderDate;

    @DynamoDBHashKey(attributeName = "orderId")
    public String getOrderId() {return orderId;}

    @DynamoDBAttribute(attributeName = "productNames")
    public List<String> getProductNames() {return productNames;}

    @DynamoDBAttribute(attributeName = "orderDate")
    public Date getOrderDate() {return this.orderDate;}

    @DynamoDBAttribute(attributeName = "customerId")
    public String getCustomerId() {return this.customerId;}

}

