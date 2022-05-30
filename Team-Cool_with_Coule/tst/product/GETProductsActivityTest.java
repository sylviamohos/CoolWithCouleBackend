package product;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import main.java.com.obj.Product;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.activity.GETProductsActivity;
import main.java.com.sequence.product.request.GETProductsRequest;
import main.java.com.sequence.product.result.GETProductsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GETProductsActivityTest {

    @Mock
    ProductDao dao;

    @InjectMocks
    GETProductsActivity getProductsActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void handleRequest_getProductsWithName_returnsListOfProducts() {
        // GIVEN
        Product product1 = new Product();
        product1.setName("cool name 1");
        Product product2 = new Product();
        product2.setName("cool name 2");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(new ProductModel(product1));
        productModelList.add(new ProductModel(product2));
        String type = "";
        String name = "cool name";
        GETProductsRequest request = new GETProductsRequest(type, name);
        when(dao.getProducts(type, name)).thenReturn(productList);

        // WHEN
        GETProductsResult result = getProductsActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getProducts(), productModelList);
    }

    @Test
    public void handleRequest_getProductsWithType_returnsListOfProducts() {
        // GIVEN
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setName("cool name 1");
        product1.setType("coule type");
        product2.setName("cool name 2");
        product2.setType("coule type");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(new ProductModel(product1));
        productModelList.add(new ProductModel(product2));
        String type = "coule type";
        String name = "";
        GETProductsRequest request = new GETProductsRequest(type, name);
        when(dao.getProducts(type, name)).thenReturn(productList);

        // WHEN
        GETProductsResult result = getProductsActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getProducts(), productModelList);
    }

    @Test
    public void handleRequest_getProductsWithNoNameOrType_throwsInvalidParameterException() {
        // GIVEN
        GETProductsRequest request = new GETProductsRequest(null, null);

        // WHEN + THEN
        assertThrows(InvalidParameterException.class, () -> getProductsActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_databaseError_throwsDynamoDBException() {
        // GIVEN
        String name = "*fh36fd7gfd[PO}!@#$%^&*()_";
        GETProductsRequest request = new GETProductsRequest(null, name);
        when(dao.getProducts(null, name)).thenThrow(new AmazonDynamoDBException(""));

        // WHEN + THEN
        assertThrows(AmazonDynamoDBException.class, () -> getProductsActivity.handleRequest(request, null));
    }
}