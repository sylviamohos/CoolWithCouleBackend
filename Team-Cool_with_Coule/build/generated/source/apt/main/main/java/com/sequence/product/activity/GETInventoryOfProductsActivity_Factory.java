package main.java.com.sequence.product.activity;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.ProductDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETInventoryOfProductsActivity_Factory
    implements Factory<GETInventoryOfProductsActivity> {
  private final Provider<ProductDao> daoProvider;

  public GETInventoryOfProductsActivity_Factory(Provider<ProductDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GETInventoryOfProductsActivity get() {
    return new GETInventoryOfProductsActivity(daoProvider.get());
  }

  public static GETInventoryOfProductsActivity_Factory create(Provider<ProductDao> daoProvider) {
    return new GETInventoryOfProductsActivity_Factory(daoProvider);
  }
}
