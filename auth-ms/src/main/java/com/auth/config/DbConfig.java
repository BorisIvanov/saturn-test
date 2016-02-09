package com.auth.config;

import com.auth.domain.Account;
import com.auth.domain.Token;
import com.auth.repository.impl.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
@ComponentScan(basePackageClasses = AccountRepository.class)
public class DbConfig {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://pellefant.db.elephantsql.com:5432/vwjyiaeu");
        dataSource.setUsername("vwjyiaeu");
        dataSource.setPassword("Hysrs4ePlY_5zxxkt71PE7QoOJnyXDcf");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        //localSessionFactoryBean.setAnnotatedPackages("com.auth.domain");
        localSessionFactoryBean.setAnnotatedClasses(Account.class, Token.class);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;
    }

/*
    @Bean
//     public org.springframework.orm.hibernate4.HibernateTransactionManager transactionManager() {
    public org.springframework.orm.hibernate5.HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }*/

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Bean
    public Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                //setProperty("hibernate.chach.provider_class", "org.hibernate.cache.NoCacheProvider");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.hbm2ddl.auto", "create-drop");
                setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            }
        };
    }
/*
    @Bean
    public entityManagerFactory(){

    }*/

}
