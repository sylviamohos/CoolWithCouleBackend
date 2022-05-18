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

public class GETCustomerByIdActivity implements RequestHandler<GETCustomerByIdRequest, GETCustomerByIdResult> {
    private final CustomerDao dao;

    @Inject
    public GETCustomerByIdActivity(CustomerDao dao) {
        this.dao = dao;
    }

    @Override
    public GETCustomerByIdResult handleRequest(GETCustomerByIdRequest input, Context context) {
        List<Customer> customerList = dao.getCustomerById(input.getCustomerId());
        if (customerList.size() == 0) {
            throw new CustomerNotFoundException();
        }
        Customer customer = customerList.get(0);
        ResponseStatus status = new ResponseStatus(200, "Customer Id found");
        return new GETCustomerByIdResult(new CustomerModel(customer), status);
    }
}
