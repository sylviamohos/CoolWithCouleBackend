package main.java.com.obj.model;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductModel {

    private String name;

    private String type;

    private String upcCode;

    private Integer quantity;

    private String description;

    private Long priceInCents;

    private String imageUrl;



}
