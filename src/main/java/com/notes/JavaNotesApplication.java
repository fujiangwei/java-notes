package com.notes;

import com.notes.utils.ParamNoteHandle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;

@SpringBootApplication
@EnableScheduling
//@ServletComponentScan(basePackages = {"com.notes.spring.filter"})
public class JavaNotesApplication {

    private static String curClassName = "";

    public static void main(String[] args) {
        SpringApplication.run(JavaNotesApplication.class, args);
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() throws Exception{
        
    }

}
