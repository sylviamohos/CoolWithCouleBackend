package main.java.com.sequence.product.result;

import lombok.*;
import main.java.com.obj.model.ProductModel;

import java.util.List;

@Getter
@Setter
@Builder
public class GETProductByTypeResult {
    private List<ProductModel> products;
}
