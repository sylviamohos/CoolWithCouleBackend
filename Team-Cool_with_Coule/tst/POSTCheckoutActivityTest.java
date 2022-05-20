
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.Product;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.order.POSTCheckoutActivity;
import main.java.com.sequence.order.POSTCheckoutRequest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class POSTCheckoutActivityTest {

    @InjectMocks
    private POSTCheckoutActivity postCheckoutActivity;

    @Inject
    private CustomerDao customerDao;

    @Inject
    private OrderDao orderDao;

    @Inject
    private ProductDao productDao;

    private String customerId;

    private Map<String, Integer> customerCart;
   // private Map<String, >
    private Customer customer1;

    private Product apron, apron2, towel, mitten;

    @BeforeEach
    private void setup() {
        openMocks(this);
        postCheckoutActivity = new POSTCheckoutActivity(orderDao, customerDao, productDao);

        apron = apron2 = towel = mitten = new Product();

        customer1 = new Customer("customer1", "name", "email",
                "password", new Location(), Arrays.asList("id01", "id02", "id03"));

        customerCart = new HashMap<>();
        customerCart.put("red apron", 2);
        customerCart.put("bear mitten", 1);

        Product apron = new Product();
        apron.setName("red apron");
        apron.setType("apron");
        apron.setQuantity(10);

        Product apron2 = new Product();
        apron2.setName("funny apron");
        apron2.setType("apron");
        apron2.setQuantity(2);

        Product towel = new Product();
        towel.setName("green towel");
        towel.setType("towel");
        towel.setQuantity(5);

        Product mitten = new Product();
        mitten.setName("bear mitten");
        mitten.setType("mitten");
        mitten.setQuantity(3);


    }

    @Test
    public void handleRequest_PostCheckoutWithValid_ReturnsPostCheckoutResult() {
        // GIVEN
        String requestedId = "customer1";
        POSTCheckoutRequest request = new POSTCheckoutRequest(requestedId, null);

        when(customerDao.getCustomerById(requestedId)).thenReturn(Arrays.asList(customer1));
        when(productDao.getProducts(Arrays.asList("red apron", "funny apron"))).thenReturn(Arrays.asList(apron, apron2));

        // WHEN
        postCheckoutActivity.handleRequest(request, null);;

        // THEN

    }



}
