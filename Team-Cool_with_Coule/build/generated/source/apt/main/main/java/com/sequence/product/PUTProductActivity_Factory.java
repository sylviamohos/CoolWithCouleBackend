package main.java.com.sequence.product;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PUTProductActivity_Factory implements Factory<PUTProductActivity> {
  private final Provider<CustomerDao> daoProvider;

  public PUTProductActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public PUTProductActivity get() {
    return new PUTProductActivity(daoProvider.get());
  }

  public static PUTProductActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new PUTProductActivity_Factory(daoProvider);
  }
}
