package org.teachmeskills.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.teachmeskills.entity.Link;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Profile({"local", "production"})
@AllArgsConstructor
@Repository
public class LinkRepositoryImpl implements LinkRepository {
    private final NamedParameterJdbcOperations jdbcOps;

    @Override
    public Optional<Link> getLink(URI url) {
        String sql = """
                SELECT id,
                       long_url,
                       short_url
                FROM link
                WHERE long_url = :url;""";

        return jdbcOps.query(sql, Map.of("url", url.toString()), this::mapToLink)
                .stream()
                .findFirst();
    }

    @Override
    public URI addNewUrl(URI longUrl, URI shortUrl) {
        String sql = """
                INSERT INTO link (long_url, short_url)
                VALUES (:long_url, :short_url)
                RETURNING short_url""";

        return jdbcOps.queryForObject(sql, Map.of(
                "long_url", longUrl.toString(),
                "short_url", shortUrl.toString()),
                URI.class);
    }

    private Link mapToLink(ResultSet rs, int rowNum) throws SQLException {
        return new Link(
                rs.getLong("id"),
                URI.create(rs.getString("long_url")),
                URI.create(rs.getString("short_url"))
        );
    }
}
