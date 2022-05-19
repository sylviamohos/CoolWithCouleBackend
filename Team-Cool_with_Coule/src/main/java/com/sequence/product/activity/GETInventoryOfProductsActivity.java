package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Product;
import main.java.com.obj.ResponseStatus;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.result.GETInventoryOfProductsResult;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GETInventoryOfProductsActivity implements RequestHandler<GETInventoryOfProductsRequest, GETInventoryOfProductsResult>{
    private final ProductDao dao;

    @Inject
    public GETInventoryOfProductsActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public GETInventoryOfProductsResult handleRequest(GETInventoryOfProductsRequest getInventoryOfProductsRequest, Context context) {
        List<Product> productList = dao.getAllProducts();
        List<ProductModel> productModelList = new ArrayList<>();
        for (Product product : productList) {
            productModelList.add(new ProductModel(product));
        }
        ResponseStatus status = new ResponseStatus(200, "Success");


        return GETInventoryOfProductsResult.builder()
                .products(productModelList)
                .responseStatus(status)
                .build();
    }
}
