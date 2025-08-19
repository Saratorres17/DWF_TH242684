package sv.edu.udb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConnectionConfig {

    private final String dbDriver;
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    private final String hibernateDdlAuto;
    private final String hibernateDialect;

    public HibernateConnectionConfig(
            @Value("${db.driver}") String dbDriver,
            @Value("${db.url}") String dbUrl,
            @Value("${db.username}") String dbUsername,
            @Value("${db.password}") String dbPassword,
            @Value("${config.hibernate.hbm2ddl.auto}") String hibernateDdlAuto,
            @Value("${config.hibernate.dialect}") String hibernateDialect) {
        this.dbDriver = Objects.requireNonNull(dbDriver);
        this.dbUrl = Objects.requireNonNull(dbUrl);
        this.dbUsername = Objects.requireNonNull(dbUsername);
        this.dbPassword = Objects.requireNonNull(dbPassword);
        this.hibernateDdlAuto = Objects.requireNonNull(hibernateDdlAuto);
        this.hibernateDialect = Objects.requireNonNull(hibernateDialect);
    }

    @Bean(name = "sessionFactory") // ¡clave! no usar 'entityManagerFactory'
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("sv.edu.udb.repository.domain"); // donde están tus @Entity
        sf.setHibernateProperties(hibernateProperties());
        return sf;
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(this.dbDriver)
                .url(this.dbUrl)
                .username(this.dbUsername)
                .password(this.dbPassword)
                .build();
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sessionFactory().getObject());
        return tx;
    }

    // Mantiene sesión abierta durante la petición web (evita "no Session bound to thread" en lecturas)
    @Bean
    public OpenSessionInViewFilter openSessionInViewFilter() {
        return new OpenSessionInViewFilter();
    }

    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", hibernateDdlAuto);
        props.setProperty("hibernate.dialect", hibernateDialect);
        return props;
    }
}
