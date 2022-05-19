package main.java.com.obj.model;

import java.util.List;
import java.util.Date;
import lombok.*;
import main.java.com.obj.Order;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class OrderModel {

    private String orderId;
    private List<String> productNames;
    private Date orderDate;
    private String customerId;

    public OrderModel(Order order) {
        this.orderId = order.getOrderId();
        this.orderDate = order.getOrderDate();
        this.productNames = order.getProductNames();
        this.customerId = order.getCustomerId();
    }

}
