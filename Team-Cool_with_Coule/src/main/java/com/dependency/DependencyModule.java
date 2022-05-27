package main.java.com.dependency;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DependencyModule {

    @Provides
    @Singleton
    public DynamoDBMapper provideDynamoDbMapper() {
        return new DynamoDBMapper(AmazonDynamoDBClient.builder().withRegion(Regions.US_WEST_2).build());
    }
}
