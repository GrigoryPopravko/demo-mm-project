package by.paprauka.web.config;

import by.paprauka.config.ServiceConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ServiceConfig.class);

        sce.getServletContext().setAttribute("applicationContext", ac);
    }
}
