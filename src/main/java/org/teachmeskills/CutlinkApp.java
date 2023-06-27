package org.teachmeskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.teachmeskills.repository.LinkRepository;

@SpringBootApplication
public class CutlinkApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CutlinkApp.class, args);
        LinkRepository repository = context.getBean("linkRepository", LinkRepository.class);
        System.out.println(repository);
    }
}
