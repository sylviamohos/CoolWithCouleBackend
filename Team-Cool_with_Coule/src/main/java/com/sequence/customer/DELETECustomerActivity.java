package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.obj.Customer;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.sequence.sample.DELETESampleRequest;
import main.java.com.sequence.sample.DELETESampleResult;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class DELETECustomerActivity implements RequestHandler<DELETECustomerRequest, DELETECustomerResult> {
    private final CustomerDao dao;

    @Inject
    public DELETECustomerActivity(CustomerDao dao) {
        this.dao = dao;
    }

    @Override
    public DELETECustomerResult handleRequest(DELETECustomerRequest deleteCustomerRequest, Context context) {

        if (!deleteCustomerRequest.getCallingUserId.equals(deleteCustomerRequest.getCustomerId)) {
            throw new CustomerNotFoundException;
        }
        List<Customer> customerList = dao.getCustomerById(deleteCustomerRequest.getCustomerId());




        return null;
    }
}
