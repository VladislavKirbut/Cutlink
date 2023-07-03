package org.teachmeskills.repository;

import org.teachmeskills.entity.Link;
import java.net.URI;
import java.util.Optional;

public interface LinkRepository {
    Optional<Link> getLink(URI link);
    URI addNewUrl(URI longLink, URI shortLink);
}
