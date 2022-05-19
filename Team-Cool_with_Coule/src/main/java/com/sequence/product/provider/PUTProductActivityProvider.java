package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.obj.ResponseStatus;
import main.java.com.sequence.product.request.POSTProductRequest;
import main.java.com.sequence.product.request.PUTProductRequest;
import main.java.com.sequence.product.result.POSTProductResult;
import main.java.com.sequence.product.result.PUTProductResult;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@NoArgsConstructor
public class PUTProductActivityProvider implements RequestHandler<PUTProductRequest, PUTProductResult> {

    @Override
    public PUTProductResult handleRequest(PUTProductRequest putProductRequest, Context context) {
        try {
            return getDaggerComponent().providePUTProductActivity().handleRequest(putProductRequest, context);
        } catch (DynamoDbException e) {
            ResponseStatus status = new ResponseStatus(500, "Error, try again");
            return new PUTProductResult(null, status);
        }
    }

    private DaggerServiceComponent getDaggerComponent() {
        return (DaggerServiceComponent) DaggerServiceComponent.create();
    }
}
