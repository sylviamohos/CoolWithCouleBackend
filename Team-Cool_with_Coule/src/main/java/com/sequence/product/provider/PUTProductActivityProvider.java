package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.sequence.product.request.POSTProductRequest;
import main.java.com.sequence.product.request.PUTProductRequest;
import main.java.com.sequence.product.result.POSTProductResult;
import main.java.com.sequence.product.result.PUTProductResult;

@NoArgsConstructor
public class PUTProductActivityProvider implements RequestHandler<PUTProductRequest, PUTProductResult> {

    @Override
    public PUTProductResult handleRequest(PUTProductRequest putProductRequest, Context context) {
        return getDaggerComponent().providePUTProductActivity().handleRequest(putProductRequest, context);
    }

    private DaggerServiceComponent getDaggerComponent() {
        return (DaggerServiceComponent) DaggerServiceComponent.create();
    }
}
