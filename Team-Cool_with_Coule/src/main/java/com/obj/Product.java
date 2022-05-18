package main.java.com.obj;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Setter

@DynamoDBTable(tableName = "Cool-With-Coule-Product")
public class Product {
    public static final String TYPE_INDEX = "type-index";

    private String name;

    private String type;

    private String upcCode;

    private Integer quantity;

    private String description;

    private Long priceInCents;

    private String imageUrl;

    @DynamoDBHashKey(attributeName = "name")
    public String getName() {
        return name;
    }

    @DynamoDBIndexHashKey(attributeName = "type", globalSecondaryIndexNames = TYPE_INDEX)
    public String getType() {
        return type;
    }

    @DynamoDBAttribute(attributeName = "upcCode")
    public String getUpcCode() {
        return upcCode;
    }

    @DynamoDBAttribute(attributeName = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    @DynamoDBAttribute(attributeName = "priceInCents")
    public Long getPriceInCents() {
        return priceInCents;
    }

    @DynamoDBAttribute(attributeName = "imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

}
