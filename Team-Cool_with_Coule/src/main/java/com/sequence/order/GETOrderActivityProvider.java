package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import lombok.*;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@NoArgsConstructor

public class GETOrderActivityProvider implements RequestHandler<GETOrderRequest, GETOrderResult> {

    ServiceComponent dagger = DaggerServiceComponent.create();
    ResponseStatus responseStatus;

    @Override
    public GETOrderResult handleRequest(GETOrderRequest input, Context context) {
        try {
            return dagger.provideGETOrderActivity().handleRequest(input, context);
        } catch (OrderNotFoundException e) {
            responseStatus = new ResponseStatus(400, String.format("[ERROR] order: {} not found! ", input.getOrderId()));
            return new GETOrderResult(null, responseStatus);
        } catch ( DynamoDbException e) {
            responseStatus = new ResponseStatus(500, "[ERROR] Database encountered an error!");
            return new GETOrderResult(null, responseStatus);
        }
    }

}
