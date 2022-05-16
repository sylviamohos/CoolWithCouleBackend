package main.java.com.sequence.product;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETProductActivity_Factory implements Factory<GETProductActivity> {
  private final Provider<CustomerDao> daoProvider;

  public GETProductActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GETProductActivity get() {
    return new GETProductActivity(daoProvider.get());
  }

  public static GETProductActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new GETProductActivity_Factory(daoProvider);
  }
}
