package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class POSTCustomerActivity implements RequestHandler<POSTCustomerRequest, POSTCustomerResult> {
    private final CustomerDao dao;

    /**
     * Instantiate a new customer.
     * @param dao The customer dao toi access the database
     */
    @Inject
    public POSTCustomerActivity(CustomerDao dao) {
        this.dao = dao;
    }

    /**
     * Creates a new customer in the database.
     * @param customerRequest the info of the customer to add
     *
     * @return result object containing
     */
    @Override
    public POSTCustomerResult handleRequest(POSTCustomerRequest customerRequest, Context context) {


        new Customer();
        Customer customer = new Customer(customerRequest.getCustomerId(), customerRequest.getName(), customerRequest.getEmail(),
                customerRequest.getPassword(), customerRequest.getLocation(), new ArrayList<>(), customerRequest.isAdmin());

        dao.saveCustomer(customer);

        return POSTCustomerResult.builder()
                .customerModel(new CustomerModel(customer))
                .build();
    }

}
