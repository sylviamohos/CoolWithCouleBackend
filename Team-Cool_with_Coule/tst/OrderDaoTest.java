
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.Order;
import main.java.com.obj.dao.OrderDao;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import javax.inject.Inject;
import java.util.*;

public class OrderDaoTest {

    @InjectMocks
    private OrderDao orderDao;

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private Order expectedOrder;
    private Customer customer1;

    @BeforeEach
    public void setup() {
        openMocks(this);
        expectedOrder = new Order();
        expectedOrder.setOrderId("orderId01");
        expectedOrder.setOrderDate(new Date());
        expectedOrder.setProductNames(Arrays.asList("apron", "mitten", "towel"));
        expectedOrder.setCustomerId("customerId01");

        customer1 = new Customer();
        customer1.setCustomerId("customerId01");
        customer1.setName("name1");
        customer1.setEmail("customer@email");
        customer1.setLocation(new Location());
        customer1.setPassword("password");
        customer1.setHistoryOrderIds(Arrays.asList("orderId01", "orderId02"));
    }

    @Test
    public void getOrder_withValidOrderId_returnOrder() {
        // GIVEN
        String givenOrderId = "orderId01";

        // WHEN
       when(dynamoDBMapper.load(Order.class, givenOrderId)).thenReturn(expectedOrder);
       Order order = orderDao.getOrder(givenOrderId);

        // THEN
        assertEquals(expectedOrder, order, String.format("order id: {%s} doesn't match the expectedOrder id: {%s}", order.getOrderId(), expectedOrder.getOrderId()));
    }

    @Test
    public void getOrder_withInvalidOrderId_returnNull() {
        // GIVEN
        String givenOrderId = "invalidId";

        // WHEN
        when(dynamoDBMapper.load(Order.class, givenOrderId)).thenReturn(null);
        Order order = orderDao.getOrder(givenOrderId);

        // THEN
        assertEquals(null, order);
    }

    @Test
    public void getOrders_withValidOrderIds_returnListOrders() {
        // GIVEN
        List<String> requestedOrders = Arrays.asList("orderId01", "orderId02");

        Order order1 = new Order();
        order1.setOrderId("orderId01");
        order1.setProductNames(Arrays.asList("mitten"));

        Order order2 = new Order();
        order2.setOrderId("orderId02");
        order2.setProductNames(Arrays.asList("apron"));

        Map<String, List<Object>>  res = new HashMap<>();

        List<Order> orderList = Arrays.asList(order1, order2);

        List<Object> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);

        res.put("Cool-With-Coule-Order", list);

        when(dynamoDBMapper.batchLoad(orderList)).thenReturn(res);

        List<Order> orders = orderDao.getOrders(requestedOrders);
        // THEN

        assertEquals(orderList, orders, String.format("%s ", orders));

    }

}
