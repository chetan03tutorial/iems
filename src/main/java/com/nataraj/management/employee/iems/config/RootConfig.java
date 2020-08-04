package com.nataraj.management.employee.iems.config;

import com.nataraj.management.employee.iems.markers.IgnoreDuringScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(
    basePackages = {"com.nataraj.management.employee.iems"},
    excludeFilters = {@ComponentScan.Filter(IgnoreDuringScan.class)}
    )
@Import(ConfigSelector.class)
public class RootConfig {


    public RootConfig(){
        System.out.println("Initializing the ROOT CONFIG constructor");
    }


    /*@Bean
    public ServletWebServerFactory jettyWebServerFactory(){
        JettyServletWebServerFactory jetty = new JettyServletWebServerFactory();
        jetty.addInitializers(RootConfig.WebAppConfig:: onStartup);
        jetty.setContextPath("/iems");
        jetty.setPort(9080);
        return jetty;
    }

    private static class WebAppConfig *//*implements ServletContextInitializer*//* {


        public static void onStartup(ServletContext servletContext) throws ServletException {

            ConfigurableApplicationContext registeredRootContext = (ConfigurableApplicationContext)servletContext.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
            Optional<ConfigurableApplicationContext> optional = Optional.of(registeredRootContext);
            if (optional.isPresent()){
                Arrays.stream(registeredRootContext.getBeanDefinitionNames()).forEach((s)-> {
                    //System.out.println("Bean Initialized by Root Context is " + s);
                });
                *//*for(String beanName : registeredRootContext.getBeanDefinitionNames()){
                    System.out.println("Initialized bean name is " + beanName);
                }*//*
            }
            // Chance to register our servlet, filters, listener, context etc
            // Let us try to register contextLoaderListerner with a rootContext
            //registerApplicationSecurityServlet(servletContext);
            registerPreprocessor(servletContext);
            registerDispatcherServlet(servletContext);

            //registerApplicationServletContextListener(servletContext);


        }

        protected static void registerPreprocessor(ServletContext servletContext) {
            ConfigurableApplicationContext registeredRootContext = (ConfigurableApplicationContext)servletContext.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
            EmployeeLoader employeeLoader = registeredRootContext.getBean(EmployeeLoader.class);
            employeeLoader.readCSV("dev/employee_details.csv");
            *//*List<Employee> empList = employeeLoader.readCSV("dev/employee_details.csv");
            empList.stream().forEach((s)-> {
                System.out.println(" Employee Details are " + s);
            });*//*
        }

        *//*protected static void registerApplicationServletContextListener(ServletContext servletContext){
            ApplicationServletContextLister applicationServletContextLister = new ApplicationServletContextLister();
            servletContext.addListener(applicationServletContextLister);
        }*//*

        *//*protected static void registerApplicationSecurityServlet(ServletContext servletContext){
            ApplicationSecurityServlet securityServlet = new ApplicationSecurityServlet();
            ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(ApplicationSecurityServlet.class.getName(), securityServlet);
            servletRegistration.addMapping(new String[] {"/secure/*"});
            servletRegistration.setLoadOnStartup(1);
        }*//*

        protected static void registerDispatcherServlet(ServletContext servletContext){
            *//**
             * 1. Build ServletAppContext using the configuration file
             * 2. Build the instance of the Dispatcher servlet initialized by ServletAppContext
             * 3. Register the dispatcher servlet in the servlet context
             * 4. Set the servlet Mapping for the dispatcher servlet
             *//*
            AnnotationConfigWebApplicationContext servletWebAppContext = new AnnotationConfigWebApplicationContext();
            servletWebAppContext.register(WebConfig.class);
            *//*servletWebAppContext.refresh();

            Arrays.stream(servletWebAppContext.getBeanDefinitionNames()).forEach(
                    (beanName) -> System.out.println("Bean created by child Context is " + beanName)
            );*//*
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
    }*/

/*@Bean
public static PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer()
        throws IOException {
    String env = System.getProperty("env");
    String resource = "classpath:/%1$s/**.properties";
    String resourcePath = String.format(resource,env);
    //resourcePath = "classpath:/dev/**.properties";
    PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
    ppc.setLocations(new PathMatchingResourcePatternResolver().getResources(resourcePath));
    return ppc;
}

@Bean
public static PropertySourcesPlaceholderConfigurer properties(){
    PropertySourcesPlaceholderConfigurer pspc
            = new PropertySourcesPlaceholderConfigurer();

    String env = System.getProperty("env");
    String resource = "/%1$s/**.properties";
    String resourcePath = String.format(resource,env);
    Resource[] resources = new ClassPathResource[ ]
            { new ClassPathResource( resourcePath ) };
    pspc.setLocations( resources );
    pspc.setIgnoreUnresolvablePlaceholders( true );
    return pspc;
}*/
}
