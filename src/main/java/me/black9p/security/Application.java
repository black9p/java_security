package me.black9p.security;

import lombok.RequiredArgsConstructor;
import me.black9p.security.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final ProviderService providerService;
    private final RandomService randomService;
    private final HashService hashService;
    private final KeyService keyService;
    private final CipherService cipherService;
    private final AuthenticationService authenticationService;
    private final SignatureService signatureService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Bean
    CommandLineRunner execute(){
        return args -> {
            providerService.enrollProvider();

            String algorithm = "RSA";
            KeyPair keyPair = keyService.createKeyPair(algorithm, 1024);

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            String plainText = "Security is very important";

            byte[] signature = signatureService.sign(privateKey, plainText.getBytes(StandardCharsets.UTF_8));
            boolean verified = signatureService.verify(publicKey, signature, plainText.getBytes(StandardCharsets.UTF_8));

            System.out.println("verified: " + verified);
        };
    }
}
