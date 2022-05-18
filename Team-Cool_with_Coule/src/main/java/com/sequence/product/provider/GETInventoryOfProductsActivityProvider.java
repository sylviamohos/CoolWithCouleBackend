package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.sequence.product.request.DELETEProductRequest;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.result.DELETEProductResult;
import main.java.com.sequence.product.result.GETInventoryOfProductsResult;

@NoArgsConstructor
public class GETInventoryOfProductsActivityProvider implements RequestHandler<GETInventoryOfProductsRequest, GETInventoryOfProductsResult> {

    @Override
    public GETInventoryOfProductsResult handleRequest(GETInventoryOfProductsRequest getInventoryOfProductsRequest, Context context) {
        return null;
    }

    private DaggerServiceComponent getDaggerComponent() {
        return (DaggerServiceComponent) DaggerServiceComponent.create();
    }
}
