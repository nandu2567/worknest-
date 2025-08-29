package com.wipro.worknest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.wipro.worknest")
public class AppConfig {

    // 1. Bean for View Resolver: Helps Spring find the JSP files.
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/"); // Folder where JSPs will be
        resolver.setSuffix(".jsp"); // File extension
        return resolver;
    }

    // 2. Bean for DataSource: Configures the database connection.
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/wiprotraining"); // Your DB name
        dataSource.setUsername("root"); // Your DB username
        dataSource.setPassword("Nandu"); // Your DB password
        return dataSource;
    }

    // 3. Bean for Hibernate SessionFactory: The main entry point for Hibernate.
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.wipro.worknest.model"); // Where to find Entity classes
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    // 4. Bean for Transaction Manager: Manages database transactions.
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    
    // Helper method for Hibernate properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", "true"); // Shows SQL in console
        properties.put("hibernate.hbm2ddl.auto", "update"); // Updates DB schema automatically
        return properties;
    }
}