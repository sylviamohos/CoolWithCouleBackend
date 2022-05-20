package main.java.com.sequence.customer;

import lombok.*;
import main.java.com.obj.Location;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

public class POSTCustomerRequest {
    private String name;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String password;
}
