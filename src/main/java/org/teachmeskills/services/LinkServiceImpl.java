package org.teachmeskills.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teachmeskills.entity.Link;
import org.teachmeskills.exceptions.IncorrectUrlException;
import org.teachmeskills.repository.LinkRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "link")
@Getter
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;
    private final List<String> hosts;

    @Transactional
    @Override
    public Link getShortUrl(String longUrl) throws URISyntaxException {
        URI uri = new URI(longUrl);
        if (!isUrlValid(uri)) throw new IncorrectUrlException("Invalid url");

        Optional<Link> link = linkRepository.getLinkByLongUrl(URI.create(longUrl));
        if(link.isPresent())
            return link.get();
        else {
            String randomLink = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
            return linkRepository.addNewUrl(URI.create(longUrl), randomLink);
        }
    }

    @Override
    @Transactional
    public Link getLongUrl(String shortUrl) throws URISyntaxException {
        URI uri = new URI(shortUrl);
        String host = uri.getHost();
        if (host == null) throw new IncorrectUrlException("Invalid url");

        Optional<Link> link = linkRepository.getLinkByShortUrl(host);

        if (link.isPresent()) return link.get();
        else throw new IncorrectUrlException("Invalid url or url doesn't exist");
    }

    private boolean isUrlValid(URI url) {
        String scheme = url.getScheme();
        String host = url.getHost();
        if (scheme == null || host == null) return false;

        for (String elem: hosts)
            if (elem.equals(host))
                return false;

        return scheme.matches("http(s)?");
    }
}
