package main.java.com.obj.model;

import main.java.com.obj.Customer;
import lombok.*;
<<<<<<< HEAD
=======
import main.java.com.obj.Customer;
>>>>>>> feature
import main.java.com.obj.Location;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CustomerModel {

    private String customerId;
    private String name;
    private String email;
    private Location location;
    private List<String> historyOrderIds;

    public CustomerModel(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.location = customer.getLocation();
        this.historyOrderIds = customer.getHistoryOrderIds();
    }
}
