package product;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import main.java.com.exception.ProductDoesNotExistException;
import main.java.com.obj.Product;
import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.product.activity.DELETEProductActivity;
import main.java.com.sequence.product.request.DELETEProductRequest;
import main.java.com.sequence.product.result.DELETEProductResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class DELETEProductActivityTest {

    @Mock
    private ProductDao dao;

    @InjectMocks
    private DELETEProductActivity deleteProductActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void handleRequest_validProductName_returnsProductModel() {
        // GIVEN
        String name = "valid name";
        String type = "valid type";
        String validUpc = "123";
        Integer quantity = 25;
        String description = "valid desc";
        Long priceInCents = 500L;
        String imageUrl = "www.coolImageUrl.com";

        DELETEProductRequest request = new DELETEProductRequest(name);
        Product product = new Product(name, type, validUpc, quantity, description, priceInCents, imageUrl);
        when(dao.getProductByName(name)).thenReturn(product);
        doNothing().when(dao).deleteProduct(product);

        // WHEN
        DELETEProductResult result = deleteProductActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getProduct().getName(), name);
    }

    @Test
    public void handleRequest_invalidProductName_throwsProductDoesNotExistsException() {
        // GIVEN
        String name = "invalid name";

        DELETEProductRequest request = new DELETEProductRequest(name);
        when(dao.getProductByName(name)).thenReturn(null);

        // WHEN + THEN
        assertThrows(ProductDoesNotExistException.class, () -> deleteProductActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_databaseError_throwsDynamoDBException() {
        // GIVEN
        String name = "valid name";
        String type = "valid type";
        String validUpc = "123";
        Integer quantity = 25;
        String description = "valid desc";
        Long priceInCents = 500L;
        String imageUrl = "www.couleImageUrl.com";
        Product product = new Product(name, type, validUpc, quantity, description, priceInCents, imageUrl);

        DELETEProductRequest request = new DELETEProductRequest(name);
        when(dao.getProductByName(name)).thenReturn(product);
        doThrow(new AmazonDynamoDBException("")).when(dao).deleteProduct(product);

        // WHEN + THEN
        assertThrows(AmazonDynamoDBException.class, () -> deleteProductActivity.handleRequest(request, null));
    }
}
