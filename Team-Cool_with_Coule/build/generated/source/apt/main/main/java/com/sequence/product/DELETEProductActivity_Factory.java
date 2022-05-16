package main.java.com.sequence.product;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DELETEProductActivity_Factory implements Factory<DELETEProductActivity> {
  private final Provider<CustomerDao> daoProvider;

  public DELETEProductActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public DELETEProductActivity get() {
    return new DELETEProductActivity(daoProvider.get());
  }

  public static DELETEProductActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new DELETEProductActivity_Factory(daoProvider);
  }
}
