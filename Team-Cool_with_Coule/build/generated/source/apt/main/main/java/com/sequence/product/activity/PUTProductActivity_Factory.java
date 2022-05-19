package main.java.com.sequence.product.activity;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.ProductDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PUTProductActivity_Factory implements Factory<PUTProductActivity> {
  private final Provider<ProductDao> daoProvider;

  public PUTProductActivity_Factory(Provider<ProductDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public PUTProductActivity get() {
    return new PUTProductActivity(daoProvider.get());
  }

  public static PUTProductActivity_Factory create(Provider<ProductDao> daoProvider) {
    return new PUTProductActivity_Factory(daoProvider);
  }
}
