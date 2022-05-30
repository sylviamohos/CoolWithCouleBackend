package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.obj.Order;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.model.OrderModel;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;

/**
 * Implementation of the GETOrderActivity for the CoolWithCoule GETOrder API.
 *
 * This API allows a customer/Admin to view their Orders.
 */
public class GETOrderActivity implements RequestHandler<GETOrderRequest, GETOrderResult> {
    private final OrderDao orderDao;
    private ResponseStatus responseStatus;

    /**
     * Instantiates a new GETOrderActivity object.
     * @param orderDao orderDao to access the Cool-With-Coule-Order table.
     */
    @Inject
    public GETOrderActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public GETOrderResult handleRequest(GETOrderRequest input, Context context) {

        if (input.getOrderId() == null) {
            throw new InvalidParameterException("[ERROR] The order id is null in the REQUEST");
        }

        String requestedOrderId = input.getOrderId();
        Order order = orderDao.getOrder(requestedOrderId);

        // throw exception here
        if (order == null) {
            throw new OrderNotFoundException(String.format("[ERROR] orderId: {%s} not found", input.getOrderId()));
        }

        responseStatus = new ResponseStatus(200, "[SUCCESS] Good job everything is working great.");

        OrderModel orderModel = new OrderModel(order);

        return GETOrderResult.builder()
                .order(orderModel)
                .responseStatus(responseStatus)
                .build();
    }
}
