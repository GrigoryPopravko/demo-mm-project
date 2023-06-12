package by.paprauka.config;


import by.paprauka.database.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(DatabaseConfig.class)
@ComponentScan("by.paprauka.service")
@Configuration
public class ServiceConfig {
}
