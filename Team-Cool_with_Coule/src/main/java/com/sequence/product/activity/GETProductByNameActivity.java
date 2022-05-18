package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.request.GETProductByNameRequest;
import main.java.com.sequence.product.result.GETInventoryOfProductsResult;
import main.java.com.sequence.product.result.GETProductByNameResult;

import javax.inject.Inject;

public class GETProductByNameActivity implements RequestHandler<GETProductByNameRequest, GETProductByNameResult> {
    private final ProductDao dao;

    @Inject
    public GETProductByNameActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public GETProductByNameResult handleRequest(GETProductByNameRequest getProductByNameRequest, Context context) {
        return null;
    }
}
