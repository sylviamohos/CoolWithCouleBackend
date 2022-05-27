package main.java.com.sequence.customer;

import lombok.*;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.CustomerModel;


@Getter
@Setter
@Builder

public class POSTCustomerResult {
    private CustomerModel customerModel;
    private ResponseStatus responseStatus;
}
