package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.obj.Customer;
import main.java.com.obj.Order;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.model.OrderModel;

import javax.inject.Inject;
import java.util.List;

public class POSTCheckoutActivity implements RequestHandler<POSTCheckoutRequest, POSTCheckoutResult> {
    private final CustomerDao customerdao;
    private final OrderDao orderDao;
    private ResponseStatus responseStatus;

    @Inject
    public POSTCheckoutActivity(OrderDao orderDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.customerdao = customerDao;
    }

    @Override
    public POSTCheckoutResult handleRequest(final POSTCheckoutRequest postCheckoutRequest, Context context) {



        Order order = new Order();
        order.setOrderId(postCheckoutRequest.getOrderModel().getOrderId());
        order.setOrderDate(postCheckoutRequest.getDate());
        order.setProductNames(postCheckoutRequest.getOrderModel().getProductNames());
        order.setCustomerId(postCheckoutRequest.getCustomerId());
        order.setProductNames(postCheckoutRequest.getProductNames());

        Order addedOrder = orderDao.addOrder(order);

        if (addedOrder == null) {
            responseStatus = new ResponseStatus(400, String.format("[ERROR] Invalid order id:{} ", postCheckoutRequest.getOrderModel().getOrderId()));
            throw new OrderNotFoundException(responseStatus.getMessage());
        }

        responseStatus = new ResponseStatus(200, "[SUCCESS] order has been added to database!");

        // Adding new historyId in Customer
        Customer customerToFind = customerdao.getCustomerById(addedOrder.getCustomerId()).get(0);
        if ( customerToFind == null ) {
            responseStatus = new ResponseStatus(400, String.format("[ERROR] Invalid customer id:{} ", addedOrder.getCustomerId()));
            throw new CustomerNotFoundException(responseStatus.getMessage());
        }

        List<String> updatedHistoryId = customerToFind.getHistoryOrderIds();
        updatedHistoryId.add(addedOrder.getOrderId());

        Customer editedCustomer = customerToFind;
        editedCustomer.setHistoryOrderIds(updatedHistoryId);

        customerdao.saveCustomer(editedCustomer);
        
        return POSTCheckoutResult.builder()
                .withOrderModel(new OrderModel())
                .withCustomerId(order.getCustomerId())
                .withResponseStatus(responseStatus)
                .build();
    }

}
