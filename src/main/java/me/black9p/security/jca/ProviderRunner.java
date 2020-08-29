package me.black9p.security.jca;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.Provider;
import java.security.Security;

/**
 * Enroll Provider
 */
@Component
public class ProviderRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Enroll Bouncy Castle Provider
        Security.addProvider(new BouncyCastleProvider());

        Provider provider = Security.getProvider("BC");
        if (provider != null) {
            System.out.println("BC available");
        } else {
            System.out.println("BC not available");
        }
    }
}
