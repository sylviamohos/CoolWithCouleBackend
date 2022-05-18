package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class OrderDao_Factory implements Factory<OrderDao> {
  private final Provider<DynamoDBMapper> dynamoDBMapperProvider;

  public OrderDao_Factory(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    this.dynamoDBMapperProvider = dynamoDBMapperProvider;
  }

  @Override
  public OrderDao get() {
    return new OrderDao(dynamoDBMapperProvider.get());
  }

  public static OrderDao_Factory create(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    return new OrderDao_Factory(dynamoDBMapperProvider);
  }
}
