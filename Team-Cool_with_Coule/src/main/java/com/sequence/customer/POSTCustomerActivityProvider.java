package main.java.com.sequence.customer;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import lombok.*;
import main.java.com.exception.CustomerAlreadyExistsException;
import main.java.com.obj.ResponseStatus;

import java.security.InvalidParameterException;

@NoArgsConstructor

public class POSTCustomerActivityProvider implements RequestHandler<POSTCustomerRequest, POSTCustomerResult> {

    ServiceComponent dagger = DaggerServiceComponent.create();

    @Override
    public POSTCustomerResult handleRequest(POSTCustomerRequest input, Context context) {
       try {
           return dagger.providePOSTCustomerActivity().handleRequest(input, context);
       } catch (InvalidParameterException e) {
           ResponseStatus status = new ResponseStatus(400, "[ERROR] attribute cannot be null.");
           return new POSTCustomerResult(null, status);
       } catch (CustomerAlreadyExistsException e) {
            ResponseStatus status = new ResponseStatus(400, "[ERROR] credentials already taken.");
            return new POSTCustomerResult(null, status);
       } catch (AmazonDynamoDBException e) {
           ResponseStatus status = new ResponseStatus(500, e.getMessage());
           return new POSTCustomerResult(null, status);
       }
    }
}
