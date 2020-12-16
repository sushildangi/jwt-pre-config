package com.sushildangi.config;


import com.sushildangi.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
public class AppConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

}
