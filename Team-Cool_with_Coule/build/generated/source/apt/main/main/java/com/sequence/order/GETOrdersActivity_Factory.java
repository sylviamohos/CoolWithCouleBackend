package main.java.com.sequence.order;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;
import main.java.com.obj.dao.OrderDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETOrdersActivity_Factory implements Factory<GETOrdersActivity> {
  private final Provider<OrderDao> orderDaoProvider;

  private final Provider<CustomerDao> customerDaoProvider;

  public GETOrdersActivity_Factory(
      Provider<OrderDao> orderDaoProvider, Provider<CustomerDao> customerDaoProvider) {
    this.orderDaoProvider = orderDaoProvider;
    this.customerDaoProvider = customerDaoProvider;
  }

  @Override
  public GETOrdersActivity get() {
    return new GETOrdersActivity(orderDaoProvider.get(), customerDaoProvider.get());
  }

  public static GETOrdersActivity_Factory create(
      Provider<OrderDao> orderDaoProvider, Provider<CustomerDao> customerDaoProvider) {
    return new GETOrdersActivity_Factory(orderDaoProvider, customerDaoProvider);
  }
}
