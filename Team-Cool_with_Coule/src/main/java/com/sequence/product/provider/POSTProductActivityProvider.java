package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.sequence.product.request.POSTProductRequest;
import main.java.com.sequence.product.result.POSTProductResult;

@NoArgsConstructor
public class POSTProductActivityProvider implements RequestHandler<POSTProductRequest, POSTProductResult> {

    @Override
    public POSTProductResult handleRequest(POSTProductRequest postProductRequest, Context context) {
        return getDaggerComponent().providePOSTProductActivity().handleRequest(postProductRequest, context);
    }

    private DaggerServiceComponent getDaggerComponent() {
        return (DaggerServiceComponent) DaggerServiceComponent.create();
    }
}
