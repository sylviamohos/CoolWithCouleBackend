package main.java.com.sequence.product.activity;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import main.java.com.obj.dao.ProductDao;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GETProductByTypeActivity_Factory implements Factory<GETProductByTypeActivity> {
  private final Provider<ProductDao> daoProvider;

  public GETProductByTypeActivity_Factory(Provider<ProductDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GETProductByTypeActivity get() {
    return new GETProductByTypeActivity(daoProvider.get());
  }

  public static GETProductByTypeActivity_Factory create(Provider<ProductDao> daoProvider) {
    return new GETProductByTypeActivity_Factory(daoProvider);
  }
}
