package me.black9p.security;

import lombok.RequiredArgsConstructor;
import me.black9p.security.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final ProviderService providerService;
    private final RandomService randomService;
    private final HashService hashService;
    private final KeyService keyService;
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

            String algorithm = "RSA";
            KeyPair keyPair = keyService.createKeyPair(algorithm, 1024);

            Key publicKey = keyPair.getPublic();
            Key privateKey = keyPair.getPrivate();

            System.out.println(publicKey.getFormat());    // 공개키 포맷 X.509
            System.out.println(privateKey.getFormat());   // 개인키 포맷 PKCS#8

            byte[] publicKeyBytes = publicKey.getEncoded();
            byte[] privateKeyBytes = privateKey.getEncoded();

            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            PrivateKey priKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

            System.out.println(publicKey.equals(pubKey));
            System.out.println(privateKey.equals(priKey));
        };
    }
}
