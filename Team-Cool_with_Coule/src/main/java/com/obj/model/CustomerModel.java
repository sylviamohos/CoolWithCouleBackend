package main.java.com.obj.model;


import lombok.*;

import javax.xml.stream.Location;
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

}
