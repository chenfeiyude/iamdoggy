package com.fytech.iamdoggy;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableScheduling
@EnableAspectJAutoProxy
@Transactional
public class IamdoggyApplication implements ServletContextListener {

	@Autowired
	private SessionFactory doggySessionFactory;

	@Autowired
	private SessionFactory managementSessionFactory;
	
	/**
	 * Keep timezone consistency between java app and database
	 */
	@PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    
	public static void main(String[] args) {
		SpringApplication.run(IamdoggyApplication.class, args);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// no need to do any thing
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		doggySessionFactory.getCurrentSession().close();
		doggySessionFactory.close();

		managementSessionFactory.getCurrentSession().close();
		managementSessionFactory.close();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("http://localhost:8081", "http://iamdoggy.com");
            }
        };
    }
}
