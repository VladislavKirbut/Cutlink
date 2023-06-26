package repository;

import model.Link;
import org.springframework.jdbc.core.JdbcOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LinkRepository {

    private final JdbcOperations jdbcOps;

    public LinkRepository(JdbcOperations jdbcOps) {
        this.jdbcOps = jdbcOps;
    }

    public List<Link> getAllLinks(String url) {
        return jdbcOps.query("""
                SELECT user_url,
                       new_url
                FROM link
                WHERE user_url = ?;""",
                this::mapToLink, url);
    }

    public int addNewUrl(String userUrl, String newUrl) {
        return jdbcOps.update("""
                INSERT INTO link (user_url, new_url)
                VALUES (?, ?);""", userUrl, newUrl);
    }

    private Link mapToLink(ResultSet rs, int rowNum) throws SQLException {
        return new Link(
                rs.getString("user_url"),
                rs.getString("new_url")
        );
    }
}
