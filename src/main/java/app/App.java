package app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"model", "controller", "services", "controller.validator"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan("model")
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        
    }

}
