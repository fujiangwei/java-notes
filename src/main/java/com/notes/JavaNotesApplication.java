package com.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan(basePackages = {"com.notes.spring.filter"})
public class JavaNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaNotesApplication.class, args);
    }

}
