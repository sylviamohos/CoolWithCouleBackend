import main.java.com.exception.OrderNotFoundException;
import main.java.com.obj.Order;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.OrderDao;
import main.java.com.sequence.order.GETOrderActivity;
import main.java.com.sequence.order.GETOrderRequest;
import main.java.com.sequence.order.GETOrderResult;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

import static org.mockito.MockitoAnnotations.openMocks;

public class GETOrderActivityTest {
    @InjectMocks
    GETOrderActivity getOrderActivity;
    @Mock
    private OrderDao orderDao;
    private ResponseStatus responseStatus;
    private Order validOrder;

    @BeforeEach
    private void setup() {
        openMocks(this);
        getOrderActivity = new GETOrderActivity(orderDao);
        validOrder = new Order();
        validOrder.setOrderId("123a");
        validOrder.setOrderDate(new Date());
        validOrder.setCustomerId("cust1");
        validOrder.setProductNames(Arrays.asList("product1", "product2"));
    }
    @Test
    public void handleRequest_GetOrderWithValidCredentials_returnsGETOrderResults() {
        // GIVEN
        String requestedOrderId = "123a";
        GETOrderRequest orderRequest = new GETOrderRequest(requestedOrderId);
        when(orderDao.getOrder(requestedOrderId)).thenReturn(validOrder);

        // WHEN
        GETOrderResult result = getOrderActivity.handleRequest(orderRequest,null);

        // THEN
        assertEquals(200, result.getResponseStatus().getCode());
        assertTrue(validOrder.getOrderId() == result.getOrder().getOrderId());
    }

    @Test
    public void handleRequest_GetOrderWithInvalidCredentials_ThrowsOrderNotFoundException() {
        // GIVEN
        String requestedOrderId = "123b";
        GETOrderRequest orderRequest = new GETOrderRequest(requestedOrderId);

        when(orderDao.getOrder(requestedOrderId)).thenThrow(new OrderNotFoundException());

        // WHEN
        // THEN
        assertThrows(OrderNotFoundException.class, () -> getOrderActivity.handleRequest(orderRequest, null));
    }

    @Test
    public void handleRequest_GetOrderWithNull_ThrowsOrderNotFoundException() {
        // GIVEN
        String requestedOrderId = null;
        GETOrderRequest orderRequest = new GETOrderRequest(requestedOrderId);

        // WHEN
        // THEN
        assertThrows(OrderNotFoundException.class, () -> getOrderActivity.handleRequest(orderRequest, null));
    }

}
