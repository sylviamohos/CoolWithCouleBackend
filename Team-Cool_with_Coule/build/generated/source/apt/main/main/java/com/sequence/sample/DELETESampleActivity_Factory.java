package main.java.com.sequence.sample;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.CustomerDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DELETESampleActivity_Factory implements Factory<DELETESampleActivity> {
  private final Provider<CustomerDao> daoProvider;

  public DELETESampleActivity_Factory(Provider<CustomerDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public DELETESampleActivity get() {
    return new DELETESampleActivity(daoProvider.get());
  }

  public static DELETESampleActivity_Factory create(Provider<CustomerDao> daoProvider) {
    return new DELETESampleActivity_Factory(daoProvider);
  }
}
