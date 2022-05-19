package main.java.com.obj.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CustomerDao_Factory implements Factory<CustomerDao> {
  private final Provider<DynamoDBMapper> mapperProvider;

  public CustomerDao_Factory(Provider<DynamoDBMapper> mapperProvider) {
    this.mapperProvider = mapperProvider;
  }

  @Override
  public CustomerDao get() {
    return new CustomerDao(mapperProvider.get());
  }

  public static CustomerDao_Factory create(Provider<DynamoDBMapper> mapperProvider) {
    return new CustomerDao_Factory(mapperProvider);
  }
}
