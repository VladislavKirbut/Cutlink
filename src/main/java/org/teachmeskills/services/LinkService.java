package org.teachmeskills.services;

import org.teachmeskills.entity.Link;

public interface LinkService {
    Link getShortUrl(String longUrl);
    Link getLongUrl(String shortUrl);
}
