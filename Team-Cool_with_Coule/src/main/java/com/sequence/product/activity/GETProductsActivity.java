package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Product;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.request.GETProductsRequest;
import main.java.com.sequence.product.result.GETProductsResult;

import javax.inject.Inject;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class GETProductsActivity implements RequestHandler<GETProductsRequest, GETProductsResult> {

    private final ProductDao dao;

    @Inject
    public GETProductsActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public GETProductsResult handleRequest(GETProductsRequest getProductsRequest, Context context) {
        if (getProductsRequest.getType() == null && getProductsRequest.getName() == null) {
            throw new InvalidParameterException();
        }
        List<Product> productList = dao.getProducts(getProductsRequest.getType(), getProductsRequest.getName());
        List<ProductModel> productModelList = new ArrayList<>();
        for (Product product : productList) {
            productModelList.add(new ProductModel(product));
        }
        ResponseStatus status = new ResponseStatus(200, "Success");

        return GETProductsResult.builder()
                .products(productModelList)
                .responseStatus(status)
                .build();
    }
}
