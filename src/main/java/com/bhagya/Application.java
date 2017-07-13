package com.bhagya;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.MultipartConfigElement;

/**
 * Created by brupasinghe on 7/13/2017.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        return new MultipartConfigElement("");
//    }
//    @Bean
//    public MultipartResolver multipartResolver() {
//        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(1000000);
//        return multipartResolver;
//    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(){
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public HttpMessageConverters customConverters() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        return new HttpMessageConverters(arrayHttpMessageConverter);
    }
}
