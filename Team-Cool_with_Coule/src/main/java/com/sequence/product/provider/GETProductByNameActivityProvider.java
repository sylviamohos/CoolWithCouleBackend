package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.sequence.product.request.GETProductByNameRequest;
import main.java.com.sequence.product.result.GETProductByNameResult;


@NoArgsConstructor
public class GETProductByNameActivityProvider implements RequestHandler<GETProductByNameRequest, GETProductByNameResult> {

    @Override
    public GETProductByNameResult handleRequest(GETProductByNameRequest getProductByNameRequest, Context context) {
        return null;
    }

    private DaggerServiceComponent getDaggerComponent() {
        return (DaggerServiceComponent) DaggerServiceComponent.create();
    }
}
