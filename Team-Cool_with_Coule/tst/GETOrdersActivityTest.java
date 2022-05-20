
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.Order;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.model.OrderModel;
import main.java.com.sequence.order.GETOrdersActivity;
import main.java.com.sequence.order.GETOrdersRequest;
import main.java.com.sequence.order.GETOrdersResult;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GETOrdersActivityTest {

    @InjectMocks
    GETOrdersActivity getOrdersActivity;
    @Mock
    private OrderDao orderDao;
    @Mock
    private CustomerDao customerDao;
    private Order order1, order2, order3, order4;
    private String orderId = "id01";
    private String orderId2 = "id02";
    private Customer customer1;

    private List<String> customer1HistoryOrderIds;

    @BeforeEach
    private void setup() {
        openMocks(this);
        getOrdersActivity = new GETOrdersActivity(orderDao, customerDao);

        order1 = order2 = order3 = order4 = new Order();

        customer1HistoryOrderIds = Arrays.asList("id01", "iod012", "iod0123");

        customer1 = new Customer("customer1","first last", "email.com",
                "password", new Location(), customer1HistoryOrderIds);

        order1.setOrderId(orderId);
        order1.setOrderDate(new Date());
        order1.setProductNames(Arrays.asList("red apron", "blue apron"));
        order1.setCustomerId("customer1");

        order2.setOrderId(orderId+1);
        order2.setOrderDate(new Date());
        order2.setProductNames(Arrays.asList("white towel"));
        order2.setCustomerId("customer1");

        order3.setOrderId(orderId+2);
        order3.setOrderDate(new Date());
        order3.setProductNames(Arrays.asList("funny mitten", "funny towel", "funny something else"));
        order3.setCustomerId("customer1");

        order4.setOrderId(orderId2);
        order4.setOrderDate(new Date());
        order4.setProductNames(Arrays.asList("something thats not even related"));
        order4.setCustomerId("customer2");
    }

    @Test
    public void handleRequest_ValidCustomerWithThreeOrders_ReturnsAllOrders() {

        // GIVEN
        String requestedCustomerId = "customer1";
        List<String> customerOrderIds = Arrays.asList(orderId, orderId+1, orderId+2);

        when(customerDao.getCustomerById(requestedCustomerId)).thenReturn(Arrays.asList(customer1));
        when(orderDao.getOrders(customerOrderIds)).thenReturn(Arrays.asList(order1, order2, order3));

        // WHEN
        GETOrdersResult result = getOrdersActivity.handleRequest(new GETOrdersRequest("customer1"), null);

        List<Order> customerOrders = new ArrayList<>();
        for (OrderModel orderModel : result.getOrderModels()) {
            Order order = new Order();
            order.setOrderId(orderModel.getOrderId());
            order.setOrderDate(orderModel.getOrderDate());
            order.setCustomerId(orderModel.getCustomerId());
            order.setProductNames(orderModel.getProductNames());

            customerOrders.add(order);
        }

        // THEN
        assertTrue(200 == result.getResponseStatus().getCode());
        assertTrue(result.getOrderModels().equals(customerOrders));
    }

    @Test
    public void handleRequest_InvalidCustomer_ThrowsCustomerNotFoundException() {
        //GIVEN
        String invalidCustomer = "invalid";

        when(customerDao.getCustomerById(invalidCustomer)).thenThrow(new CustomerNotFoundException());
        when(orderDao.getOrders(null)).thenThrow(new OrderNotFoundException());

        // WHEN
        GETOrdersResult getOrdersResult = getOrdersActivity.handleRequest(new GETOrdersRequest(invalidCustomer), null);

        // THEN


    }
}
