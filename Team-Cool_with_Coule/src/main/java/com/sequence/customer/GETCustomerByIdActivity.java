package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.PasswordDoesNotMatchException;
import main.java.com.obj.Customer;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;

import javax.inject.Inject;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * The purpose of this class is to handle the request to get a customer by their Id from the database
 */
public class GETCustomerByIdActivity implements RequestHandler<GETCustomerByIdRequest, GETCustomerByIdResult> {
    private final CustomerDao dao;

    /**
     * Instantiate a new Get Customer By Id Activity
     * @param dao - the customer data access object that we are using to locate the customer
     */
    @Inject
    public GETCustomerByIdActivity(CustomerDao dao) {
        this.dao = dao;
    }

    /**
     * The request handler will get a customer by their id from the database if the customer is found
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return - a new GetCustomerByIdResult
     */
    @Override
    public GETCustomerByIdResult handleRequest(GETCustomerByIdRequest input, Context context) {
        if (input.getCustomerId() == null) {
            throw new InvalidParameterException();
        }
        List<Customer> customerList = dao.getCustomerById(input.getCustomerId());
        if (customerList.size() == 0) {
            throw new CustomerNotFoundException();
        }

        Customer customer = customerList.get(0);
        ResponseStatus status = new ResponseStatus(200, "Customer Id found");
        return new GETCustomerByIdResult(new CustomerModel(customer), status);
    }
}
