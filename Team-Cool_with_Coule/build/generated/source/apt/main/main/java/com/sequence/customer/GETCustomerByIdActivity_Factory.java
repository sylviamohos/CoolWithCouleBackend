package main.java.com.sequence.customer;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETCustomerByIdActivity_Factory implements Factory<GETCustomerByIdActivity> {
  private final Provider<CustomerDao> daoProvider;

  public GETCustomerByIdActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GETCustomerByIdActivity get() {
    return new GETCustomerByIdActivity(daoProvider.get());
  }

  public static GETCustomerByIdActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new GETCustomerByIdActivity_Factory(daoProvider);
  }
}
