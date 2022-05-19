package main.java.com.sequence.order;

import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.OrderModel;
import lombok.*;

@Getter
@Setter
@Builder
@ToString

public class GETOrderResult {

    private OrderModel order;
    private ResponseStatus responseStatus;
}
