package com.example.demo.store;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:persistence-multiple-db.properties"})
@EnableJpaRepositories(
        basePackages = "com.example.demo.store.second",
        entityManagerFactoryRef = "productEntityManager",
        transactionManagerRef = "productTransactionManager")
public class PersistenceProductConfiguration {
    @Autowired
    private Environment env;

    public PersistenceProductConfiguration() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean productEntityManager() {
        System.out.println("loading config 2 ");
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(productDataSource());
        em.setPackagesToScan("com.example.demo.store.second");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto.second-datasource"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect.second-datasource"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource productDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName.second-datasource")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("product.jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("second-datasource.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("second-datasource.password")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager productTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(productEntityManager().getObject());
        return transactionManager;
    }
}