package me.black9p.security;

import lombok.RequiredArgsConstructor;
import me.black9p.security.service.*;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final ProviderService providerService;
    private final RandomService randomService;
    private final HashService hashService;
    private final PrivateKeyService privateKeyService;
    private final CipherService cipherService;
    private final AuthenticationService authenticationService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Bean
    CommandLineRunner execute(){
        return args -> {
            providerService.enrollProvider();

            String plainText = "Security is very important";
            System.out.println("Plain Text : " + plainText);

            String algorithm = "HmacSHA256";
            SecretKey privateKey = privateKeyService.createPrivateKeyByKeyGenerator(algorithm, 256);

            byte[] mac = authenticationService.generateMAC(algorithm, privateKey, plainText);
            System.out.println(Hex.toHexString(mac));
        };
    }
}
