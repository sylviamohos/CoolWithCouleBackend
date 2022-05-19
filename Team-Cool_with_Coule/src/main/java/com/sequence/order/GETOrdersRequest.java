package main.java.com.sequence.order;

import java.util.Date;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

public class GETOrdersRequest {
    private List<String> orderId;
    private List<String> productNames;
    private Date orderDate;
    private String customerId;

}
