package com.nataraj.management.employee.iems.config;

import com.nataraj.management.employee.iems.markers.IgnoreDuringScan;
import com.nataraj.management.employee.iems.markers.Preprocessor;
import com.nataraj.management.employee.iems.services.EmployeeLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Configuration
@ComponentScan(
        basePackages = {"com.nataraj.management.employee.iems.web.controllers"},
        excludeFilters = {@ComponentScan.Filter(IgnoreDuringScan.class)}
)
@PropertySource({
        "classpath:/${env}/application.properties"
})
@EnableAutoConfiguration
public class WebConfig {


    private Environment environment;

    @Autowired
    public WebConfig(Environment environment){
        System.out.println("Initializing the WEB CONFIG constructor");
        this.environment = environment;
    }

    @Bean
    public ServletWebServerFactory jettyWebServerFactory(){
        JettyServletWebServerFactory jetty = new JettyServletWebServerFactory();
        WebAppConfig webAppConfig = new WebAppConfig();
        jetty.addInitializers(webAppConfig:: onStartup);
        jetty.setContextPath("/iems");
        jetty.setPort(9080);
        return jetty;
    }

    private class WebAppConfig /*implements ServletContextInitializer*/ {


        public void onStartup(ServletContext servletContext) throws ServletException {

            ConfigurableApplicationContext registeredRootContext = (ConfigurableApplicationContext)servletContext.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
            /*String[] childBeans = registeredRootContext.getBeanFactory().getBeanDefinitionNames();
            List<String> childBeanList = Arrays.asList(childBeans);

            DefaultListableBeanFactory parentFactory  = (DefaultListableBeanFactory)registeredRootContext.getBeanFactory().getParentBeanFactory();
            String[] parentBeans = parentFactory.getBeanDefinitionNames();

            System.out.println("Beans created in parent context");
            Arrays.stream(parentBeans).forEach(System.out::println);
            List<String> parentBeansList = Arrays.asList(parentBeans);

            System.out.println("Bean created in child web context");
            Arrays.stream(childBeans).filter(not(parentBeansList::contains)).forEach(System.out::println);*/


            /*Optional<ConfigurableApplicationContext> optional = Optional.of(registeredRootContext);
            if (optional.isPresent()){
                Arrays.stream(registeredRootContext.getBeanDefinitionNames()).forEach((s)-> {
                    System.out.println("Bean Initialized by Root Context is " + s);
                });
                *//*for(String beanName : registeredRootContext.getBeanDefinitionNames()){
                    System.out.println("Initialized bean name is " + beanName);
                }*//*
            }*/
            // Chance to register our servlet, filters, listener, context etc
            // Let us try to register contextLoaderListerner with a rootContext
            //registerApplicationSecurityServlet(servletContext);


            //registerPreprocessor(registeredRootContext);
            EmployeeLoader employeeLoader = registeredRootContext.getBean(EmployeeLoader.class);
            employeeLoader.readCSV("dev/employee_details.csv");
            //registerDispatcherServlet(servletContext);

            //registerApplicationServletContextListener(servletContext);


        }

        /*public  <R> Predicate<R> not(Predicate<R> predicate) {
            return predicate.negate();
        }*/



        protected void registerPreprocessor(Consumer<ConfigurableApplicationContext> consumer,
                                            ConfigurableApplicationContext registeredRootContext) {


            String[] clazzes = environment.getProperty("application.preprocessor").split(",");

            Arrays.stream(clazzes).forEach( (clazzName)->{
                this.invokePreprocessor(clazzName,registeredRootContext);
            });

            /*EmployeeLoader employeeLoader = registeredRootContext.getBean(EmployeeLoader.class);
            employeeLoader.readCSV("dev/employee_details.csv");*/
            consumer.accept(registeredRootContext);

        }

        /*protected static void registerApplicationServletContextListener(ServletContext servletContext){
            ApplicationServletContextLister applicationServletContextLister = new ApplicationServletContextLister();
            servletContext.addListener(applicationServletContextLister);
        }*/

        /*protected static void registerApplicationSecurityServlet(ServletContext servletContext){
            ApplicationSecurityServlet securityServlet = new ApplicationSecurityServlet();
            ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(ApplicationSecurityServlet.class.getName(), securityServlet);
            servletRegistration.addMapping(new String[] {"/secure/*"});
            servletRegistration.setLoadOnStartup(1);
        }*/


        public void invokePreprocessor(String clazzName,ConfigurableApplicationContext context ){
            final Class<? extends Annotation> type = Preprocessor.class;
            try {
                Class<?> clazz = Class.forName(clazzName);
                Optional<Method> getter =
                        Arrays.stream(clazz.getMethods())
                                .filter((method)->{
                                    return method.isAnnotationPresent(type);
                                }).findFirst();

                if(getter.isPresent()){
                    Method m = getter.get();
                    Consumer<ApplicationContext> consumer =
                            (Consumer<ApplicationContext>)m.invoke(clazz.newInstance());
                }
            } catch (ClassNotFoundException  | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        public boolean isAnnotationPresent(Method method){
            return true;
        }

        protected void registerDispatcherServlet(ServletContext servletContext){
            /**
             * 1. Build ServletAppContext using the configuration file
             * 2. Build the instance of the Dispatcher servlet initialized by ServletAppContext
             * 3. Register the dispatcher servlet in the servlet context
             * 4. Set the servlet Mapping for the dispatcher servlet
             */
            AnnotationConfigWebApplicationContext servletWebAppContext = new AnnotationConfigWebApplicationContext();
            //servletWebAppContext.register(WebConfig.class);
            /*servletWebAppContext.refresh();

            Arrays.stream(servletWebAppContext.getBeanDefinitionNames()).forEach(
                    (beanName) -> System.out.println("Bean created by child Context is " + beanName)
            );*/
            FrameworkServlet dispatcherServlet = new DispatcherServlet(servletWebAppContext);

            ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(DispatcherServlet.class.getName(), dispatcherServlet);
            servletRegistration.addMapping(new String[] {"/secure/*"});
            servletRegistration.setLoadOnStartup(1);
        }

    }

    private static class ApplicationServletContextLister implements ServletContextListener {

        public void contextInitialized(ServletContextEvent sce) {
            System.out.println("Servlet Context is being initialized, I am going to select a profile" +
                    "or perform some meaningful work");
        }
        public void contextDestroyed(ServletContextEvent sce) {}
    }





}
