package by.paprauka.database.util;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

@lombok.experimental.UtilityClass
public class PropertiesManager {

    private final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    @SneakyThrows
    private void loadProperties() {
        try (InputStream inputStream = PropertiesManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        }
    }
}
