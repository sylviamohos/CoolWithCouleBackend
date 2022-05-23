package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
        name = name.toLowerCase();
        return this.mapper.load(Product.class, name);
    }

    public List<Product> getProducts(String type, String name) {
        Product product = new Product();
        product.setType(type);
        product.setName(name);

        Map<String, AttributeValue> valueMap = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();
        if (type != null) {
            nameMap.put("#type", "type");
            valueMap.put(":type", new AttributeValue().withS(type));
        }
        if (name != null) {
            nameMap.put("#name", "name");
            valueMap.put(":name", new AttributeValue().withS(name));
        }

        String filter;
        if (type != null && name == null) {
            filter = "#type = :type";
        } else if (type == null && name != null) {
            filter = "contains(#name, :name)";
        } else {
            filter = "#type = :type AND contains(#name, :name)";
        }

        DynamoDBScanExpression expression = new DynamoDBScanExpression()
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
                .withFilterExpression(filter)
                .withConsistentRead(false);

        List<Product> result = mapper.scan(Product.class, expression);
        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    public List<Product> getProducts(List<String> productNames) {
        List<Product> products = new ArrayList<>();
        for (String s : productNames) {
            Product product = new Product();
            product.setName(s.toLowerCase());
            products.add(product);
        }

        Map<String, List<Object>> loadResult = mapper.batchLoad(products);
        List<Product> result = new ArrayList<>();
        for (List<Object> list : loadResult.values()) {
            for (Object o : list) {
                result.add((Product) o);
            }
        }
        return result;
    }

    public List<Product> getAllProducts() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        List<Product> products = mapper.scan(Product.class, expression);
        if (products == null) {
            products = new ArrayList<>();
        }
        return products;
    }


    public void saveProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        product.setType(product.getType().toLowerCase());
        mapper.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        mapper.batchSave(products);
        return products;
    }

    public void deleteProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        mapper.delete(product);
    }


}
