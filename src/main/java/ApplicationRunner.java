import config.RepositoryConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcOperations;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationRunner {
    public static void main(String[] args) {
        try(ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
                RepositoryConfig.class,
                ApplicationRunner.class
        )) {
            DataSource ds = context.getBean("hikariDataSource", DataSource.class);
            System.out.println(ds);

            JdbcOperations ops = context.getBean("jdbcTemplate", JdbcOperations.class);
            System.out.println(ops);

            JdbcOperations ops1 = context.getBean("jdbcTemplate", JdbcOperations.class);
            System.out.println(ops1);
        }
    }
}
