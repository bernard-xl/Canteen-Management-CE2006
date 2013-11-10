package solid5ive.cams.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * The Spring library configuration class that includes settings for database URL, connection pool and secondary level cache,
 * as well as transaction management, and IoC container.
 * 
 * @author Poh Shie Liang
 */
@Configuration
@ComponentScan(basePackages = {"solid5ive.cams.service", "solid5ive.cams.security", "solid5ive.cams.demo"})
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "solid5ive.cams.data.repositories")
@EnableTransactionManagement
public class SpringConfiguration {

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(false);
        return jpaVendorAdapter;
    }

    @Bean
    public Properties extraJpaProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.cache.use_second_level_cache", "true");
        props.setProperty("hibernate.cache.use_query_cache", "true");
        props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        props.setProperty("hibernate.connection.driver_class",  "com.mysql.jdbc.Driver");
        //props.setProperty("hibernate.connection.url", "jdbc:mysql://10.211.55.4:3306/cams");
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/cams2");
        props.setProperty("hibernate.connection.username", "root");
        props.setProperty("hibernate.connection.password",  "root");
        props.setProperty("hibernate.c3p0.min_size",  "5");
        props.setProperty("hibernate.c3p0.max_size",  "100");
        props.setProperty("hibernate.c3p0.timeout",  "300");
        props.setProperty("hibernate.c3p0.max_statements",  "50");
        props.setProperty("hibernate.c3p0.idle_test_period",  "3000");
        return props;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("solid5ive.cams.data.entities");
        lef.setJpaProperties(extraJpaProperties());
        return lef;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
