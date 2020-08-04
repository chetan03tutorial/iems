package com.nataraj.management.employee.iems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * This is a configuration class for configuring the datasource,r JPA Vendor specific properties and Transaction Manager
 */

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.nataraj.management.employee.iems.dao"},
        entityManagerFactoryRef = "hrmsEntityManager",
        transactionManagerRef = "hrmsTransactionManager"
)
@PropertySource({
        "classpath:/${env}/database.properties"
})
public class DbConfig {

    @Autowired
    private Environment environment;
    private static final JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

    @Autowired
    public DbConfig(Environment environment) {
        System.out.println("Initializing DBCONFIG");
        this.environment = environment;
    }


    @Bean
    public PlatformTransactionManager hrmsTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(hrmsEntityManager().getObject());
        return jpaTransactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean hrmsEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(datasource());
        // To set up the multiple packages to scan
        // Read command separated string from properties file and convert in into streams and then array of string using java 8
        
        entityManagerFactoryBean.setPackagesToScan(new String[]{"com.nataraj.management.employee.iems.entities"}); //new String[] {environment.getProperty("db.jpa.entity.packageToScan")}
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
        return entityManagerFactoryBean;
    }


    @Bean
    public DataSource datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.db.driver.classname"));
        String url = environment.getProperty("spring.db.schema");
        dataSource.setUrl(environment.getProperty("spring.db.schema"));
        dataSource.setUsername(environment.getProperty("spring.db.username"));
        // Password would be read in the encrypted format
        dataSource.setPassword(environment.getProperty("spring.db.password"));
        return dataSource;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        return jpaVendorAdapter;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.hbn.hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.hbn.dialect"));
        return properties;
    }

}
