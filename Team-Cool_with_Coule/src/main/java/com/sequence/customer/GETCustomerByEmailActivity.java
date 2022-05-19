package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;

import javax.inject.Inject;
import java.util.List;

/**
 * The purpose of this class is to handle the request to get a customer by their email address from the database
 */
public class GETCustomerByEmailActivity implements RequestHandler<GETCustomerByEmailRequest, GETCustomerByEmailResult> {
    private final CustomerDao dao;

    /**
     * Instantiate a new Get Customer By Email Activity
     * @param dao - the customer data access object that we are using to locate the customer
     */
    @Inject
    public GETCustomerByEmailActivity(CustomerDao dao) {
        this.dao = dao;
    }

    /**
     * The request handler will get a customer by their email from the database if the customer is found
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return - a new GetCustomerByEmailResult
     */
    @Override
    public GETCustomerByEmailResult handleRequest(GETCustomerByEmailRequest input, Context context) {
        Customer customer = dao.getCustomer(input.getEmail(), input.getPassword());
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        ResponseStatus status = new ResponseStatus(200, "Customer email found");
        return new GETCustomerByEmailResult(new CustomerModel(customer), status);
    }
}
