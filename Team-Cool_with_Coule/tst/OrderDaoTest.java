
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import main.java.com.obj.Customer;
import main.java.com.obj.Location;
import main.java.com.obj.Order;
import main.java.com.obj.dao.OrderDao;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
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
        String tableName = "Cool-With-Coule-Order";
        List<String> orderIds = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        List<String> productNames = new ArrayList<>();
        List<Order> tmp = new ArrayList<>();

        productNames.add("apron01");
        productNames.add("apron02");
        productNames.add("teatowel01");

        Order order1 = new Order();
        order1.setOrderDate(new Date());
        order1.setOrderId("order01");
        order1.setCustomerId(customer1.getCustomerId());
        order1.setProductNames(productNames);

        Order order2 = new Order();
        order2.setOrderDate(new Date());
        order2.setOrderId("order02");
        order2.setCustomerId(customer1.getCustomerId());
        order2.setProductNames(productNames);

        orderIds.add(order1.getOrderId());
        orderIds.add(order2.getOrderId());

        for (String s : orderIds) {
            Order order = new Order();
            order.setOrderId(s);
            tmp.add(order);
        }

        orderList.add(order1);
        orderList.add(order2);

        List<Object> objectList = new ArrayList<>();
        objectList.add(order1);
        objectList.add(order2);


        Map<String, List<Object>> map = new HashMap<>();
        map.put(tableName, objectList);

        when(dynamoDBMapper.batchLoad(tmp)).thenReturn(map);

        List<Order> orders = orderDao.getOrders(orderIds);
        // THEN

        assertEquals(orderList, orders, String.format("%s ", orders));
    }
}
