import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;
import main.java.com.sequence.customer.DELETECustomerActivity;
import main.java.com.sequence.customer.DELETECustomerRequest;
import main.java.com.sequence.customer.DELETECustomerResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class DELETECustomerActivityTest {

    @Mock
    private CustomerDao dao;
    @InjectMocks
    private DELETECustomerActivity deleteCustomerActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer goodCustomer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        Customer badCustomer = new Customer("benWhiskers", "Ben", "wizardsRUS@gmail.com", "thecoulest", location, new ArrayList<>());
        ResponseStatus responseStatus = new ResponseStatus(200, "");
        ResponseStatus responseStatus1 = new ResponseStatus(400, "");
        ResponseStatus responseStatus2 = new ResponseStatus(500, "");
    }

    @Test
    public void handleRequest_customerFoundById_returnsDeleteCustomerResult() {
        // GIVEN
        List<Customer> customerList = new ArrayList<>();
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer goodCustomer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        customerList.add(goodCustomer);
        DELETECustomerRequest request = new DELETECustomerRequest(goodCustomer.getCustomerId());
        when(dao.getCustomerById(goodCustomer.getCustomerId())).thenReturn(customerList);
        doNothing().when(dao).deleteCustomer(goodCustomer);
        // WHEN
        DELETECustomerResult result = deleteCustomerActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getCustomerModel(), new CustomerModel(goodCustomer));

    }

    @Test
    public void handleRequest_customerNotFoundById_throwsCustomerNotFoundException() {
        // GIVEN
        List<Customer> customerList = new ArrayList<>();
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer goodCustomer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        customerList.add(goodCustomer);
        DELETECustomerRequest request = new DELETECustomerRequest(goodCustomer.getCustomerId());
        when(dao.getCustomerById(goodCustomer.getCustomerId())).thenReturn(new ArrayList<>());
        doNothing().when(dao).deleteCustomer(goodCustomer);
        // WHEN & THEN
        assertThrows(CustomerNotFoundException.class, () -> deleteCustomerActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_failedToDelete_throwsAmazonDynamoDBException() {
        // GIVEN
        List<Customer> customerList = new ArrayList<>();
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer goodCustomer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        customerList.add(goodCustomer);
        DELETECustomerRequest request = new DELETECustomerRequest(goodCustomer.getCustomerId());
        when(dao.getCustomerById(goodCustomer.getCustomerId())).thenReturn(customerList);
        doThrow(new AmazonDynamoDBException("")).when(dao).deleteCustomer(goodCustomer);
        // WHEN & THEN
        assertThrows(AmazonDynamoDBException.class, () -> deleteCustomerActivity.handleRequest(request, null));
    }
}
