package product;

import main.java.com.obj.dao.ProductDao;
import main.java.com.sequence.product.activity.POSTProductActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.openMocks;

public class POSTProductActivityTest {
    //TODO - POSTProductActivity is only used from an admin standpoint, currently out of scope

    @Mock
    ProductDao dao;

    @InjectMocks
    POSTProductActivity postProductActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void handleRequest_createProductActivity_returnsProductModel() {
        // implement me
    }
}
