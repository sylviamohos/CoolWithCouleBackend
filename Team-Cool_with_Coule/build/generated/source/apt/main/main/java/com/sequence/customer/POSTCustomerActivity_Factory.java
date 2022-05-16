package main.java.com.sequence.customer;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class POSTCustomerActivity_Factory implements Factory<POSTCustomerActivity> {
  private final Provider<CustomerDao> daoProvider;

  public POSTCustomerActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public POSTCustomerActivity get() {
    return new POSTCustomerActivity(daoProvider.get());
  }

  public static POSTCustomerActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new POSTCustomerActivity_Factory(daoProvider);
  }
}
