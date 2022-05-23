package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.ProductAlreadyExistsException;
import main.java.com.obj.ResponseStatus;
import main.java.com.sequence.product.request.POSTProductRequest;
import main.java.com.sequence.product.result.POSTProductResult;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@NoArgsConstructor
public class POSTProductActivityProvider implements RequestHandler<POSTProductRequest, POSTProductResult> {

    @Override
    public POSTProductResult handleRequest(POSTProductRequest postProductRequest, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            if (postProductRequest.getName() == null || postProductRequest.getType() == null || postProductRequest.getUpcCode() == null
            || postProductRequest.getQuantity() == null || postProductRequest.getDescription() == null || postProductRequest.getPriceInCents() == null
            || postProductRequest.getImageUrl() == null) {
                throw new NullPointerException();
            }
            return dagger.providePOSTProductActivity().handleRequest(postProductRequest, context);
        } catch (NullPointerException e) {
            ResponseStatus status = new ResponseStatus(400, "One of the provided fields were null");
            return new POSTProductResult(null, status);
        } catch (ProductAlreadyExistsException e) {
            ResponseStatus status = new ResponseStatus(400, "Product Already Exists with the name " + postProductRequest.getName());
            return new POSTProductResult(null, status);
        } catch (DynamoDbException e) {
            ResponseStatus status = new ResponseStatus(500, "Customer not found.");
            return new POSTProductResult(null, status);
        }
    }
}