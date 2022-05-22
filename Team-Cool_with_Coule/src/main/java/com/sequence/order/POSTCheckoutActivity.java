package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.OutOfStockException;
import main.java.com.obj.Customer;
import main.java.com.obj.Order;
import main.java.com.obj.Product;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.OrderModel;

import javax.inject.Inject;
import java.security.InvalidParameterException;
import java.util.*;

public class POSTCheckoutActivity implements RequestHandler<POSTCheckoutRequest, POSTCheckoutResult> {
    private final CustomerDao customerdao;
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private ResponseStatus responseStatus;

    // TODO Blank comment
    @Inject
    public POSTCheckoutActivity(OrderDao orderDao, CustomerDao customerDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.customerdao = customerDao;
        this.productDao = productDao;
    }

    @Override
    public POSTCheckoutResult handleRequest(final POSTCheckoutRequest postCheckoutRequest, Context context) {

        if (postCheckoutRequest.getCustomerId() == null) {
            throw new InvalidParameterException("[ERROR] customer id is null");
        }
        if (postCheckoutRequest.getCart() == null) {
            throw new InvalidParameterException("[ERROR] cart is null");
        }
        if (postCheckoutRequest.getCart().isEmpty()) {
            throw new InvalidParameterException("[ERROR] WHY IS THE CART EMPTY???!");
        }

        // GET CUSTOMER FIRST
        // if customer doesnt exist throw customer not found exception
        List<Customer> customersFound = customerdao.getCustomerById(postCheckoutRequest.getCustomerId());

        if (customersFound.isEmpty()) {
            throw new CustomerNotFoundException(String.format("[ERROR] customer id not found."));
        }

        Customer customer = customersFound.get(0);
        List<String> productNames = new ArrayList<>();
        productNames.addAll(postCheckoutRequest.getCart().keySet());

        List<Product> products = productDao.getProducts(productNames);

        for (Product product : products) {
            Integer availableQuantity = product.getQuantity();
            Integer desiredQuantity = postCheckoutRequest.getCart().get(product.getName());

            if (availableQuantity < desiredQuantity) {
                throw new OutOfStockException(String.format("[ERROR] product: {%s} Not enough sorry! Please come back next time!",
                        product.getName()));
            }
            product.setQuantity(availableQuantity - desiredQuantity);
        }

        productDao.saveProducts(products);

        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderDate(new Date());
        order.setCustomerId(customer.getCustomerId());
        order.setProductNames(productNames);
        orderDao.addOrder(order);

        List<String> historyOrderIds = new ArrayList<>();
        historyOrderIds.addAll(customer.getHistoryOrderIds());

        historyOrderIds.add(order.getOrderId());
        customer.setHistoryOrderIds(historyOrderIds);
        customerdao.saveCustomer(customer);

        responseStatus = new ResponseStatus(200, "[SUCCESS] order has been added to database!");

        return POSTCheckoutResult.builder()
                .orderModel(new OrderModel(order))
                .responseStatus(responseStatus)
                .build();
    }

}