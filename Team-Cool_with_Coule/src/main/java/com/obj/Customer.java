package main.java.com.obj;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import javax.xml.stream.Location;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

@DynamoDBTable(tableName = "Cool-With-Coule-Customers")
public class Customer {

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "customerId")
    @DynamoDBAttribute(attributeName = "customerId")
    private String customerId;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBIndexHashKey(attributeName = "email")
    @DynamoDBAttribute(attributeName = "email")
    private String email;
    @DynamoDBRangeKey(attributeName = "password")
    @DynamoDBAttribute(attributeName = "password")
    private String password;
    @DynamoDBAttribute(attributeName = "location")
    private Location location;
    @DynamoDBAttribute(attributeName = "historyOrderIds")
    private List<String> historyOrderIds;
    @DynamoDBAttribute(attributeName = "isAdmin")
    private boolean isAdmin;






}
