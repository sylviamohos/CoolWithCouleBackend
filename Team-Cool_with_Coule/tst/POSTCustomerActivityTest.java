import main.java.com.exception.CustomerAlreadyExistsException;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.model.CustomerModel;
import main.java.com.sequence.customer.GETCustomerByEmailRequest;
import main.java.com.sequence.customer.POSTCustomerActivity;
import main.java.com.sequence.customer.POSTCustomerRequest;
import main.java.com.sequence.customer.POSTCustomerResult;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POSTCustomerActivityTest {

    @Mock
    private CustomerDao dao;


    @InjectMocks
    private POSTCustomerActivity postCustomerActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    // This test checks to see that a customer is created and saved in the dao
    @Test
    public void handleRequest_newlyCreatedCustomerIsSaved_returnsPostCustomerResult() {
        // GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer customer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        POSTCustomerRequest request = POSTCustomerRequest.builder().address("12").city("Vernon").email("zuluzuzu@gmail.com").name("Zeus").password("fetchBonez123").state("WA").zipcode("23456").build();
        when(dao.saveCustomer(customer)).thenReturn(customer);
        // WHEN
        POSTCustomerResult result = postCustomerActivity.handleRequest(request, null);

        //THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getCustomerModel().getName(), customer.getName());
        assertEquals(result.getCustomerModel().getEmail(), customer.getEmail());
        assertEquals(result.getCustomerModel().getLocation(), customer.getLocation());


    }

    // This test verifies that a newly created customer does not already exist
    @Test
    public void handleRequest_customerAlreadyExists_throwsCustomerAlreadyExistsException() {
        //GIVEN
        Location location = new Location("12", "Vernon", "WA", "23456" );
        Customer customer = new Customer("zulu246", "Zeus", "zuluzuzu@gmail.com", "fetchBonez123", location, new ArrayList<>());
        POSTCustomerRequest request = POSTCustomerRequest.builder().address("12").city("Vernon").email("zuluzuzu@gmail.com").name("Zeus").password("fetchBonez123").state("WA").zipcode("23456").build();
        when(dao.getCustomer(customer.getEmail())).thenReturn(customer);
        when(dao.saveCustomer(customer)).thenReturn(customer);

        // WHEN & THEN
        assertThrows(CustomerAlreadyExistsException.class, () -> postCustomerActivity.handleRequest(request, null));

    }
}
