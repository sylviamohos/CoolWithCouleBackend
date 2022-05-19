package main.java.com.sequence.product.activity;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.ProductDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETProductByNameActivity_Factory implements Factory<GETProductByNameActivity> {
  private final Provider<ProductDao> daoProvider;

  public GETProductByNameActivity_Factory(Provider<ProductDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GETProductByNameActivity get() {
    return new GETProductByNameActivity(daoProvider.get());
  }

  public static GETProductByNameActivity_Factory create(Provider<ProductDao> daoProvider) {
    return new GETProductByNameActivity_Factory(daoProvider);
  }
}
