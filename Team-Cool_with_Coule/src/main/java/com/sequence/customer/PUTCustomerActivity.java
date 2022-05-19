package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class PUTCustomerActivity implements RequestHandler<PUTCustomerRequest, PUTCustomerResult> {
    private final CustomerDao dao;

    @Inject
    public PUTCustomerActivity(CustomerDao dao) {
        this.dao = dao;
    }
    @Override
    public PUTCustomerResult handleRequest(PUTCustomerRequest input, Context context) {
        List<Customer> customers = dao.getCustomerById(input.getCustomerId());
        if (customers.size() == 0) {
            throw new CustomerNotFoundException();
        }
        Customer customer = customers.get(0);

        Location location = customer.getLocation();
        location.setAddress(Optional.ofNullable(input.getAddress()).orElse(location.getAddress()));
        location.setCity(Optional.ofNullable(input.getCity()).orElse(location.getCity()));
        location.setState(Optional.ofNullable(input.getState()).orElse(location.getState()));
        location.setZipCode(Optional.ofNullable(input.getZipCode()).orElse(location.getZipCode()));

        customer.setName(Optional.ofNullable(input.getName()).orElse(customer.getName()));
        if (input.getEmail() != null || input.getPassword() != null) {
            dao.deleteCustomer(customer);
            customer.setEmail(Optional.ofNullable(input.getEmail()).orElse(customer.getEmail()));
            customer.setPassword(Optional.ofNullable(input.getPassword()).orElse(customer.getPassword()));
        }
        customer.setLocation(location);

        dao.saveCustomer(customer);
        ResponseStatus status = new ResponseStatus(200, "Success");
        return new PUTCustomerResult(new CustomerModel(customer), status);
    }
}
