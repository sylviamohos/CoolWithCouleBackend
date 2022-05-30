package main.java.com.sequence.sample;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class DELETESampleActivity implements RequestHandler<DELETESampleRequest, DELETESampleResult> {
    private final CustomerDao dao;

    @Inject
    public DELETESampleActivity(CustomerDao dao) {
        this.dao = dao;
    }

    @Override
    public DELETESampleResult handleRequest(DELETESampleRequest input, Context context) {
        return new DELETESampleResult();
    }
}
