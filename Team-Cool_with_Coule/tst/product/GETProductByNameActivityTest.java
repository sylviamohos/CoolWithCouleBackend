package product;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import main.java.com.exception.ProductDoesNotExistException;
import main.java.com.obj.Product;
import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.product.activity.GETProductByNameActivity;
import main.java.com.sequence.product.request.DELETEProductRequest;
import main.java.com.sequence.product.request.GETInventoryOfProductsRequest;
import main.java.com.sequence.product.request.GETProductByNameRequest;
import main.java.com.sequence.product.result.GETProductByNameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GETProductByNameActivityTest {

    @Mock
    ProductDao dao;

    @InjectMocks
    GETProductByNameActivity getProductByNameActivity;

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
        String imageUrl = "www.goodImageUrl.com";

        GETProductByNameRequest request = new GETProductByNameRequest(name);
        Product product = new Product(name, type, validUpc, quantity, description, priceInCents, imageUrl);
        when(dao.getProductByName(name)).thenReturn(product);

        // WHEN
        GETProductByNameResult result = getProductByNameActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getProduct().getName(), name);
    }

    @Test
    public void handleRequest_invalidProductName_throwsProductDoesNotExistsException() {
        // GIVEN
        String name = "invalid name";

        GETProductByNameRequest request = new GETProductByNameRequest(name);
        when(dao.getProductByName(name)).thenReturn(null);

        // WHEN + THEN
        assertThrows(ProductDoesNotExistException.class, () -> getProductByNameActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_databaseError_throwsDynamoDBException() {
        // GIVEN
        String name = "&*fh36fd7gfd[PO}";
        GETProductByNameRequest request = new GETProductByNameRequest(name);
        when(dao.getProductByName(name)).thenThrow(new AmazonDynamoDBException(""));

        // WHEN + THEN
        assertThrows(AmazonDynamoDBException.class, () -> getProductByNameActivity.handleRequest(request, null));
    }

}
