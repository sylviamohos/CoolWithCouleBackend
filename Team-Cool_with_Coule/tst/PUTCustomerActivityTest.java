import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;
import main.java.com.sequence.customer.PUTCustomerActivity;
import main.java.com.sequence.customer.PUTCustomerRequest;
import main.java.com.sequence.customer.PUTCustomerResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class PUTCustomerActivityTest {
    @Mock
    private CustomerDao dao;
    @InjectMocks
    private PUTCustomerActivity putCustomerActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void handleRequest_noChanges_returnsSameCustomer() {
        // GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer customer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        PUTCustomerRequest request = new PUTCustomerRequest();
        request.setCustomerId(customer.getCustomerId());
        request.setName(customer.getName());
        request.setEmail(customer.getEmail());
        request.setPassword(customer.getPassword());
        request.setAddress(customer.getLocation().getAddress());
        request.setCity(customer.getLocation().getCity());
        request.setState(customer.getLocation().getState());
        request.setZipCode(customer.getLocation().getZipCode());

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(dao.getCustomerById(customer.getCustomerId())).thenReturn(customerList);
        doNothing().when(dao).deleteCustomer(customer);
        when(dao.saveCustomer(customer)).thenReturn(customer);

        // WHEN
        PUTCustomerResult result = putCustomerActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getCustomerModel(), new CustomerModel(customer));

    }

    @Test
    public void handleRequest_changedValues_returnsSameCustomer() {
        // GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Location changedLocation = new Location("15", "Mt. Pleasant", "TX", "12345");
        Customer customer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        Customer changedCustomer = new Customer("zulu246", "Bobby", "test@gmail.com", "shitballz", changedLocation, new ArrayList<>());
        PUTCustomerRequest request = new PUTCustomerRequest();
        request.setCustomerId(customer.getCustomerId());
        request.setName(changedCustomer.getName());
        request.setEmail(changedCustomer.getEmail());
        request.setPassword(changedCustomer.getPassword());
        request.setAddress(changedCustomer.getLocation().getAddress());
        request.setCity(changedCustomer.getLocation().getCity());
        request.setState(changedCustomer.getLocation().getState());
        request.setZipCode(changedCustomer.getLocation().getZipCode());

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(dao.getCustomerById(customer.getCustomerId())).thenReturn(customerList);
        doNothing().when(dao).deleteCustomer(customer);
        when(dao.saveCustomer(customer)).thenReturn(customer);

        // WHEN
        PUTCustomerResult result = putCustomerActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getCustomerModel(), new CustomerModel(changedCustomer));
        assertEquals(200, result.getResponseStatus().getCode());

    }

    @Test
    public void handleRequest_nonexistentId_throwsCustomerNotFoundException() {
        // GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Location changedLocation = new Location("15", "Mt. Pleasant", "TX", "12345");
        Customer customer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        Customer changedCustomer = new Customer("zulu246", "Bobby", "test@gmail.com", "shitballz", changedLocation, new ArrayList<>());
        PUTCustomerRequest request = new PUTCustomerRequest();
        request.setCustomerId(customer.getCustomerId());
        request.setName(changedCustomer.getName());
        request.setEmail(changedCustomer.getEmail());
        request.setPassword(changedCustomer.getPassword());
        request.setAddress(changedCustomer.getLocation().getAddress());
        request.setCity(changedCustomer.getLocation().getCity());
        request.setState(changedCustomer.getLocation().getState());
        request.setZipCode(changedCustomer.getLocation().getZipCode());

        List<Customer> customerList = new ArrayList<>();

        when(dao.getCustomerById(customer.getCustomerId())).thenReturn(customerList);
        doNothing().when(dao).deleteCustomer(customer);
        when(dao.saveCustomer(customer)).thenReturn(customer);

        // WHEN
        // THEN
        assertThrows(CustomerNotFoundException.class, ()-> putCustomerActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_deleteCustomer_throwsAmazonDynamoDbException() {
        // GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Location changedLocation = new Location("15", "Mt. Pleasant", "TX", "12345");
        Customer customer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        Customer changedCustomer = new Customer("zulu246", "Bobby", "test@gmail.com", "shitballz", changedLocation, new ArrayList<>());
        PUTCustomerRequest request = new PUTCustomerRequest();
        request.setCustomerId(customer.getCustomerId());
        request.setName(changedCustomer.getName());
        request.setEmail(changedCustomer.getEmail());
        request.setPassword(changedCustomer.getPassword());
        request.setAddress(changedCustomer.getLocation().getAddress());
        request.setCity(changedCustomer.getLocation().getCity());
        request.setState(changedCustomer.getLocation().getState());
        request.setZipCode(changedCustomer.getLocation().getZipCode());

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(dao.getCustomerById(customer.getCustomerId())).thenReturn(customerList);
        doThrow(new AmazonDynamoDBException("")).when(dao).deleteCustomer(customer);
        when(dao.saveCustomer(customer)).thenReturn(customer);

        // WHEN
        // THEN
        assertThrows(AmazonDynamoDBException.class, ()-> putCustomerActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_saveCustomer_throwsAmazonDynamoDbException() {
        // GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Location changedLocation = new Location("15", "Mt. Pleasant", "TX", "12345");
        Customer customer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        Customer changedCustomer = new Customer("zulu246", "Bobby", "test@gmail.com", "shitballz", changedLocation, new ArrayList<>());
        PUTCustomerRequest request = new PUTCustomerRequest();
        request.setCustomerId(customer.getCustomerId());
        request.setName(changedCustomer.getName());
        request.setEmail(changedCustomer.getEmail());
        request.setPassword(changedCustomer.getPassword());
        request.setAddress(changedCustomer.getLocation().getAddress());
        request.setCity(changedCustomer.getLocation().getCity());
        request.setState(changedCustomer.getLocation().getState());
        request.setZipCode(changedCustomer.getLocation().getZipCode());

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(dao.getCustomerById(customer.getCustomerId())).thenReturn(customerList);
        doThrow(new AmazonDynamoDBException("")).when(dao).deleteCustomer(customer);
        when(dao.saveCustomer(customer)).thenThrow(new AmazonDynamoDBException(""));

        // WHEN
        // THEN
        assertThrows(AmazonDynamoDBException.class, ()-> putCustomerActivity.handleRequest(request, null));
    }
}
