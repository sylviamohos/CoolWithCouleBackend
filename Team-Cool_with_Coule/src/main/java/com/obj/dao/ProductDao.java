package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import main.java.com.obj.Product;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
        //TODO - Talk with Ben to see if this works.  Need HashKey and not IndexHashKey?
        DynamoDBQueryExpression<Product> queryExpression = new DynamoDBQueryExpression<Product>()
                .withHashKeyValues(product)
                .withConsistentRead(false)
                .withIndexName(type);

        return new ArrayList<>(mapper.query(Product.class, queryExpression));
    }


    public Product saveProduct(Product product) {
        mapper.save(product);
        return product;
    }

    public void deleteProduct(Product product) {
        mapper.delete(product);
    }

}
