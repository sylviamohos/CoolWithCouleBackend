package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import lombok.*;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@NoArgsConstructor

public class POSTCustomerActivityProvider implements RequestHandler<POSTCustomerRequest, POSTCustomerResult> {

    ServiceComponent dagger = DaggerServiceComponent.create();

    @Override
    public POSTCustomerResult handleRequest(POSTCustomerRequest input, Context context) {
       try {
           return dagger.providePOSTCustomerActivity().handleRequest(input, context);
       } catch (DynamoDbException e) {
           ResponseStatus status = new ResponseStatus(500, "[ERROR] database error");
           return new POSTCustomerResult(null, status);
       } catch (RuntimeException e) {
           ResponseStatus status = new ResponseStatus(400, "[Error] RuntimeError");
           return new POSTCustomerResult(null, status);
       }
    }
}
