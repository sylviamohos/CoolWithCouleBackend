package main.java.com.sequence.order;

import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.OrderModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString

public class POSTCheckoutResult {
    private OrderModel orderModel;
    private ResponseStatus responseStatus;

}
