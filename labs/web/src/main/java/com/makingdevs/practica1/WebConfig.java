package com.makingdevs.practica1;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.makingdevs.practica12.TimeOpeningInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.makingdevs.practica7", "com.makingdevs.practica4", "com.makingdevs.practica5",
    "com.makingdevs.practica6", "com.makingdevs.practica9", "com.makingdevs.practica10", "com.makingdevs.practica11",
    "com.makingdevs.practica13", "com.makingdevs.practica14" })
public class WebConfig extends WebMvcConfigurerAdapter {

  @Bean
  public MessageSource messageSource() {
    // Hey ma! Look...
    // http://docs.spring.io/spring/docs/4.0.0.RELEASE/javadoc-api/org/springframework/context/support/ReloadableResourceBundleMessageSource.html
    ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
    ms.setBasenames("classpath:/i18n/messages");
    // ms.setDefaultEncoding("UTF-8");
    return ms;
  }

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(new Locale("es"));
    return localeResolver;
  }

  @Bean
  public ThemeResolver themeResolver() {
    SessionThemeResolver themeResolver = new SessionThemeResolver();
    themeResolver.setDefaultThemeName("style.normal");
    return themeResolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
    localeInterceptor.setParamName("lang");
    ThemeChangeInterceptor themeInterceptor = new ThemeChangeInterceptor();
    themeInterceptor.setParamName("theme");
    registry.addInterceptor(new TimeOpeningInterceptor());
    registry.addInterceptor(localeInterceptor).addPathPatterns("/");
    registry.addInterceptor(themeInterceptor).addPathPatterns("/");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("/libs/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
  }

  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setViewClass(JstlView.class);
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    resolver.setOrder(10);
    return resolver;
  }

  @Bean
  public BeanNameViewResolver beanNameViewResolver() {
    BeanNameViewResolver resolver = new BeanNameViewResolver();
    resolver.setOrder(0);
    return resolver;
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new CommonsMultipartResolver();
  }

}