package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;

public class POSTCheckoutActivityProvider implements RequestHandler<POSTCheckoutRequest, POSTCheckoutResult> {

    ServiceComponent dagger = DaggerServiceComponent.create();

    @Override
    public POSTCheckoutResult handleRequest(POSTCheckoutRequest input, Context context) {
        return dagger.providePOSTCheckoutActivity().handleRequest(input, context);
    }
}
