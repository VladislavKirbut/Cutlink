package org.teachmeskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.teachmeskills.repository.LinkRepository;

import java.net.URI;

@SpringBootApplication
public class CutlinkApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CutlinkApp.class, args);
        LinkRepository repository = context.getBean("linkRepositoryImpl", LinkRepository.class);

        URI uri = repository.addNewUrl(URI.create("https://google.com"), URI.create("gog"));
        System.out.println(uri);
    }
}
