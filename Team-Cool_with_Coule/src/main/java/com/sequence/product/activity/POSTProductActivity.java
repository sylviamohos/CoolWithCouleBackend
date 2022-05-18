package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Product;
import main.java.com.obj.dao.ProductDao;
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
        Product product = new Product();
        product.setName(postProductRequest.getName());
        product.setType(postProductRequest.getType());
        product.setUpcCode(postProductRequest.getUpcCode());
        product.setQuantity(product.getQuantity());
        product.setDescription(postProductRequest.getDescription());
        product.setPriceInCents(postProductRequest.getPriceInCents());
        product.setImageUrl(postProductRequest.getImageUrl());
        dao.saveProduct(product);
        return POSTProductResult.builder()
                //.product(product)
                .build();
        //return null;
    }
}
