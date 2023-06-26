package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.LinkRepository;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {

    @Bean
    HikariDataSource hikariDataSource(
            @Value("${cutlink.db.username}") String username,
            @Value("${cutlink.db.password}") String password,
            @Value("${cutlink.db.url}") String url,
            @Value("${cutlink.db.driver}") String driver
    ) {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setJdbcUrl(url);
        return new HikariDataSource(config);
    }

    @Bean
    JdbcTemplate jdbcTemplate(@Qualifier ("hikariDataSource")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    LinkRepository linkRepository(@Qualifier("jdbcTemplate") JdbcOperations ops) {
        return new LinkRepository(ops);
    }

}
