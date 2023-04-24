package by.paprauka.database.connection;

import by.paprauka.database.util.PropertiesManager;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;

@UtilityClass
public class ConnectionPool {

    private final DataSource DATA_SOURCE;

    private final String USER_KEY = "db.user";
    private final String PASSWORD_KEY = "db.password";
    private final String URL_KEY = "db.url";
    private final String DRIVER_KEY = "db.driver";
    private final String POOL_SIZE_KEY = "db.pool.size";

    static {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(PropertiesManager.get(URL_KEY));
        poolProperties.setUsername(PropertiesManager.get(USER_KEY));
        poolProperties.setPassword(PropertiesManager.get(PASSWORD_KEY));
        poolProperties.setDriverClassName(PropertiesManager.get(DRIVER_KEY));
        poolProperties.setMaxActive(Integer.parseInt(PropertiesManager.get(POOL_SIZE_KEY)));
        DATA_SOURCE = new DataSource(poolProperties);
    }

    @SneakyThrows
    public Connection get() {
        return DATA_SOURCE.getConnection();
    }
}
