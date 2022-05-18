package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import main.java.com.obj.Product;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao {

    private final DynamoDBMapper mapper;
    @Inject
    public ProductDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Product getProductByName(String name) {
        return this.mapper.load(Product.class, name);
    }

    public List<Product> getProductByType(String type) {
        Product product = new Product();
        product.setType(type);
        DynamoDBQueryExpression<Product> queryExpression = new DynamoDBQueryExpression<Product>()
                .withHashKeyValues(product)
                .withConsistentRead(false)
                .withIndexName(Product.TYPE_INDEX);
        return new ArrayList<>(mapper.query(Product.class, queryExpression));
    }

    public List<Product> getAllProducts() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        return mapper.scan(Product.class, expression);
    }


    public Product saveProduct(Product product) {
        mapper.save(product);
        return product;
    }

    public void deleteProduct(Product product) {
        mapper.delete(product);
    }


}
