package main.java.com.sequence.order;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.dependency.DaggerServiceComponent;
import main.java.com.dependency.ServiceComponent;
import lombok.*;

@NoArgsConstructor

public class GETOrderActivityProvider implements RequestHandler<GETOrderRequest, GETOrderResult> {

    ServiceComponent dagger = DaggerServiceComponent.create();

    @Override
    public GETOrderResult handleRequest(GETOrderRequest input, Context context) {

        return dagger.provideGETOrdersActivity().handleRequest(input, context);


    }

}
