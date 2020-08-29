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
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final ProviderService providerService;
    private final RandomService randomService;
    private final HashService hashService;
    private final PrivateKeyService privateKeyService;
    private final CipherService cipherService;

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

            String transformation = "AES/ECB/PKCS5Padding";
            String algorithm = "AES";
            SecretKey privateKey = privateKeyService.createPrivateKeyByKeyGenerator(algorithm, 256);

            byte[] encrypted = cipherService.encrypt(transformation, privateKey, plainText.getBytes(StandardCharsets.UTF_8));
            System.out.println("Encrypted Text: " + Hex.toHexString(encrypted));

            byte[] decrypted = cipherService.decrypt(transformation, privateKey, encrypted);
            System.out.println("Decrypted Text: " + new String(decrypted, StandardCharsets.UTF_8));
        };
    }
}
