
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.OutOfStockException;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.Product;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.order.POSTCheckoutActivity;
import main.java.com.sequence.order.POSTCheckoutRequest;
import main.java.com.sequence.order.POSTCheckoutResult;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class POSTCheckoutActivityTest {

    @InjectMocks
    private POSTCheckoutActivity postCheckoutActivity;

    @Mock
    private CustomerDao customerDao;

    @Mock
    private OrderDao orderDao;

    @Mock
    private ProductDao productDao;

    private Map<String, Integer> customerCart;

    private Map<String, Integer> database;

    private Customer customer1;

    private Product apron, apron2, towel, mitten;

    @BeforeEach
    private void setup() {
        openMocks(this);
        postCheckoutActivity = new POSTCheckoutActivity(orderDao, customerDao, productDao);

        customer1 = new Customer("customer1", "name", "email",
                "password", new Location(), Arrays.asList("id01", "id02", "id03"));

        customerCart = new HashMap<>();
        customerCart.put("red apron", 2);
        customerCart.put("funny apron", 1);

        apron = new Product();
        apron.setName("red apron");
        apron.setType("apron");
        apron.setQuantity(10);

        apron2 = new Product();
        apron2.setName("funny apron");
        apron2.setType("apron");
        apron2.setQuantity(2);

        towel = new Product();
        towel.setName("green towel");
        towel.setType("towel");
        towel.setQuantity(5);

        mitten = new Product();
        mitten.setName("bear mitten");
        mitten.setType("mitten");
        mitten.setQuantity(3);

    }

    @Test
    public void handleRequest_PostCheckoutWithValid_ReturnsPostCheckoutResult() {
        // GIVEN
        String requestedId = "customer1";

        POSTCheckoutRequest request = new POSTCheckoutRequest(requestedId, customerCart);

        when(customerDao.getCustomerById(requestedId)).thenReturn(Arrays.asList(customer1));
        when(productDao.getProducts(Arrays.asList("red apron", "funny apron"))).thenReturn(Arrays.asList(apron, apron2));

        // WHEN
        POSTCheckoutResult result = postCheckoutActivity.handleRequest(request, null);

        // THEN
        assertEquals(200, result.getResponseStatus().getCode(), String.format("response code: {%d} isn't 200", result.getResponseStatus().getCode()));
        assertEquals(requestedId, result.getOrderModel().getCustomerId(), String.format("customer id: {%s} != {%s}", result.getOrderModel().getCustomerId(), requestedId));
    }

    @Test
    public void handleRequest_PostCheckoutWithInvalidCustomerId_ThrowsCustomerNotFound() {
        // GIVEN
        String requestedId = "invalid";
        POSTCheckoutRequest request = new POSTCheckoutRequest(requestedId, customerCart);

        when(customerDao.getCustomerById(requestedId)).thenThrow(new CustomerNotFoundException());

        // THEN + WHEN
        assertThrows(CustomerNotFoundException.class, () -> postCheckoutActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_PostCheckoutWithInvalidAmount_ThrowsOutOfStock() {
        // GIVEN
        String requestedId = "customer1";
        POSTCheckoutRequest request = new POSTCheckoutRequest(requestedId, customerCart);
        customerCart.put("red apron", 50);

        when(customerDao.getCustomerById(requestedId)).thenReturn(Arrays.asList(customer1));
        when(productDao.getProducts(Arrays.asList("red apron", "funny apron"))).thenReturn(Arrays.asList(apron, apron2));

        // THEN + WHEN
        assertThrows(OutOfStockException.class, () -> postCheckoutActivity.handleRequest(request, null));

    }


}
