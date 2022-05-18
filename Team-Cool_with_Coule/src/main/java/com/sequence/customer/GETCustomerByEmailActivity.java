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

public class GETCustomerByEmailActivity implements RequestHandler<GETCustomerByEmailRequest, GETCustomerByEmailResult> {
    private final CustomerDao dao;

    @Inject
    public GETCustomerByEmailActivity(CustomerDao dao) {
        this.dao = dao;
    }
    
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
