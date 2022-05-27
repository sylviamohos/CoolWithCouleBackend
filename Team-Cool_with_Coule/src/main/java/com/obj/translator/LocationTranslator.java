package main.java.com.obj.translator;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.obj.Location;

public class LocationTranslator implements DynamoDBTypeConverter<String, Location> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convert(Location object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Location unconvert(String object) {
        try {
            return mapper.readValue(object, Location.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
