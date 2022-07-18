package ru.crbpavel.music.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.crbpavel.music.security.DecodeTokenUtil;

@Configuration
@EnableJpaRepositories(basePackages = {"ru.crbpavel.music.*"})
@EntityScan(basePackages = {"ru.crbpavel.music.*"})
@ComponentScan(basePackages = {"ru.crbpavel.music.*"})
public class AppConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DecodeTokenUtil decodeTokenUtil() {
        return new DecodeTokenUtil();
    }
}
