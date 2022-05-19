package main.java.com.sequence.order;

import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.CustomerModel;
import main.java.com.obj.model.OrderModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString

public class GETOrdersResult {
    private CustomerModel customerModel;
    private List<String> orderId;
    private ResponseStatus responseStatus;
    private List<String> ProductIds;
}
