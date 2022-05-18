package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Order;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.model.CustomerModel;
import main.java.com.obj.model.OrderModel;

import javax.inject.Inject;

public class POSTCheckoutActivity implements RequestHandler<POSTCheckoutRequest, POSTCheckoutResult> {
    //private final CustomerDao customerdao;
    private final OrderDao orderDao;

    @Inject
    public POSTCheckoutActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public POSTCheckoutResult handleRequest(final POSTCheckoutRequest postCheckoutRequest, Context context) {


        if (postCheckoutRequest.getCustomerModel().getCustomerId() == null ) {
           // throw new CustomerNoyFoundExcception()

        }

        Order order = new Order();
        order.setOrderId(postCheckoutRequest.getOrderModel().getOrderId());
        order.setOrderDate(postCheckoutRequest.getDate());
        order.setProductNames(postCheckoutRequest.getOrderModel().getProductNames());
        order.setCustomerModel(postCheckoutRequest.getCustomerModel());

        orderDao.addOrder(order);

      /*
            check for responseStatus.
            if good continue to return statement
            if not also throw an exception.

       */

        return POSTCheckoutResult.builder()
                .withOrderModel(new OrderModel())
                .withCustomerModel(new CustomerModel())
               // .withResponseStatus()
                .build();
    }

}
