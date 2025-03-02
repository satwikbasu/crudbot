package com.satwik.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CrudAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudAppApplication.class, args);
    }

    @GetMapping
    public String HelloWorld() {
        return "Hello World Spring Boot";
    }

}
