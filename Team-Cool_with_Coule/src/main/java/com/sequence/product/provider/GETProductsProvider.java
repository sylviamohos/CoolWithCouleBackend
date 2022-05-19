package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.obj.ResponseStatus;
import main.java.com.sequence.product.request.GETProductsRequest;
import main.java.com.sequence.product.result.GETProductsResult;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.security.InvalidParameterException;

@NoArgsConstructor
public class GETProductsProvider implements RequestHandler<GETProductsRequest, GETProductsResult> {

    @Override
    public GETProductsResult handleRequest(GETProductsRequest getProductsRequest, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.provideGETProductsActivity().handleRequest(getProductsRequest, context);
        } catch (InvalidParameterException e) {
            ResponseStatus status = new ResponseStatus(400, "Must include a name or a type");
            return new GETProductsResult(null, status);
        } catch (DynamoDbException e) {
            ResponseStatus status = new ResponseStatus(500, "Customer not found.");
            return new GETProductsResult(null, status);
        }
    }
}
