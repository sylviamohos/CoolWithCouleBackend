package main.java.com.sequence.product.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class GETProductByTypeRequest {
    private String type;
}
