package com.feiyu4fun.iamdoggy.configurations.datasource;

import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = {"com.fytech.iamdoggy.interfaces.daos.doggy"},
        entityManagerFactoryRef = "doggyEntityManagerFactory",
        transactionManagerRef = "doggyTransactionManager")
public class DoggyDataSourceConfig extends CommonDataSourceConfig {
	
	@Autowired
	public DoggyDataSourceConfig(Environment env) {
		super(env, "doggy_datasource");
	}

	@Bean(name = "doggyDatasource")
	@Primary
    @ConfigurationProperties(prefix="doggy.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "doggySessionFactory")
    @Primary
    public LocalSessionFactoryBean doggySessionFactory() {
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(poolDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.fytech.iamdoggy.dtos.doggy" });
        
        sessionFactory.setHibernateProperties(getJpaProperties());
   
        return sessionFactory;
    }
    
    @Bean(name = "doggyEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean doggyEntityManagerFactory() {
         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
         em.setDataSource(poolDataSource());
         em.setPackagesToScan(new String[] { "com.fytech.iamdoggy.dtos.doggy" });
         JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
         em.setJpaVendorAdapter(vendorAdapter);
         return em;
    }
    
    @Bean(name = "doggyTransactionManager")
    @Primary
    public PlatformTransactionManager doggyTransactionManager()
    {
        EntityManagerFactory factory = doggyEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }
}
