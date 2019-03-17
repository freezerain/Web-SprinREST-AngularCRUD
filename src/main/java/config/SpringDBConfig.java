package config;

import model.service.ArticulosFacade;
import model.service.IArticulosCRUD;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@ComponentScan({"model", "controller"})
@EnableTransactionManagement
public class SpringDBConfig {

    public SpringDBConfig() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Bean("sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("model", "controller");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@servicios.grupoatrium.com:1521:XE");
        dataSource.setUsername("jol183");
        dataSource.setPassword("jol183");
        return dataSource;

    }

    @Bean("crud")
    public IArticulosCRUD getIArticulosCRUD() {
        return new ArticulosFacade();
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

        return hibernateProperties;
    }

}
