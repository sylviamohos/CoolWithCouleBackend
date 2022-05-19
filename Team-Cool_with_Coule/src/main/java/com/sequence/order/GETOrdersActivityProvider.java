package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.OrderNotFoundException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class GETOrdersActivityProvider implements RequestHandler<GETOrdersRequest, GETOrdersResult> {

    ServiceComponent dagger = DaggerServiceComponent.create();
    ResponseStatus responseStatus;

    @Override
    public GETOrdersResult handleRequest(GETOrdersRequest input, Context context) {
        try {
            return dagger.provideGETOrdersActivity().handleRequest(input, context);
        } catch (OrderNotFoundException e) {
            responseStatus = new ResponseStatus(400, String.format("[ERROR] order: {} not found! ", input.getOrderId()));
            return new GETOrdersResult(null, input.getOrderId(), responseStatus, input.getProductNames());
        } catch ( DynamoDbException e) {
            responseStatus = new ResponseStatus(500, "[ERROR] Database encountered an error!");
            return new GETOrdersResult(null, input.getOrderId(), responseStatus, input.getProductNames());
        } catch (RuntimeException e) {
            responseStatus = new ResponseStatus(400, "[ERROR] Runtime error.");
            return new GETOrdersResult(null, input.getOrderId(), responseStatus, input.getProductNames());
        }


}
}
