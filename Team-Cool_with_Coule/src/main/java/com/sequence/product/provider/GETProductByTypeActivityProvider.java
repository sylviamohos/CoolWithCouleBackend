package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.request.GETProductByTypeRequest;
import main.java.com.sequence.product.result.GETInventoryOfProductsResult;
import main.java.com.sequence.product.result.GETProductByTypeResult;

@NoArgsConstructor
public class GETProductByTypeActivityProvider implements RequestHandler<GETProductByTypeRequest, GETProductByTypeResult> {

    @Override
    public GETProductByTypeResult handleRequest(GETProductByTypeRequest getProductByTypeRequest, Context context) {
        return null;
    }

    private DaggerServiceComponent getDaggerComponent() {
        return (DaggerServiceComponent) DaggerServiceComponent.create();
    }
}
