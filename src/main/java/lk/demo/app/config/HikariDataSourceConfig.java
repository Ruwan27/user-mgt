package lk.demo.app.config;

import com.zaxxer.hikari.HikariConfig;

import javax.sql.DataSource;

public class HikariDataSourceConfig {

    public DataSource dbDataSource(String jdbcUrl, String className, String user, String password, String maxPoolSize, String minIdleSize, long idleTimeout) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(className);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(user);
        config.setPassword(password);
        return new com.zaxxer.hikari.HikariDataSource(config);
    }
}
