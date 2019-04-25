
package router.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import router.connectors.*;

//Defines Beans, which will be found and autowired.

@Configuration
//@SpringBootApplication is in higher-level package, so this bean will be scanned automatically.

public class ControllerDependencies {

    @Bean
    public LoginControllerConfig getLoginConfig(@Value("${login.url}") String loginUrl,
                                                @Value("${username}") String username,
                                                @Value("${password}") String password) {
        return new LoginControllerConfig(loginUrl, username, password);
    }

    @Bean
    public HttpConnection getHttpConnection() {
        return new HttpConnectionImpl();
    }

    @Bean
    public MqttConnection getMQTTConnection() {
        return new MqttConnectionImpl();
    }

    @Bean
    public FamilyConnection getFamilyConnection() {
        return new FamilyConnectionImpl();
    }
}