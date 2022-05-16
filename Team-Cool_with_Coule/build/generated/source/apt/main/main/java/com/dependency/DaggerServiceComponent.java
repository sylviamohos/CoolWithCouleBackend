package main.java.com.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.sequence.customer.DELETECustomerActivity;
import main.java.com.sequence.customer.GETCustomerByEmailActivity;
import main.java.com.sequence.customer.GETCustomerByIdActivity;
import main.java.com.sequence.customer.POSTCustomerActivity;
import main.java.com.sequence.customer.PUTCustomerActivity;
import main.java.com.sequence.order.GETOrdersActivity;
import main.java.com.sequence.order.POSTCheckoutActivity;
import main.java.com.sequence.product.DELETEProductActivity;
import main.java.com.sequence.product.GETInventoryOfProductsActivity;
import main.java.com.sequence.product.GETProductActivity;
import main.java.com.sequence.product.POSTProductActivity;
import main.java.com.sequence.product.PUTProductActivity;
import main.java.com.sequence.sample.DELETESampleActivity;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerServiceComponent implements ServiceComponent {
  private Provider<DynamoDBMapper> provideDynamoDbMapperProvider;

  private DaggerServiceComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServiceComponent create() {
    return new Builder().build();
  }

  private CustomerDao getCustomerDao() {
    return new CustomerDao(provideDynamoDbMapperProvider.get());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideDynamoDbMapperProvider =
        DoubleCheck.provider(
            DependencyModule_ProvideDynamoDbMapperFactory.create(builder.dependencyModule));
  }

  @Override
  public DELETECustomerActivity provideDELTECustomerActivity() {
    return new DELETECustomerActivity(getCustomerDao());
  }

  @Override
  public GETCustomerByEmailActivity provideGETCustomerByEmailActivity() {
    return new GETCustomerByEmailActivity(getCustomerDao());
  }

  @Override
  public GETCustomerByIdActivity provideGETCustomerByIdActivity() {
    return new GETCustomerByIdActivity(getCustomerDao());
  }

  @Override
  public POSTCustomerActivity providePOSTCustomerActivity() {
    return new POSTCustomerActivity(getCustomerDao());
  }

  @Override
  public PUTCustomerActivity providePUTCustomerActivity() {
    return new PUTCustomerActivity(getCustomerDao());
  }

  @Override
  public GETOrdersActivity provideGETOrdersActivity() {
    return new GETOrdersActivity(getCustomerDao());
  }

  @Override
  public POSTCheckoutActivity providePOSTCheckoutActivity() {
    return new POSTCheckoutActivity(getCustomerDao());
  }

  @Override
  public DELETEProductActivity provideDELETEProductActivity() {
    return new DELETEProductActivity(getCustomerDao());
  }

  @Override
  public GETInventoryOfProductsActivity provideGETInventoryOfProductsActivity() {
    return new GETInventoryOfProductsActivity(getCustomerDao());
  }

  @Override
  public GETProductActivity provideGETProductActivity() {
    return new GETProductActivity(getCustomerDao());
  }

  @Override
  public POSTProductActivity providePOSTProductActivity() {
    return new POSTProductActivity(getCustomerDao());
  }

  @Override
  public PUTProductActivity providePUTProductActivity() {
    return new PUTProductActivity(getCustomerDao());
  }

  @Override
  public DELETESampleActivity provideDELETESampleActivity() {
    return new DELETESampleActivity(getCustomerDao());
  }

  public static final class Builder {
    private DependencyModule dependencyModule;

    private Builder() {}

    public ServiceComponent build() {
      if (dependencyModule == null) {
        this.dependencyModule = new DependencyModule();
      }
      return new DaggerServiceComponent(this);
    }

    public Builder dependencyModule(DependencyModule dependencyModule) {
      this.dependencyModule = Preconditions.checkNotNull(dependencyModule);
      return this;
    }
  }
}
