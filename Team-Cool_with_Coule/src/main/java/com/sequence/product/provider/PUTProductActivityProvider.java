package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.obj.ResponseStatus;
import main.java.com.sequence.product.request.POSTProductRequest;
import main.java.com.sequence.product.request.PUTProductRequest;
import main.java.com.sequence.product.result.DELETEProductResult;
import main.java.com.sequence.product.result.POSTProductResult;
import main.java.com.sequence.product.result.PUTProductResult;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.security.InvalidParameterException;

@NoArgsConstructor
public class PUTProductActivityProvider implements RequestHandler<PUTProductRequest, PUTProductResult> {

    @Override
    public PUTProductResult handleRequest(PUTProductRequest putProductRequest, Context context) {
        try {
            ServiceComponent dagger = DaggerServiceComponent.create();
            return dagger.providePUTProductActivity().handleRequest(putProductRequest, context);
        } catch (InvalidParameterException e) {
            ResponseStatus status = new ResponseStatus(400, "Product name cannot be null");
            return new PUTProductResult(null, status);
        } catch (DynamoDbException e) {
            ResponseStatus status = new ResponseStatus(500, "Error, try again");
            return new PUTProductResult(null, status);
        }
    }
}
