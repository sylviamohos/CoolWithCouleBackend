package main.java.com.sequence.customer;


import lombok.*;
import main.java.com.obj.dao.CustomerDao;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class DELETECustomerRequest {

    private String customerId;

    // TODO: implement if Administrator functionality is added:
    //private String callingUserId;


}
