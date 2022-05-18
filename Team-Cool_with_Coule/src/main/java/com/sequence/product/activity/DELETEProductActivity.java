package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Product;
import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.product.request.DELETEProductRequest;
import main.java.com.sequence.product.result.DELETEProductResult;

import javax.inject.Inject;

public class DELETEProductActivity implements RequestHandler<DELETEProductRequest, DELETEProductResult> {
    private final ProductDao dao;

    @Inject
    public DELETEProductActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public DELETEProductResult handleRequest(DELETEProductRequest deleteProductRequest, Context context) {

        Product product = dao.getProductByName(deleteProductRequest.getName());
        dao.deleteProduct(product);

        return null;
    }
}
