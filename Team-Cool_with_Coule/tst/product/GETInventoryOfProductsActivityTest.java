package product;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import main.java.com.obj.Product;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.activity.GETInventoryOfProductsActivity;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.result.GETInventoryOfProductsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GETInventoryOfProductsActivityTest {

    @Mock
    private ProductDao dao;

    @InjectMocks
    private GETInventoryOfProductsActivity getInventoryOfProductsActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void handleRequest_getInventoryRequest_returnsProductList() {
        // GIVEN
        Product product1 = new Product();
        product1.setName("product1");
        Product product2 = new Product();
        product2.setName("product2");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(new ProductModel(product1));
        productModelList.add(new ProductModel(product2));
        GETInventoryOfProductsRequest request = new GETInventoryOfProductsRequest();
        when(dao.getAllProducts()).thenReturn(productList);

        // WHEN
        GETInventoryOfProductsResult result = getInventoryOfProductsActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getProducts(), productModelList);
    }

    @Test
    public void handleRequest_databaseError_throwsDynamoDBException() {
        // GIVEN
        GETInventoryOfProductsRequest request = new GETInventoryOfProductsRequest();
        when(dao.getAllProducts()).thenThrow(new AmazonDynamoDBException(""));

        // WHEN + THEN
        assertThrows(AmazonDynamoDBException.class, () -> getInventoryOfProductsActivity.handleRequest(request, null));
    }

}
