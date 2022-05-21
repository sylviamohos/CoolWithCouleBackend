package main.java.com.sequence.customer;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.exception.CustomerNotFoundException;
import main.java.com.exception.InvalidEmailException;
import main.java.com.exception.PasswordDoesNotMatchException;
import main.java.com.obj.ResponseStatus;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.security.InvalidParameterException;

/**
 * The purpose of this class is to provide a customer by their email address from the database
 */
public class GETCustomerByEmailActivityProvider implements RequestHandler<GETCustomerByEmailRequest, GETCustomerByEmailResult> {
    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return - a new Get Customer By Email Result
     * @throws CustomerNotFoundException is caught if the customer's email is not found in the database
     *                                   will create a ResponseStatus 400 to indicate the customer is not found
     * @throws AmazonDynamoDBException   is caught if the DynamoDB experiences a problem
     *                                   will create a ResponseStatus 500 to indicate that the database is having an error
     */
    @Override
    public GETCustomerByEmailResult handleRequest(GETCustomerByEmailRequest input, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        try {
            return dagger.provideGETCustomerByEmailActivity().handleRequest(input, context);
        } catch (CustomerNotFoundException e) {
            ResponseStatus status = new ResponseStatus(400, "Email or password incorrect.");
            return new GETCustomerByEmailResult(null, status);
        } catch (InvalidParameterException e) {
            ResponseStatus status = new ResponseStatus(400, "Invalid email");
            return new GETCustomerByEmailResult(null, status);
        } catch (PasswordDoesNotMatchException e) {
            ResponseStatus status = new ResponseStatus(400, "The provided password does not match");
            return new GETCustomerByEmailResult(null, status);
        } catch (AmazonDynamoDBException e) {
            ResponseStatus status = new ResponseStatus(500, "Error, try again");
            return new GETCustomerByEmailResult(null, status);
        }
    }
}
