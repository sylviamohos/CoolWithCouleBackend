package main.java.com.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DependencyModule_ProvideDynamoDbMapperFactory
    implements Factory<DynamoDBMapper> {
  private final DependencyModule module;

  public DependencyModule_ProvideDynamoDbMapperFactory(DependencyModule module) {
    this.module = module;
  }

  @Override
  public DynamoDBMapper get() {
    return Preconditions.checkNotNull(
        module.provideDynamoDbMapper(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static DependencyModule_ProvideDynamoDbMapperFactory create(DependencyModule module) {
    return new DependencyModule_ProvideDynamoDbMapperFactory(module);
  }

  public static DynamoDBMapper proxyProvideDynamoDbMapper(DependencyModule instance) {
    return Preconditions.checkNotNull(
        instance.provideDynamoDbMapper(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
