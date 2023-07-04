package org.teachmeskills.repository;

import org.teachmeskills.entity.Link;
import java.net.URI;
import java.util.Optional;

public interface LinkRepository {
    Optional<Link> getShortLink(URI longUrl);
    Optional<Link> getLongLink(String shortUrl);
    Link addNewUrl(URI longLink, String shortLink);
}
