package electronicapractica10.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.beans.BeansException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

    }


}




