package org.example.jvcarsharingservice;

import org.springframework.boot.SpringApplication;

public class TestJvCarSharingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(JvCarSharingServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
