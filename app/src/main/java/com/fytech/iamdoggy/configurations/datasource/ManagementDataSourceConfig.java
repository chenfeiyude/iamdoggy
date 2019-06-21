package com.fytech.iamdoggy.configurations.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
		basePackages = {"com.fytech.iamdoggy.interfaces.daos.management"},
        entityManagerFactoryRef = "managementEntityManagerFactory",
        transactionManagerRef = "managementTransactionManager")
public class ManagementDataSourceConfig extends CommonDataSourceConfig {

	@Autowired
	public ManagementDataSourceConfig(Environment env) {
		super(env, "management_datasource");
	}

	@Bean(name = "managementDatasource")
    @ConfigurationProperties(prefix="management.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "managementSessionFactory")
    public LocalSessionFactoryBean managementSessionFactory() {
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(poolDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.fytech.iamdoggy.dtos.management" });
        
        sessionFactory.setHibernateProperties(getJpaProperties());
   
        return sessionFactory;
    }
    
    @Bean(name = "managementEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean managementEntityManagerFactory() {
         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
         em.setDataSource(poolDataSource());
         em.setPackagesToScan(new String[] { "com.fytech.iamdoggy.dtos.management" });
         JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
         em.setJpaVendorAdapter(vendorAdapter);
         return em;
    }
    
    @Bean(name = "managementTransactionManager")
    public PlatformTransactionManager managementTransactionManager()
    {
        EntityManagerFactory factory = managementEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }
}
