package com.thuja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author: Ansen
 * @create: 2022-01-09 18:13
 **/
@SpringBootApplication
@ServletComponentScan
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class,args);
    }
}
