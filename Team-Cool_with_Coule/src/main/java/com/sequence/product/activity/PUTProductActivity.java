package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.exception.ProductDoesNotExistException;
import main.java.com.obj.Product;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.request.PUTProductRequest;
import main.java.com.sequence.product.result.PUTProductResult;

import javax.inject.Inject;
import java.security.InvalidParameterException;
import java.util.Optional;

public class PUTProductActivity implements RequestHandler<PUTProductRequest, PUTProductResult> {
    private final ProductDao dao;

    @Inject
    public PUTProductActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public PUTProductResult handleRequest(PUTProductRequest putProductRequest, Context context) {
        if (putProductRequest.getName() == null) {
            throw new InvalidParameterException();
        }
        Product oldProduct = dao.getProductByName(putProductRequest.getName());
        if (oldProduct == null) {
            throw new ProductDoesNotExistException();
        }
        Product newProduct = new Product();
        if (putProductRequest.getNewName() != null) {
            newProduct.setName(putProductRequest.getNewName());
            dao.deleteProduct(oldProduct);
        } else {
            newProduct.setName(oldProduct.getName());
        }
        newProduct.setType(Optional.ofNullable(putProductRequest.getType()).orElse(oldProduct.getType()));
        newProduct.setUpcCode(Optional.ofNullable(putProductRequest.getUpcCode()).orElse(oldProduct.getUpcCode()));
        newProduct.setQuantity(Optional.ofNullable(putProductRequest.getQuantity()).orElse(oldProduct.getQuantity()));
        newProduct.setDescription(Optional.ofNullable(putProductRequest.getDescription()).orElse(oldProduct.getDescription()));
        newProduct.setPriceInCents(Optional.ofNullable(putProductRequest.getPriceInCents()).orElse(oldProduct.getPriceInCents()));
        newProduct.setImageUrl(Optional.ofNullable(putProductRequest.getImageUrl()).orElse(oldProduct.getImageUrl()));

        dao.saveProduct(newProduct);
        ResponseStatus status = new ResponseStatus(200, "Success");

        return PUTProductResult.builder()
                .product(new ProductModel(newProduct))
                .responseStatus(status)
                .build();
    }
}
