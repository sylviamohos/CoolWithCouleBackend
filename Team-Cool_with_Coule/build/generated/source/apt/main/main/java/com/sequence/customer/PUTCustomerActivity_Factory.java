package main.java.com.sequence.customer;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PUTCustomerActivity_Factory implements Factory<PUTCustomerActivity> {
  private final Provider<CustomerDao> daoProvider;

  public PUTCustomerActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public PUTCustomerActivity get() {
    return new PUTCustomerActivity(daoProvider.get());
  }

  public static PUTCustomerActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new PUTCustomerActivity_Factory(daoProvider);
  }
}
