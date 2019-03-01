package router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://dzone.com/articles/spring-spring-boot-and-component-scan
//In Spring, we would need @ComponentScan(router)

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}