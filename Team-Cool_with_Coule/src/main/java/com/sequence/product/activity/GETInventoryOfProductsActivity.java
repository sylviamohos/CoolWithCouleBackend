package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Product;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.product.provider.GETInventoryOfProductsActivityProvider;
import main.java.com.sequence.product.request.DELETEProductRequest;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.result.DELETEProductResult;
import main.java.com.sequence.product.result.GETInventoryOfProductsResult;

import javax.inject.Inject;

public class GETInventoryOfProductsActivity implements RequestHandler<GETInventoryOfProductsRequest, GETInventoryOfProductsResult>{
    private final ProductDao dao;

    @Inject
    public GETInventoryOfProductsActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public GETInventoryOfProductsResult handleRequest(GETInventoryOfProductsRequest getInventoryOfProductsRequest, Context context) {
        return null;
    }
}
