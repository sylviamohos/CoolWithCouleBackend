package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.ProductDoesNotExistException;
import main.java.com.obj.Product;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.request.GETProductByNameRequest;
import main.java.com.sequence.product.result.GETInventoryOfProductsResult;
import main.java.com.sequence.product.result.GETProductByNameResult;
import main.java.com.sequence.product.result.POSTProductResult;

import javax.inject.Inject;
import java.security.InvalidParameterException;
import java.util.Locale;

public class GETProductByNameActivity implements RequestHandler<GETProductByNameRequest, GETProductByNameResult> {
    private final ProductDao dao;

    @Inject
    public GETProductByNameActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public GETProductByNameResult handleRequest(GETProductByNameRequest getProductByNameRequest, Context context) {
        if (getProductByNameRequest.getName() == null) {
            throw new InvalidParameterException();
        }
        Product product = dao.getProductByName(getProductByNameRequest.getName());
        if (product == null) {
            throw new ProductDoesNotExistException();
        }
        ResponseStatus status = new ResponseStatus(200, "Success");

        return GETProductByNameResult.builder()
                .product(new ProductModel(product))
                .responseStatus(status)
                .build();
    }
}