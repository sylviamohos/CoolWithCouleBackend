package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;

import javax.inject.Inject;

public class POSTCheckoutActivity implements RequestHandler<POSTCheckoutRequest, POSTCheckoutResult> {
    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    private ResponseStatus responseStatus;

    @Inject
    public POSTCheckoutActivity(OrderDao orderDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
    }
    @Override
    public POSTCheckoutResult handleRequest(POSTCheckoutRequest input, Context context) {
        return null;
    }
}
