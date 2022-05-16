package main.java.com.sequence.product;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class POSTProductActivity_Factory implements Factory<POSTProductActivity> {
  private final Provider<CustomerDao> daoProvider;

  public POSTProductActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public POSTProductActivity get() {
    return new POSTProductActivity(daoProvider.get());
  }

  public static POSTProductActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new POSTProductActivity_Factory(daoProvider);
  }
}
