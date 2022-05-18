package main.java.com.dependency;

import dagger.Component;
import main.java.com.sequence.customer.*;
import main.java.com.sequence.order.GETOrdersActivity;
import main.java.com.sequence.order.POSTCheckoutActivity;
import main.java.com.sequence.product.activity.*;
import main.java.com.sequence.sample.DELETESampleActivity;

import javax.inject.Singleton;

@Component (modules = {DependencyModule.class})
@Singleton
public interface ServiceComponent {

    DELETECustomerActivity provideDELTECustomerActivity();
    GETCustomerByEmailActivity provideGETCustomerByEmailActivity();
    GETCustomerByIdActivity provideGETCustomerByIdActivity();
    POSTCustomerActivity providePOSTCustomerActivity();
    PUTCustomerActivity providePUTCustomerActivity();

    GETOrdersActivity provideGETOrdersActivity();
    POSTCheckoutActivity providePOSTCheckoutActivity();

    DELETEProductActivity provideDELETEProductActivity();
    GETInventoryOfProductsActivity provideGETInventoryOfProductsActivity();
    GETProductByNameActivity provideGETProductActivity();
    POSTProductActivity providePOSTProductActivity();
    PUTProductActivity providePUTProductActivity();


    DELETESampleActivity provideDELETESampleActivity();
}
