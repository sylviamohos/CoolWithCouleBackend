package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
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

        orderDao.addOrder(order);

        responseStatus.setMessage("[SUCCESS] order has been added to database!");
        responseStatus.setCode(200);
      /*
            check for responseStatus.
            if good continue to return statement
            if not also throw an exception.

       */

        return POSTCheckoutResult.builder()
                .withOrderModel(new OrderModel())
                .withCustomerModel(new CustomerModel())
                .withResponseStatus(responseStatus)
                .build();
    }

}
