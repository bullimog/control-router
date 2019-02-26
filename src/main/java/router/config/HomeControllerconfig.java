package router.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import router.connectors.HttpConnection;
import router.connectors.HttpConnectionImpl;
import router.controllers.HomeController;

@Configuration
//When constructing a HomeController, use this Bean
@ComponentScan(basePackageClasses = HomeController.class)

public class HomeControllerconfig {

    @Bean
    public HttpConnection getHttpConnection() {
        return new HttpConnectionImpl();
    }
}