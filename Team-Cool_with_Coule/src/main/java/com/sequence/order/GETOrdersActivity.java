package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Order;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.model.CustomerModel;
import main.java.com.obj.model.OrderModel;
import main.java.com.obj.model.OrdersModel;

import javax.inject.Inject;
import java.util.List;


public class GETOrdersActivity implements RequestHandler<GETOrdersRequest, GETOrdersResult> {
    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    private ResponseStatus responseStatus;

    @Inject
    public GETOrdersActivity(OrderDao orderDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
    }

    @Override
    public GETOrdersResult handleRequest(GETOrdersRequest input, Context context) {
        String requestedCustomerId = input.getCustomerId();
        Customer customerToFind = customerDao.getCustomerById(requestedCustomerId).get(0);

        if (customerToFind == null) {
            throw new CustomerNotFoundException();
        }
        responseStatus = new ResponseStatus(200, "Customer found!");


        OrdersModel ordersModel = OrdersModel.builder()
                .customerModel(new CustomerModel(customerToFind))
                // TODO clarify tmr
                //.OrderId(input.getOrderId())
                //.productNames()
                .date(input.getOrderDate())
                .build();

        return GETOrdersResult.builder()
                .customerModel(new CustomerModel(customerToFind))
                .responseStatus(responseStatus)

                .build();
    }
}
