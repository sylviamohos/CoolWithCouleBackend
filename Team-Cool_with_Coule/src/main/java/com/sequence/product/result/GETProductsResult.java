package main.java.com.sequence.product.result;

import lombok.*;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.ProductModel;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GETProductsResult {
    private List<ProductModel> products;
    private ResponseStatus responseStatus;
}
