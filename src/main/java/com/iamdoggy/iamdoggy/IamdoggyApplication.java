package com.iamdoggy.iamdoggy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableScheduling
@EnableAspectJAutoProxy
public class IamdoggyApplication implements ServletContextListener {

	@Autowired
	private SessionFactory doggySessionFactory;

	@Autowired
	private SessionFactory managementSessionFactory;

	public static void main(String[] args) {
		SpringApplication.run(IamdoggyApplication.class, args);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// no need to do any thing
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		doggySessionFactory.getCurrentSession().close();
//		doggySessionFactory.close();
//
//		managementSessionFactory.getCurrentSession().close();
//		managementSessionFactory.close();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
