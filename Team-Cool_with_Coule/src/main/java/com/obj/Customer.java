package main.java.com.obj;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
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

@DynamoDBTable(tableName = "customers")
public class Customer {

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "customerId")
    private String customerId;
    private String name;
    @DynamoDBIndexHashKey(attributeName = "email")
    private String email;
    @DynamoDBRangeKey(attributeName = "password")
    private String password;
    private Location location;
    private List<String> historyOrderIds;
    private boolean isAdmin;






}
