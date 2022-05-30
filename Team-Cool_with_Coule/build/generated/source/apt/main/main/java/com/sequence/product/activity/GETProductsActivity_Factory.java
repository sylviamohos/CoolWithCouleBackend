package main.java.com.sequence.product.activity;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.ProductDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETProductsActivity_Factory implements Factory<GETProductsActivity> {
  private final Provider<ProductDao> daoProvider;

  public GETProductsActivity_Factory(Provider<ProductDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GETProductsActivity get() {
    return new GETProductsActivity(daoProvider.get());
  }

  public static GETProductsActivity_Factory create(Provider<ProductDao> daoProvider) {
    return new GETProductsActivity_Factory(daoProvider);
  }
}
