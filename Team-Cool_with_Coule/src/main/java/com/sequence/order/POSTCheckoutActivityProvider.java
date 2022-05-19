package main.java.com.sequence.order;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.exception.OutOfStockException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class POSTCheckoutActivityProvider implements RequestHandler<POSTCheckoutRequest, POSTCheckoutResult> {

    ServiceComponent dagger = DaggerServiceComponent.create();
    ResponseStatus responseStatus;

    @Override
    public POSTCheckoutResult handleRequest(POSTCheckoutRequest input, Context context) {
        try {
            return dagger.providePOSTCheckoutActivity().handleRequest(input, context);
        } catch (CustomerNotFoundException e){
            responseStatus = new ResponseStatus(400, String.format("customer id: {} not found!))", input.getCustomerId()));
            return new POSTCheckoutResult(null, responseStatus);
        } catch (OutOfStockException e) {
            responseStatus = new ResponseStatus(400, String.format("product name: {} not found!))",e.getMessage()));
            return new POSTCheckoutResult(null, responseStatus);
        } catch (OrderNotFoundException e) {
            responseStatus = new ResponseStatus(400, String.format("order id: {} not found!))", e.getMessage()));
            return new POSTCheckoutResult(null, responseStatus);
        } catch (AmazonDynamoDBException e) {
            responseStatus = new ResponseStatus(500, String.format("[ERROR] Database encountered an error."));
            return new POSTCheckoutResult(null, responseStatus);
        }
    }
}
