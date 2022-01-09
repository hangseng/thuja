package com.codeTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author: Ansen
 * @create: 2022-01-09 10:42
 **/
@SpringBootApplication
@ServletComponentScan
public class CodeGeneratorApp {
    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApp.class,args);
    }
}
