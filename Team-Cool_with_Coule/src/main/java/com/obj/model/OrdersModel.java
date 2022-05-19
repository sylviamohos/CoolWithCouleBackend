package main.java.com.obj.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder

public class OrdersModel {
    private String orderId;
    private List<String> productIds;
    private CustomerModel customerModel;
    private Date date;

}
