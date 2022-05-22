package main.java.com.sequence.order;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.security.InvalidParameterException;

public class GETOrdersActivityProvider implements RequestHandler<GETOrdersRequest, GETOrdersResult> {


    ResponseStatus responseStatus;

    @Override
    public GETOrdersResult handleRequest(GETOrdersRequest input, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.provideGETOrdersActivity().handleRequest(input, context);
        } catch (InvalidParameterException e) {
            responseStatus = new ResponseStatus(400, e.getMessage());
            return new GETOrdersResult(null, responseStatus);
        } catch (CustomerNotFoundException e) {
            responseStatus = new ResponseStatus(400, e.getMessage());
            return new GETOrdersResult(null, responseStatus);
        } catch (AmazonDynamoDBException e) {
            responseStatus = new ResponseStatus(500, "[ERROR] Database encountered an error!");
            return new GETOrdersResult(null, responseStatus);
        }
    }
}
