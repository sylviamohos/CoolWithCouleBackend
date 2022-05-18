package main.java.com.sequence.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import main.java.com.sequence.sample.DELETESampleRequest;
import main.java.com.sequence.sample.DELETESampleResult;

public class DELETECustomerActivityProvider implements RequestHandler<DELETECustomerRequest, DELETECustomerResult> {

    @Override
    public DELETECustomerResult handleRequest(DELETECustomerRequest input, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        //return dagger.provideDELETECustomerActivity().handleRequest(input, context);
        return null;
    }
}
