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

    public static final String ID_INDEX = "idIndex";

    @DynamoDBIndexHashKey(attributeName = "customerId", globalSecondaryIndexNames = ID_INDEX)
    private String customerId;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBHashKey(attributeName = "email")
    private String email;

    @DynamoDBRangeKey(attributeName = "password")
    private String password;

    @DynamoDBAttribute(attributeName = "location")
    private Location location;

    @DynamoDBAttribute(attributeName = "historyOrderIds")
    private List<String> historyOrderIds;

    @DynamoDBAttribute(attributeName = "isAdmin")
    private boolean isAdmin;






}
