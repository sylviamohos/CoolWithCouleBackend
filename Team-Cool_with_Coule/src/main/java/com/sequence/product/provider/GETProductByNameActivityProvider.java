package main.java.com.sequence.product.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.ProductDoesNotExistException;
import main.java.com.obj.ResponseStatus;
import main.java.com.sequence.product.request.GETProductByNameRequest;
import main.java.com.sequence.product.result.GETProductByNameResult;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;


@NoArgsConstructor
public class GETProductByNameActivityProvider implements RequestHandler<GETProductByNameRequest, GETProductByNameResult> {

    @Override
    public GETProductByNameResult handleRequest(GETProductByNameRequest getProductByNameRequest, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();

        try {
            if (getProductByNameRequest.getName() == null) {
                throw new NullPointerException();
            }
            return dagger.provideGETProductByNameActivity().handleRequest(getProductByNameRequest, context);
        } catch (NullPointerException e) {
            ResponseStatus status = new ResponseStatus(400, "Name cannot be null");
            return new GETProductByNameResult(null, status);
        }
        catch (ProductDoesNotExistException e) {
            ResponseStatus status = new ResponseStatus(400, "Product does not exist with that name");
            return new GETProductByNameResult(null, status);
        } catch (DynamoDbException e) {
            ResponseStatus status = new ResponseStatus(500, "Customer not found.");
            return new GETProductByNameResult(null, status);
        }
    }
}
