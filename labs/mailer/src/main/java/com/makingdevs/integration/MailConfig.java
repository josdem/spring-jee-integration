package com.makingdevs.integration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
//tag::environment[]
@Configuration
@ComponentScan(basePackages = {"com.makingdevs.integration"})
@PropertySource("classpath:/mail.properties")
public class MailConfig {

  @Autowired
  Environment environment;
//end::environment[]
//tag::configuration1[]
  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(environment.getProperty("mail.host"));
    mailSender.setPort(environment.getProperty("mail.port", Integer.class));
    mailSender.setUsername(environment.getProperty("mail.username"));
    mailSender.setPassword(environment.getProperty("mail.password"));
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
    properties.put("mail.smtp.socketFactory.port",
        environment.getProperty("mail.smtp.socketFactory.port"));
    properties.put("mail.smtp.socketFactory.class",
        environment.getProperty("mail.smtp.socketFactory.class"));
    properties.put("mail.smtp.socketFactory.fallback",
        environment.getProperty("mail.smtp.socketFactory.fallback"));
    mailSender.setJavaMailProperties(properties);
    return mailSender;
  }
//end::configuration1[]
//tag::configuration2[]
  @Bean
  public SimpleMailMessage simpleMailMessage() {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("info@makingdevs.com");
    mailMessage.setSubject("MakingDevs Training");
    return mailMessage;
  }
//end::configuration2[]
//tag::configuration3[]
  @Bean
  public FreeMarkerConfigurationFactoryBean freemarkerConfiguration() {
    FreeMarkerConfigurationFactoryBean configuration = new FreeMarkerConfigurationFactoryBean();
    configuration.setTemplateLoaderPath("classpath:/freemarker/mail/");
    return configuration;
  }
//end::configuration3[]
}