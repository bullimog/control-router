package router.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import router.connectors.HttpConnection;
import router.connectors.HttpConnectionImpl;
import router.controllers.HomeController;
import router.controllers.LoginController;

@Configuration
//When constructing a Controller, use this Bean
//@ComponentScan(basePackageClasses = {HomeController.class, LoginController.class} )

public class HomeControllerConfig {

    @Bean
    public HttpConnection getHttpConnection() {
        return new HttpConnectionImpl();
    }
}