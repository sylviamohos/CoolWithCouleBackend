package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.ProductDoesNotExistException;
import main.java.com.obj.ResponseStatus;
import main.java.com.sequence.product.request.DELETEProductRequest;
import main.java.com.sequence.product.result.DELETEProductResult;

@NoArgsConstructor
public class DELETEProductActivityProvider implements RequestHandler<DELETEProductRequest, DELETEProductResult> {

    @Override
    public DELETEProductResult handleRequest(DELETEProductRequest deleteProductRequest, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.provideDELETEProductActivity().handleRequest(deleteProductRequest, context);
        } catch (ProductDoesNotExistException e) {
            ResponseStatus status = new ResponseStatus(400, "Customer not found.");
            return new DELETEProductResult(status);
        }
    }

}
