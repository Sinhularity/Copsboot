package mx.uv.coatza.S22017021.copsboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity; // HttpSecurity is now visible?
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import com.c4_soft.springaddons.security.oidc.starter.synchronised.resourceserver.ResourceServerExpressionInterceptUrlRegistryPostProcessor;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    @Bean
    ResourceServerExpressionInterceptUrlRegistryPostProcessor authorizedPostProcessor() {
        return registry -> registry.requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .requestMatchers("/api/**").authenticated().anyRequest().authenticated();
    }
}