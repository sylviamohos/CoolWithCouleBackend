package main.java.com.sequence.customer;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class GETCustomerByIdRequest {
    private String customerId;
}
