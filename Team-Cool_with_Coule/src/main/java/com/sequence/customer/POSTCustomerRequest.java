package main.java.com.sequence.customer;

import lombok.*;
import main.java.com.obj.model.CustomerModel;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

public class POSTCustomerRequest {
    private String customerId;
    private String name;
    private String email;
    private String location;
    private boolean isAdmin;
    private String password;
}
