package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class GETCustomerByEmailActivityProvider implements RequestHandler<GETCustomerByEmailRequest, GETCustomerByEmailResult> {
    @Override
    public GETCustomerByEmailResult handleRequest(GETCustomerByEmailRequest input, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.provideGETCustomerByEmailActivity().handleRequest(input, context);
        } catch (CustomerNotFoundException e) {
            ResponseStatus status = new ResponseStatus(400, "Customer not found.");
            return new GETCustomerByEmailResult(null, status);
        } catch (DynamoDbException e) {
            ResponseStatus status = new ResponseStatus(400, "Error, try again");
            return new GETCustomerByEmailResult(null, status);
        } catch (RuntimeException e) {
            ResponseStatus status = new ResponseStatus(400, "Error, cannot find customer by email");
            return new GETCustomerByEmailResult(null, status);
        }

    }
}
