package main.java.com.obj.models;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.*;
import main.java.com.obj.ResponseStatus;

import javax.xml.stream.Location;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CustomerModel {

    private String customerId;
    private String name;
    private String email;
    private Location location;
    private List<String> historyOrderIds;
    private ResponseStatus responseStatus;

}
