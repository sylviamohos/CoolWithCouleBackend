package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.Data;
import main.java.com.exception.CustomerAlreadyExistsException;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class POSTCustomerActivity implements RequestHandler<POSTCustomerRequest, POSTCustomerResult> {
    private final CustomerDao dao;
    private ResponseStatus responseStatus;

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

        for (Field f : customerRequest.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.get(customerRequest) == null) {
                    throw new InvalidParameterException();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Customer customer = dao.getCustomer(customerRequest.getEmail());

        if(customer != null) {
            throw new CustomerAlreadyExistsException();
        }
        customer = new Customer(UUID.randomUUID().toString(), customerRequest.getName(), customerRequest.getEmail(),
                customerRequest.getPassword(), new Location(customerRequest.getAddress(), customerRequest.getCity(),
                customerRequest.getState(), customerRequest.getZipcode()), new ArrayList<>());

        dao.saveCustomer(customer);
        responseStatus = new ResponseStatus(200, "Welcome Coule person.");

        return POSTCustomerResult.builder()
                .customerModel(new CustomerModel(customer))
                .responseStatus(responseStatus)
                .build();

    }

}
