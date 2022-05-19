package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ProductDao_Factory implements Factory<ProductDao> {
  private final Provider<DynamoDBMapper> mapperProvider;

  public ProductDao_Factory(Provider<DynamoDBMapper> mapperProvider) {
    this.mapperProvider = mapperProvider;
  }

  @Override
  public ProductDao get() {
    return new ProductDao(mapperProvider.get());
  }

  public static ProductDao_Factory create(Provider<DynamoDBMapper> mapperProvider) {
    return new ProductDao_Factory(mapperProvider);
  }
}
