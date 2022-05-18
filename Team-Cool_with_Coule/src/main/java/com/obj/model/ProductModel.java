package main.java.com.obj.model;


import lombok.*;
import main.java.com.obj.Product;


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

/*
    public ProductModel toProductModel(Product product) {
        return ProductModel.builder()
                .name(product.getName())
                .type(product.getType())
                .upcCode(product.getUpcCode())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .priceInCents(product.getPriceInCents())
                .imageUrl(product.getImageUrl())
                .build();
    }
 */