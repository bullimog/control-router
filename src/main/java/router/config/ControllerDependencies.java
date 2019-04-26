
package router.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import router.connectors.*;

import java.util.Locale;

//Defines Beans, which will be found and autowired.

@Configuration
//@SpringBootApplication is in higher-level package, so this bean will be scanned automatically.

public class ControllerDependencies{ //} implements WebMvcConfigurer {

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


    //FOR I18N
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.UK);
//        return slr;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
}