package com.example.src.configurations;

import com.example.src.entities.User;
import com.example.src.services.JobService;
import com.example.src.utilities.AuditorAwareImpl;
import lombok.AllArgsConstructor;
import org.apache.catalina.startup.Tomcat;
import org.jobrunr.scheduling.JobScheduler;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.UnknownHostException;
import java.util.concurrent.Executor;

@Configuration
@EnableJpaAuditing
@AllArgsConstructor
@EnableAsync
public class AppConfig {

    private final JobScheduler jobScheduler;

    private final JobService sampleJobService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        LOGGER.info("CREATING BCRYPT ENCODER BEAN");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        LOGGER.info("CREATING MODEL MAPPER BEAN");
        return new ModelMapper();
    }

    @Bean
    public Tomcat getTomcat(){
        LOGGER.info("CREATING TOMCAT BEAN");
        return new Tomcat();
    }

    @Bean
    public AuditorAware<User> auditorProvider(){
        LOGGER.info("CREATING AUDITOR PROVIDER BEAN");
        return new AuditorAwareImpl();
    }

    @Bean
    public Executor taskExecutor() {
        LOGGER.debug("CREATING ASYNC TASK EXECUTOR BEAN");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Thread-");
        executor.initialize();
        return executor;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        LOGGER.debug("CREATING CORS CONFIGURATION BEAN");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public void aTestJob() {
        jobScheduler.scheduleRecurrently("* * * * *", () -> sampleJobService.executePassedTasks());
    }

}
