package main.java.com.sequence.order;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETOrdersActivity_Factory implements Factory<GETOrdersActivity> {
  private final Provider<CustomerDao> daoProvider;

  public GETOrdersActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GETOrdersActivity get() {
    return new GETOrdersActivity(daoProvider.get());
  }

  public static GETOrdersActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new GETOrdersActivity_Factory(daoProvider);
  }
}
