package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import main.java.com.obj.Customer;

import javax.inject.Inject;

public class CustomerDao {

    private final DynamoDBMapper mapper;

    @Inject
    public CustomerDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }
}
