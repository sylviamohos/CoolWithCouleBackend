package main.java.com.sequence.product.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.obj.Product;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.request.GETProductByNameRequest;
import main.java.com.sequence.product.request.GETProductByTypeRequest;
import main.java.com.sequence.product.result.GETProductByNameResult;
import main.java.com.sequence.product.result.GETProductByTypeResult;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GETProductByTypeActivity implements RequestHandler<GETProductByTypeRequest, GETProductByTypeResult> {

    private final ProductDao dao;

    @Inject
    public GETProductByTypeActivity(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public GETProductByTypeResult handleRequest(GETProductByTypeRequest getProductByTypeRequest, Context context) {
        List<Product> productList = dao.getProductByType(getProductByTypeRequest.getType());
        List<ProductModel> productModelList = new ArrayList<>();
        for (Product product : productList) {
            productModelList.add(new ProductModel(product));
        }
        return GETProductByTypeResult.builder()
                .products(productModelList)
                .build();
    }
}
