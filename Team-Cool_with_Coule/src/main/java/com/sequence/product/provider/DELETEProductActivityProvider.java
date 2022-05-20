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
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@NoArgsConstructor
public class DELETEProductActivityProvider implements RequestHandler<DELETEProductRequest, DELETEProductResult> {

    @Override
    public DELETEProductResult handleRequest(DELETEProductRequest deleteProductRequest, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.provideDELETEProductActivity().handleRequest(deleteProductRequest, context);
        } catch (ProductDoesNotExistException e) {
            ResponseStatus status = new ResponseStatus(400, "Product does not exist with the name " + deleteProductRequest.getName());
            return new DELETEProductResult(null, status);
        } catch (DynamoDbException e) {
            ResponseStatus status = new ResponseStatus(500, "Error, try again");
            return new DELETEProductResult(null, status);
        }
    }

}
