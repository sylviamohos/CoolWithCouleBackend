package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.obj.Customer;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;
import main.java.com.sequence.sample.DELETESampleRequest;
import main.java.com.sequence.sample.DELETESampleResult;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.sequence.customer.DELETECustomerRequest;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is responsible for deleting a customer from the database
 */
public class DELETECustomerActivity implements RequestHandler<DELETECustomerRequest, DELETECustomerResult> {
    private final CustomerDao dao;

    /**
     * Instantiate a new Delete Customer Activity
     * @param dao - the customer data access object that we are using to locate the customer to be deleted
     */
    @Inject
    public DELETECustomerActivity(CustomerDao dao) {
        this.dao = dao;
    }

    /**
     * The request handler method will delete a customer from the database if the customer's id is located
     * @param deleteCustomerRequest The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return - a new DeleteCustomerResult
     */
    @Override
    public DELETECustomerResult handleRequest(DELETECustomerRequest deleteCustomerRequest, Context context) {

        /* TODO: implement if Administrator functionality is added:
         if (!deleteCustomerRequest.getCallingUserId().equals(deleteCustomerRequest.getCustomerId())) {
            throw new RuntimeException();
        }
         */

        List<Customer> customerList = dao.getCustomerById(deleteCustomerRequest.getCustomerId());
        if(customerList.size() == 0) {
            throw new CustomerNotFoundException();
        }
        Customer customer = customerList.get(0);
        dao.deleteCustomer(customer);
        ResponseStatus status = new ResponseStatus(200, "Customer deleted");
        return new DELETECustomerResult(new CustomerModel(customer), status);
    }
}
