package me.black9p.security.service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;

import java.security.Provider;
import java.security.Security;

/**
 * Provider 등록 서비스
 */
@Component
public class ProviderService {

    public void enrollProvider() {
        // Enroll Bouncy Castle Provider
        Security.addProvider(new BouncyCastleProvider());

//        Provider provider = Security.getProvider("BC");
//        if (provider != null) {
//            System.out.println("BC available");
//        } else {
//            System.out.println("BC not available");
//        }
    }
}
