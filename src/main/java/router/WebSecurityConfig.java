package router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    //https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/myservice/family/{id:[0-9]+}",
                                         "/family",
                                         "/getDateAndTime",
                                         "/storePerson",
                                         "/findPerson",
                                         "/people").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

//        PasswordEncoderFactories
//        UserDetails user2 =
//                User.withUsername("user")
//                    .password("password")
//                    .roles("USER")
//                    .passwordEncoder()
//                .build();

        return new InMemoryUserDetailsManager(user);
    }
}