package main.java.com.sequence.customer;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.obj.ResponseStatus;
import main.java.com.sequence.sample.DELETESampleRequest;
import main.java.com.sequence.sample.DELETESampleResult;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

/**
 * The purpose of this class is to handle the request to delete a customer from the database
 */
public class DELETECustomerActivityProvider implements RequestHandler<DELETECustomerRequest, DELETECustomerResult> {

    /**
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @exception CustomerNotFoundException is caught if the customer is not found in the database
     *   will create a ResponseStatus 400 to indicate the customer is not found
     * @exception AmazonDynamoDBException is caught if the DynamoDB experiences a problem
     *   will create a ResponseStatus 500 to indicate that the database is having an error
     * @return - a new Delete Customer Result
     */
    @Override
    public DELETECustomerResult handleRequest(DELETECustomerRequest input, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.provideDELTECustomerActivity().handleRequest(input, context);
        } catch (CustomerNotFoundException e) {
            ResponseStatus status = new ResponseStatus(400, "Customer not found.");
            return new DELETECustomerResult(null, status);
        } catch (AmazonDynamoDBException e) {
            ResponseStatus status = new ResponseStatus(500, "Error, try again");
            return new DELETECustomerResult(null, status);

        }
    }
}
