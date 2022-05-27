package main.java.com.obj.model;


import lombok.*;
import main.java.com.obj.Product;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ProductModel {

    private String name;

    private String type;

    private String upcCode;

    private Integer quantity;

    private String description;

    private Long priceInCents;

    private String imageUrl;





    public ProductModel (Product product) {
        this.name = product.getName();
        this.type = product.getType();
        this.upcCode = product.getUpcCode();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.priceInCents = product.getPriceInCents();
        this.imageUrl = product.getImageUrl();
    }

}