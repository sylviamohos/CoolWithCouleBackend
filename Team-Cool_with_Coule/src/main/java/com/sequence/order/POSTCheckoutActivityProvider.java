package main.java.com.sequence.order;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.exception.OutOfStockException;
import main.java.com.exception.ProductDoesNotExistException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.security.InvalidParameterException;

public class POSTCheckoutActivityProvider implements RequestHandler<POSTCheckoutRequest, POSTCheckoutResult> {


    ResponseStatus responseStatus;

    @Override
    public POSTCheckoutResult handleRequest(POSTCheckoutRequest input, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.providePOSTCheckoutActivity().handleRequest(input, context);
        } catch (InvalidParameterException | CustomerNotFoundException | OutOfStockException | OrderNotFoundException | ProductDoesNotExistException e) {
            responseStatus = new ResponseStatus(400, e.getMessage());
            return new POSTCheckoutResult(null, responseStatus);
        } catch (AmazonDynamoDBException e) {
            responseStatus = new ResponseStatus(500, String.format(e.getMessage()));
            return new POSTCheckoutResult(null, responseStatus);
        }
    }
}
