package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import main.java.com.obj.Customer;
import org.checkerframework.checker.units.qual.A;

import javax.inject.Inject;
import java.beans.Expression;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

public class CustomerDao {

    private final DynamoDBMapper mapper;

    @Inject
    public CustomerDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }



    public Customer getCustomer(String email) {
        Customer customer = this.mapper.load(Customer.class, email);

        return customer;

        }

    public List<Customer> getCustomerById(String id) {
        Customer customer = new Customer();
        customer.setCustomerId(id);

        DynamoDBQueryExpression<Customer> queryExpression = new DynamoDBQueryExpression<Customer>()
                .withHashKeyValues(customer)
                .withConsistentRead(false)
                .withIndexName(Customer.ID_INDEX);

        return new ArrayList<>(mapper.query(Customer.class, queryExpression));

    }

    public Customer saveCustomer(Customer customer) {
        mapper.save(customer);
        return customer;
    }

    public void deleteCustomer(Customer customer) {
        mapper.delete(customer);
    }
}
