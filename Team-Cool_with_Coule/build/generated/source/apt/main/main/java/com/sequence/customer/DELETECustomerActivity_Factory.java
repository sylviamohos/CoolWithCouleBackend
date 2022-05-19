package main.java.com.sequence.customer;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DELETECustomerActivity_Factory implements Factory<DELETECustomerActivity> {
  private final Provider<CustomerDao> daoProvider;

  public DELETECustomerActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public DELETECustomerActivity get() {
    return new DELETECustomerActivity(daoProvider.get());
  }

  public static DELETECustomerActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new DELETECustomerActivity_Factory(daoProvider);
  }
}
