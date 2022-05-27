package product;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import main.java.com.obj.Product;
import main.java.com.obj.dao.ProductDao;
import main.java.com.obj.model.ProductModel;
import main.java.com.sequence.product.activity.PUTProductActivity;
import main.java.com.sequence.product.request.PUTProductRequest;
import main.java.com.sequence.product.result.PUTProductResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PUTProductActivityTest {

    @Mock
    ProductDao dao;

    @InjectMocks
    PUTProductActivity putProductActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void handleRequest_editProductDetailsExceptName_returnsEditedProduct() {
        // GIVEN
        String name = "valid name";
        String type = "old type";
        String newType = "new type";
        String oldUpcCode = "123";
        String newUpcCode = "456";
        Integer oldQuantity = 25;
        Integer newQuantity = 20;
        String oldDescription = "old desc";
        String newDescription = "new desc";
        Long oldPriceInCents = 500L;
        Long newPriceInCents = 1000L;
        String imageUrl = "www.couleImageUrl.com";
        Product oldProduct = new Product(name, type, oldUpcCode, oldQuantity, oldDescription, oldPriceInCents, imageUrl);
        Product newProduct = new Product(name, newType, newUpcCode, newQuantity, newDescription, newPriceInCents, imageUrl);
        ProductModel productModel = new ProductModel(newProduct);

        PUTProductRequest request = new PUTProductRequest(name, null, newType, newUpcCode, newQuantity, newDescription, newPriceInCents, null);
        when(dao.getProductByName(name)).thenReturn(oldProduct);
        doNothing().when(dao).saveProduct(newProduct);

        // WHEN
        PUTProductResult result = putProductActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getProduct(), productModel);
    }

    @Test
    public void handleRequest_editProductDetailsIncludingName_returnsEditedProduct() {
        // GIVEN
        String oldName = "old name";
        String newName = "new name";
        String type = "old type";
        String newType = "new type";
        String oldUpcCode = "123";
        String newUpcCode = "456";
        Integer oldQuantity = 25;
        Integer newQuantity = 20;
        String description = "very cool desc";
        Long oldPriceInCents = 500L;
        Long newPriceInCents = 1000L;
        String oldImageUrl = "www.coolImageUrl.com";
        String newImageUrl = "www.coolerImageUrl.com";
        Product oldProduct = new Product(oldName, type, oldUpcCode, oldQuantity, description, oldPriceInCents, oldImageUrl);
        Product newProduct = new Product(newName, newType, newUpcCode, newQuantity, description, newPriceInCents, newImageUrl);
        ProductModel productModel = new ProductModel(newProduct);
        PUTProductRequest request = new PUTProductRequest(oldName, newName, newType, newUpcCode, newQuantity, null, newPriceInCents, newImageUrl);
        when(dao.getProductByName(oldName)).thenReturn(oldProduct);
        doNothing().when(dao).deleteProduct(oldProduct);
        doNothing().when(dao).saveProduct(newProduct);

        // WHEN
        PUTProductResult result = putProductActivity.handleRequest(request, null);

        // THEN
        assertEquals(result.getResponseStatus().getCode(), 200);
        assertEquals(result.getProduct(), productModel);
    }

    @Test
    public void handleRequest_databaseError_throwsDynamoDBException() {
        // GIVEN
        String name = "*fh36fd7gfd[PO}!@#$%^&*()_";
        PUTProductRequest request = new PUTProductRequest(name, null, null, null, null, null, null, null);
        when(dao.getProductByName(name)).thenThrow(new AmazonDynamoDBException(""));

        // WHEN + THEN
        assertThrows(AmazonDynamoDBException.class, () -> putProductActivity.handleRequest(request, null));
    }
}
