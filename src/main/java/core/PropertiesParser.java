package core;

import lombok.extern.java.Log;

import java.util.Properties;

@Log
public class PropertiesParser {

    protected static String getProperty(Properties property, String propertyKey) {
        String prop = getProperty(property, propertyKey, null);
        if ( prop != null ) {
            return prop;
        } else {
            throw new IllegalArgumentException("Mandatory property: " + propertyKey + " is missing");
        }
    }

    protected static String getProperty(Properties property, String propertyKey, String defaultValue) {
        return System.getProperty(propertyKey) != null
                ? System.getProperty(propertyKey)
                : property.getProperty(propertyKey, defaultValue);
    }


    protected static <E extends Enum<E>> E getFromEnum(Class<E> enumType, String value) {
        try {
            return Enum.valueOf(enumType, value);
        } catch (IllegalArgumentException e) {
            // !TODO  Add log.error -> e.printStackTrace();
            throw new IllegalArgumentException("Unsupported value: " + value + " for " + enumType.getName());
        }


    }
}
