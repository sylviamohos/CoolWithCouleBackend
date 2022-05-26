package main.java.com.sequence.order;

import lombok.*;
import main.java.com.obj.Product;
import main.java.com.obj.model.OrderModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder

public class POSTCheckoutRequest {

    private String customerId;
    private List<Product> cart;

}
