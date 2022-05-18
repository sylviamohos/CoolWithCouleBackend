package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.obj.Order;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.model.CustomerModel;
import main.java.com.obj.model.OrderModel;

import javax.inject.Inject;

public class POSTCheckoutActivity implements RequestHandler<POSTCheckoutRequest, POSTCheckoutResult> {
    //private final CustomerDao customerdao;
    private final OrderDao orderDao;
    private ResponseStatus responseStatus;

    @Inject
    public POSTCheckoutActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public POSTCheckoutResult handleRequest(final POSTCheckoutRequest postCheckoutRequest, Context context) {

        Order order = new Order();
        order.setOrderId(postCheckoutRequest.getOrderModel().getOrderId());
        order.setOrderDate(postCheckoutRequest.getDate());
        order.setProductNames(postCheckoutRequest.getOrderModel().getProductNames());
        order.setCustomerModel(postCheckoutRequest.getCustomerModel());

        Order addedOrder = orderDao.addOrder(order);

        if (addedOrder == null) {
            responseStatus = new ResponseStatus(400, String.format("[ERROR] Invalid order id:{} ", postCheckoutRequest.getOrderModel().getOrderId()));
            throw new OrderNotFoundException(responseStatus.getMessage());
        }

        responseStatus = new ResponseStatus(200, "[SUCCESS] order has been added to database!");

        return POSTCheckoutResult.builder()
                .withOrderModel(new OrderModel())
                .withCustomerModel(new CustomerModel())
                .withResponseStatus(responseStatus)
                .build();
    }

}
