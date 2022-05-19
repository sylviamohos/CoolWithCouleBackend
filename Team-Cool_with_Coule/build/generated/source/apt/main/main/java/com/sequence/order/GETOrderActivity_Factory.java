package main.java.com.sequence.order;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.OrderDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETOrderActivity_Factory implements Factory<GETOrderActivity> {
  private final Provider<OrderDao> orderDaoProvider;

  public GETOrderActivity_Factory(Provider<OrderDao> orderDaoProvider) {
    this.orderDaoProvider = orderDaoProvider;
  }

  @Override
  public GETOrderActivity get() {
    return new GETOrderActivity(orderDaoProvider.get());
  }

  public static GETOrderActivity_Factory create(Provider<OrderDao> orderDaoProvider) {
    return new GETOrderActivity_Factory(orderDaoProvider);
  }
}
