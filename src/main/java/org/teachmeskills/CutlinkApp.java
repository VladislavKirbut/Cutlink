package org.teachmeskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CutlinkApp {
    public static void main(String[] args) {
        SpringApplication.run(CutlinkApp.class, args);
    }
}
