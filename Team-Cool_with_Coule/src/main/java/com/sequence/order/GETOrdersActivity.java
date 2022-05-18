package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Order;
import main.java.com.obj.ResponseStatus;
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
    private ResponseStatus responseStatus;

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

        if (order != null) {
            responseStatus.setCode(200);
            responseStatus.setMessage(String.format("[SUCCESS] Order by order id: {} has been found!", input.getOrderId()));
        } else {
            responseStatus.setCode(400);
            responseStatus.setMessage(String.format("[ERROR] Order has not been found by order id {}!", input.getOrderId()));
            // throw new OrderNotFoundException
        }

        OrderModel orderModel = new OrderModel(order);

        return GETOrderResult.builder()
                .withOrder(orderModel)
                .withResponseStatus(responseStatus)
                .build();
    }
}
