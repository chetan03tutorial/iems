package com.nataraj.management.employee.iems.config;

import com.nataraj.management.employee.iems.markers.IgnoreDuringScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.*;
import java.util.Optional;

@Configuration
public class WebConfig {

    @Bean
    public ServletWebServerFactory jettyWebServerFactory(){
        JettyServletWebServerFactory jetty = new JettyServletWebServerFactory();
        jetty.addInitializers(WebAppConfig :: onStartup);
        jetty.setContextPath("/iems");
        jetty.setPort(9080);
        return jetty;
    }

    private static class WebAppConfig /*implements ServletContextInitializer*/ {


        public static void onStartup(ServletContext servletContext) throws ServletException {
            ConfigurableApplicationContext registeredRootContext = (ConfigurableApplicationContext)servletContext.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
            Optional<ConfigurableApplicationContext> optional = Optional.of(registeredRootContext);
            if (optional.isPresent()){
                System.out.println("Root Context already exist");
                for(String beanName : registeredRootContext.getBeanDefinitionNames()){
                    System.out.println("Initialized bean name is " + beanName);
                }
            }
            // Chance to register our servlet, filters, listener, context etc
            // Let us try to register contextLoaderListerner with a rootContext
            //registerApplicationSecurityServlet(servletContext);
            //registerDispatcherServlet(servletContext);
            registerPreprocessor(servletContext);
            //registerApplicationServletContextListener(servletContext);


        }

        protected static void registerPreprocessor(ServletContext servletContext) {
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

        protected static void registerDispatcherServlet(ServletContext servletContext){
            /**
             * 1. Build ServletAppContext using the configuration file
             * 2. Build the instance of the Dispatcher servlet initialized by ServletAppContext
             * 3. Register the dispatcher servlet in the servlet context
             * 4. Set the servlet Mapping for the dispatcher servlet
             */
            AnnotationConfigWebApplicationContext servletWebAppContext = new AnnotationConfigWebApplicationContext();
            servletWebAppContext.register(SevletWebAppContextConfig.class);
            FrameworkServlet dispatcherServlet = new DispatcherServlet(servletWebAppContext);

            ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(DispatcherServlet.class.getName(), dispatcherServlet);
            servletRegistration.addMapping(new String[] {"/*"});
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
