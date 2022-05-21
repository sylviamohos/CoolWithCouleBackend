package main.java.com.sequence.customer;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.security.InvalidParameterException;


public class PUTCustomerActivityProvider implements RequestHandler<PUTCustomerRequest, PUTCustomerResult> {
    @Override
    public PUTCustomerResult handleRequest(PUTCustomerRequest input, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.providePUTCustomerActivity().handleRequest(input, context);
        } catch (InvalidParameterException e) {
            ResponseStatus status = new ResponseStatus(400, "Customer id cannot be null.");
            return new PUTCustomerResult(null, status);
        } catch (CustomerNotFoundException e) {
            ResponseStatus status = new ResponseStatus(400, "Customer not found.");
            return new PUTCustomerResult(null, status);
        } catch (AmazonDynamoDBException e) {
            ResponseStatus status = new ResponseStatus(400, "Error, try again.");
            return new PUTCustomerResult(null, status);
        }
    }
}
