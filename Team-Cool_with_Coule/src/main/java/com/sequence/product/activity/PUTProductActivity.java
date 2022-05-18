package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.product.request.GETProductByTypeRequest;
import main.java.com.sequence.product.request.PUTProductRequest;
import main.java.com.sequence.product.result.GETProductByTypeResult;
import main.java.com.sequence.product.result.PUTProductResult;

import javax.inject.Inject;

public class PUTProductActivity implements RequestHandler<PUTProductRequest, PUTProductResult> {
    private final ProductDao dao;

    @Inject
    public PUTProductActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public PUTProductResult handleRequest(PUTProductRequest putProductRequest, Context context) {
        return null;
    }
}
