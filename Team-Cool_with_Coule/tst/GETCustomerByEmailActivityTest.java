import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;
import main.java.com.sequence.customer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GETCustomerByEmailActivityTest {

    @Mock
    private CustomerDao dao;

    @InjectMocks
    private GETCustomerByEmailActivity getCustomerByEmailActivity;

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
    public void handleRequest_customerEmailFound_returnsGetCustomerByEmailResult() {
        // GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer goodCustomer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        GETCustomerByEmailRequest request = new GETCustomerByEmailRequest(goodCustomer.getEmail(), goodCustomer.getPassword());
        when(dao.getCustomer(goodCustomer.getEmail(), goodCustomer.getPassword())).thenReturn(goodCustomer);
        // WHEN
        GETCustomerByEmailResult result = getCustomerByEmailActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getCustomerModel(), new CustomerModel(goodCustomer));


    }

    @Test
    public void handleRequest_customerEmailNotFound_throwsCustomerNotFoundException() {
        //GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer goodCustomer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        GETCustomerByEmailRequest request = new GETCustomerByEmailRequest(goodCustomer.getEmail(), goodCustomer.getPassword());
        when(dao.getCustomer(goodCustomer.getEmail(), goodCustomer.getPassword())).thenThrow(new CustomerNotFoundException());
        // WHEN & THEN
        assertThrows(CustomerNotFoundException.class, () -> getCustomerByEmailActivity.handleRequest(request, null));

    }

    @Test
    public void handleRequest_failedToRetrieveCustomerByEmail_throwsAmazonDynamoDBException() {
        // GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer goodCustomer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        GETCustomerByEmailRequest request = new GETCustomerByEmailRequest(goodCustomer.getEmail(), goodCustomer.getPassword());
        when(dao.getCustomer(goodCustomer.getEmail(), goodCustomer.getPassword())).thenReturn(goodCustomer);
        doThrow(new AmazonDynamoDBException("")).when(dao).getCustomer(goodCustomer.getEmail(), goodCustomer.getPassword());
        // WHEN & THEN
        assertThrows(AmazonDynamoDBException.class, () -> getCustomerByEmailActivity.handleRequest(request, null));

    }

}
