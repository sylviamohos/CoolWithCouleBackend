package main.java.com.sequence.product.result;

import lombok.*;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.ProductModel;

@Getter
@Setter
@Builder
public class POSTProductResult {
    private ProductModel product;
    private ResponseStatus responseStatus;
}
