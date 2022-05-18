package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;

import javax.inject.Inject;

public class GETCustomerByIdActivity {
    private final CustomerDao dao;

    @Inject
    public GETCustomerByIdActivity(CustomerDao dao) {
        this.dao = dao;
    }

    public GETCustomerByIdResult handleRequest(GETCustomerByIdRequest input, Context context) {
        Customer customer = dao.getCustomerById();
    }
}



    public GETCustomerByEmailResult handleRequest(GETCustomerByEmailRequest input, Context context) {
        Customer customer = dao.getCustomer(input.getEmail(), input.getPassword());
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        ResponseStatus status = new ResponseStatus(200, "Customer email found");
        return new GETCustomerByEmailResult(new CustomerModel(customer), status);
    }