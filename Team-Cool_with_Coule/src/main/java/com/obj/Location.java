package main.java.com.obj;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Location {

    private String address;
    private String city;
    private String state;
    private String zipCode;
}
