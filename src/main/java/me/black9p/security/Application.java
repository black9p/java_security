package me.black9p.security;

import lombok.RequiredArgsConstructor;
import me.black9p.security.jca.ProviderService;
import me.black9p.security.random.RandomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final ProviderService providerService;
    private final RandomService randomService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Bean
    CommandLineRunner execute(){
        return args -> randomService.generateRandomNumber();
    }
}
