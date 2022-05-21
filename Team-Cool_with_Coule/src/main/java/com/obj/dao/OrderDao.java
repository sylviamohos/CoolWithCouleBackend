package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import main.java.com.obj.Customer;
import main.java.com.obj.Order;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Accesses data for orders using Order
 */

public class OrderDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiate OrderDao.
     *
     * @param dynamoDBMapper used to interact with the Cool-With-Coule table
     */
    @Inject
    public OrderDao(DynamoDBMapper dynamoDBMapper) {this.dynamoDBMapper = dynamoDBMapper;}

    /**
     * Returns a single {@link Order} by its orderId.
     * @param orderId the id of the Order being retrieved
     * @return the Order, or null if not found
     */
    public Order getOrder(String orderId) {

        Order order = this.dynamoDBMapper.load(Order.class, orderId);
        return order;
    }

    public List<Order> getOrders(List<String> orderIds) {
        List<Order> orders = new ArrayList<>();

        for (String s : orderIds) {
            Order order = new Order();
            order.setOrderId(s);
            orders.add(order);
        }

        Map<String, List<Object>> loadResult = dynamoDBMapper.batchLoad(orders);
        List<Order> result = new ArrayList<>();
        for (List<Object> list : loadResult.values()) {
            for (Object o : list) {
                result.add((Order) o);
            }
        }
        return result;
    }

    /**
     * Add an {@link Order} to the dynamoDB Cool-With-Coule table.
     * @param order The order being saved
     * @return the order that was created
     */
    public Order addOrder(Order order) {
        dynamoDBMapper.save(order);
        return order;
    }

}
