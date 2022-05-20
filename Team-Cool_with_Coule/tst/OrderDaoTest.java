
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
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
import java.util.Arrays;
import java.util.Date;

public class OrderDaoTest {

    @InjectMocks
    private OrderDao orderDao;

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private Order expectedOrder;

    @BeforeEach
    public void setup() {
        openMocks(this);
        expectedOrder = new Order();
        expectedOrder.setOrderId("orderId01");
        expectedOrder.setOrderDate(new Date());
        expectedOrder.setProductNames(Arrays.asList("apron", "mitten", "towel"));
        expectedOrder.setCustomerId("customerId01");
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

    }

}
