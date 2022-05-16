package main.java.com.sequence.sample;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;

public class DELETESampleActivityProvider implements RequestHandler<DELETESampleRequest, DELETESampleResult> {
    @Override
    public DELETESampleResult handleRequest(DELETESampleRequest input, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger.provideDELETESampleActivity().handleRequest(input, context);
    }
}
