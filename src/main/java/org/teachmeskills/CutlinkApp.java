package org.teachmeskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.teachmeskills.entity.Link;
import org.teachmeskills.repository.LinkRepository;
import org.teachmeskills.services.LinkService;

import java.net.URI;
import java.util.Optional;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CutlinkApp {
    public static void main(String[] args) {
        var context = SpringApplication.run(CutlinkApp.class, args);
        LinkRepository repository = context.getBean("linkRepositoryImpl", LinkRepository.class);
        LinkService linkServiceImpl = context.getBean("linkServiceImpl", LinkService.class);

        Link linkSer = linkServiceImpl.getLongUrl("14a8ec8");
        System.out.println(linkSer);

        Optional<Link> link1 = repository.getLinkByShortUrl("14a8ec8");
        link1.ifPresent(value -> System.out.println(value.getLongUrl()));

        Optional<Link> link = repository.getLinkByLongUrl(URI.create("https://hello.com"));
        link.ifPresent(value -> System.out.println(value.getShortUrl()));
    }
}
