package main.java.com.sequence.customer;

import lombok.*;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.CustomerModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class GETCustomerByEmailResult {
    private CustomerModel customerModel;
    private ResponseStatus responseStatus;
}
