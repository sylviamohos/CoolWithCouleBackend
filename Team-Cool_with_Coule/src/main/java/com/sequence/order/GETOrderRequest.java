package main.java.com.sequence.order;

import lombok.*;
import main.java.com.obj.model.CustomerModel;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString

public class GETOrderRequest {
    private String orderId;

}
