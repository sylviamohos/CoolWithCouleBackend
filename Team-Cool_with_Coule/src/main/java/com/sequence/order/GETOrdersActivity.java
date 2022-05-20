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

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class GETOrdersActivity implements RequestHandler<GETOrdersRequest, GETOrdersResult> {
    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    private ResponseStatus responseStatus;

    // TODO comment
    @Inject
    public GETOrdersActivity(OrderDao orderDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
    }

    @Override
    public GETOrdersResult handleRequest(GETOrdersRequest input, Context context) {
        String requestedCustomerId = input.getCustomerId();

        List<Customer> customers = customerDao.getCustomerById(requestedCustomerId);

        if (customers.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        Customer customer = customers.get(0);

        // get list of order
        List<Order> ordersFound = orderDao.getOrders(customer.getHistoryOrderIds());

        // convert order model list to orders from model class
        List<OrderModel> orderModels = new ArrayList<>();

        for(Order order: ordersFound ) {
            orderModels.add(new OrderModel(order));
        }

        responseStatus = new ResponseStatus(200, "Orders found!");

        return GETOrdersResult.builder()
                .orderModels(orderModels)
                .responseStatus(responseStatus)
                .build();
    }
}
