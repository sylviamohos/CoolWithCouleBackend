package main.java.com.sequence.customer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PUTCustomerRequest {
    private String callingUserId;
    private String customerId;
    private String name;
    private String email;
    private String password;
    private String address;
    private String city;
    private String state;
    private String zipCode;
}
