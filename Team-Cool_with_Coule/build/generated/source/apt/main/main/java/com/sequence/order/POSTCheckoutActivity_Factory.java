package main.java.com.sequence.order;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;
import main.java.com.obj.dao.ProductDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class POSTCheckoutActivity_Factory implements Factory<POSTCheckoutActivity> {
  private final Provider<OrderDao> orderDaoProvider;

  private final Provider<CustomerDao> customerDaoProvider;

  private final Provider<ProductDao> productDaoProvider;

  public POSTCheckoutActivity_Factory(
      Provider<OrderDao> orderDaoProvider,
      Provider<CustomerDao> customerDaoProvider,
      Provider<ProductDao> productDaoProvider) {
    this.orderDaoProvider = orderDaoProvider;
    this.customerDaoProvider = customerDaoProvider;
    this.productDaoProvider = productDaoProvider;
  }

  @Override
  public POSTCheckoutActivity get() {
    return new POSTCheckoutActivity(
        orderDaoProvider.get(), customerDaoProvider.get(), productDaoProvider.get());
  }

  public static POSTCheckoutActivity_Factory create(
      Provider<OrderDao> orderDaoProvider,
      Provider<CustomerDao> customerDaoProvider,
      Provider<ProductDao> productDaoProvider) {
    return new POSTCheckoutActivity_Factory(
        orderDaoProvider, customerDaoProvider, productDaoProvider);
  }
}
