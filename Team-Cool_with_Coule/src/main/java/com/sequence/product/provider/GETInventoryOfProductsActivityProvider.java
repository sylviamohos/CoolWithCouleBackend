package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.obj.ResponseStatus;
import main.java.com.sequence.product.request.DELETEProductRequest;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.result.DELETEProductResult;
import main.java.com.sequence.product.result.GETInventoryOfProductsResult;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@NoArgsConstructor
public class GETInventoryOfProductsActivityProvider implements RequestHandler<GETInventoryOfProductsRequest, GETInventoryOfProductsResult> {

    @Override
    public GETInventoryOfProductsResult handleRequest(GETInventoryOfProductsRequest getInventoryOfProductsRequest, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.provideGETInventoryOfProductsActivity().handleRequest(getInventoryOfProductsRequest, context);
        } catch (DynamoDbException e) {
            ResponseStatus status = new ResponseStatus(500, "Error, try again");
            return new GETInventoryOfProductsResult(null, status);
        }
    }

}
