package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.CustomerAlreadyExistsException;
import main.java.com.exception.ProductAlreadyExistsException;
import main.java.com.obj.Product;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.request.POSTProductRequest;
import main.java.com.sequence.product.result.POSTProductResult;

import javax.inject.Inject;

public class POSTProductActivity implements RequestHandler<POSTProductRequest, POSTProductResult> {
    private final ProductDao dao;

    @Inject
    public POSTProductActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public POSTProductResult handleRequest(POSTProductRequest postProductRequest, Context context) {
        if (dao.getProductByName(postProductRequest.getName()) != null) {
            throw new ProductAlreadyExistsException();
        }
        Product product = new Product();
        product.setName(postProductRequest.getName());
        product.setType(postProductRequest.getType());
        product.setUpcCode(postProductRequest.getUpcCode());
        product.setQuantity(postProductRequest.getQuantity());
        product.setDescription(postProductRequest.getDescription());
        product.setPriceInCents(postProductRequest.getPriceInCents());
        product.setImageUrl(postProductRequest.getImageUrl());

        dao.saveProduct(product);
        ResponseStatus status = new ResponseStatus(200, "Success");

        return POSTProductResult.builder()
                .product(new ProductModel(product))
                .responseStatus(status)
                .build();
    }
}
