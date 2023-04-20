package by.paprauka.database.connection;

import by.paprauka.database.util.PropertiesManager;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;

@UtilityClass
public class ConnectionManager {

    static {
        loadDriver();
    }

    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static final String URL_KEY = "db.url";

    @SneakyThrows
    public Connection open() {
        return DriverManager.getConnection(
                PropertiesManager.get(URL_KEY),
                PropertiesManager.get(USER_KEY),
                PropertiesManager.get(PASSWORD_KEY)
        );
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("org.postgresql.Driver");
    }
}
