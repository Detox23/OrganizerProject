package com.example.src.configurations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.example.src.entities.User;
import com.example.src.utilities.AuditorAwareImpl;
import lombok.var;
import org.apache.catalina.startup.Tomcat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceJavaMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

@Configuration
@EnableJpaAuditing
public class AppConfig implements WebMvcConfigurer {


    @Autowired
    private EmailConfiguration emailConfiguration;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService(){
        var w = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                    emailConfiguration.awsAccessKey, emailConfiguration.awsSecretKey
                                )
                        )
                ).withRegion(Regions.EU_NORTH_1).build();
        return w;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
    }

    @Bean
    public ModelMapper modelMapper() throws UnknownHostException {
        return new ModelMapper();
    }

    @Bean
    public Tomcat getTomcat(){
        return new Tomcat();
    }

    @Bean
    public AuditorAware<User> auditorProvider(){
        return new AuditorAwareImpl();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}
