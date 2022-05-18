package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Order;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.model.OrderModel;

import javax.inject.Inject;

/**
 * Implementation of the GETOrderActivity for the CoolWithCoule GETOrder API.
 *
 * This API allows a customer/Admin to view their Orders.
 */
public class GETOrdersActivity implements RequestHandler<GETOrderRequest, GETOrderResult> {
    private final OrderDao orderDao;

    /**
     * Instantiates a new GETOrderActivity object.
     * @param orderDao orderDao to access the Cool-With-Coule-Order table.
     */
    @Inject
    public GETOrdersActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public GETOrderResult handleRequest(GETOrderRequest input, Context context) {
        String requestedOrderId = input.getOrderId();
        Order order = orderDao.getOrder(requestedOrderId);
        OrderModel orderModel = new OrderModel(order);

        // TODO
        // find responseStatus.
        // if good continue to return statement
        // if not throw exception.

        return GETOrderResult.builder()
                .withOrder(orderModel)
                //.withResponseStatus()
                .build();
    }
}
