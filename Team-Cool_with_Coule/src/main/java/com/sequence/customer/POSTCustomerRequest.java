package main.java.com.sequence.customer;

import lombok.*;


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
    //TODO
//    private String password;

}
