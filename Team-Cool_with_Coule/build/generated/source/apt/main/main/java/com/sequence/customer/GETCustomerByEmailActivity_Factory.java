package main.java.com.sequence.customer;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETCustomerByEmailActivity_Factory
    implements Factory<GETCustomerByEmailActivity> {
  private final Provider<CustomerDao> daoProvider;

  public GETCustomerByEmailActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GETCustomerByEmailActivity get() {
    return new GETCustomerByEmailActivity(daoProvider.get());
  }

  public static GETCustomerByEmailActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new GETCustomerByEmailActivity_Factory(daoProvider);
  }
}
