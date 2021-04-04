package lk.demo.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
@PropertySource("classpath:application.properties")
public class MySqlDataSourceConfig {
    private String jdbcUrl;
    private String className;
    private String user;
    private String password;
    private String maxPoolSize;
    private String minIdlePooleSize;
    long idleTimeout;

    public MySqlDataSourceConfig(@Value("${demo.db.url}") String jdbcUrl,
                                 @Value("${demo.db.className}") String className,
                                 @Value("${demo.db.user}") String user,
                                 @Value("${demo.db.password}") String password,
                                 @Value("${demo.db.maxPoolSize}") String maxPoolSize,
                                 @Value("${demo.db.minIdlePoolSize}") String minIdlePooleSize,
                                 @Value("${demo.db.idleTimeout}") long idleTimeout) {
        this.jdbcUrl = jdbcUrl;
        this.className = className;
        this.user = user;
        this.password = password;
        this.maxPoolSize = maxPoolSize;
        this.minIdlePooleSize = minIdlePooleSize;
        this.idleTimeout = idleTimeout;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource demoDataSource() {
        HikariDataSourceConfig hikariDataSource = new HikariDataSourceConfig();
        return hikariDataSource.dbDataSource(jdbcUrl, className, user, password, maxPoolSize, minIdlePooleSize, idleTimeout);
    }

    @Bean("demo-NamedParameter")
    public NamedParameterJdbcTemplate demoNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(demoDataSource());
    }

    @Bean("demo-JdbcTemplate")
    public JdbcTemplate demoJdbcTemplate() {
        return new JdbcTemplate(demoDataSource());
    }
}
