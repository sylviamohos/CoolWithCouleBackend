package main.java.com.sequence.customer;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class GETCustomerByEmailRequest {
    
    private String email;
    private String password;
}
