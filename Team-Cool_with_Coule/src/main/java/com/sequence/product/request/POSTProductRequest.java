package main.java.com.sequence.product.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class POSTProductRequest {

    private String name;

    private String type;

    private String upcCode;

    private Integer quantity;

    private String description;

    private Long priceInCents;

    private String imageUrl;
}
