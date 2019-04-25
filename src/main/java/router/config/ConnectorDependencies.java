package router.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorDependencies {

    @Bean
    public FamilyConnectionConfig getFamilyConnectionConfig(@Value("${family.url}") String familyUrl) {
        return new FamilyConnectionConfig(familyUrl);
    }
}
