package org.teachmeskills.repository;

import org.teachmeskills.entity.Link;
import java.net.URI;
import java.util.Optional;

public interface LinkRepository {
    Optional<Link> getLinkByLongUrl(URI longUrl);
    Optional<Link> getLinkByShortUrl(String shortUrl);
    Link addNewUrl(URI longLink, String shortLink);
}
