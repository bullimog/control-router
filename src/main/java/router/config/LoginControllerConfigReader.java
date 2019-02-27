
package router.config;

        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import router.controllers.LoginController;


@Configuration
//When constructing a LoginController, use this Bean
@ComponentScan(basePackageClasses = LoginController.class)

public class LoginControllerConfigReader {

    @Bean
    public LoginControllerConfig getLoginConfig(@Value("${login.url}") String loginUrl,
                                                @Value("${username}") String username,
                                                @Value("${password}") String password) {
        return new LoginControllerConfig(loginUrl, username, password);
    }
}