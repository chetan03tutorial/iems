package com.nataraj.management.employee.iems.runner;

import com.nataraj.management.employee.iems.config.DbConfig;
import com.nataraj.management.employee.iems.config.RootConfig;
import com.nataraj.management.employee.iems.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.stream.Stream;


public class IemsRunner {


    public static void main(String args[]) {
        //AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext(RootConfig.class,
                //WebConfig.class, DbConfig.class);
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        //SpringApplication app = new SpringApplication(RootConfig.class, WebConfig.class, DbConfig.class);
        //ConfigurableApplicationContext rootContext = app.run(args);
        ConfigurableApplicationContext rootContext = builder.parent(RootConfig.class).web(WebApplicationType.NONE).child(WebConfig.class).web(WebApplicationType.SERVLET).run(args);

        //SpringApplication springApplication = new SpringApplication();
        //ConfigurableApplicationContext rootContext = springApplication.run(JettyWebApplicationInitializer.class);
        //ServletContext servletContext = rootContext.getBean(ServletContext.class);
        //rootContext = (ConfigurableApplicationContext)servletContext.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
        print(rootContext.getBeanDefinitionNames());
        //print(rootContext.getParent().getBeanDefinitionNames());
    }

    public static void print(String[] beans) {
        Stream<String> beanNames = Arrays.asList(beans).stream();
        System.out.println("bean name is == ");
        beanNames.forEach(System.out ::println);
    }
}
