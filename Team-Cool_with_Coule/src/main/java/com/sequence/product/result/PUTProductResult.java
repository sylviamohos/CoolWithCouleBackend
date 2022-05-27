package main.java.com.sequence.product.result;

import lombok.*;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.ProductModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PUTProductResult {
    private ProductModel product;
    private ResponseStatus responseStatus;
}
