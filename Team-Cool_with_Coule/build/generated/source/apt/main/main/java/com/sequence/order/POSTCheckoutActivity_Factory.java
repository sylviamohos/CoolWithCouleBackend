package main.java.com.sequence.order;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class POSTCheckoutActivity_Factory implements Factory<POSTCheckoutActivity> {
  private final Provider<CustomerDao> daoProvider;

  public POSTCheckoutActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public POSTCheckoutActivity get() {
    return new POSTCheckoutActivity(daoProvider.get());
  }

  public static POSTCheckoutActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new POSTCheckoutActivity_Factory(daoProvider);
  }
}
