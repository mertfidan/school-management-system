package com.mertfidan.schoolmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SchoolManagementApplication implements CommandLineRunner {


    public SchoolManagementApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private final ApplicationContext applicationContext;


    @RequestMapping("/home")
    String home() {
        return "Hello !!!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SchoolManagementApplication.class, args);

        
        
    }


    @Override
    public void run(String... args) throws Exception {
        int beanDefinationCount = applicationContext.getBeanDefinitionCount();
        System.out.println("Bean count : " + beanDefinationCount);

      /*  String[] beanDefinitionNames =applicationContext.getBeanDefinitionNames();

       for (String beanDefinitionName: beanDefinitionNames) {
           System.out.println(beanDefinitionName);

       }*/
    }
}


