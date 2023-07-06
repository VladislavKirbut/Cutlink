package org.teachmeskills.services;

import org.teachmeskills.entity.Link;

import java.net.URISyntaxException;

public interface LinkService {
    Link getShortUrl(String longUrl) throws URISyntaxException;
    Link getLongUrl(String shortUrl) throws URISyntaxException;
}
