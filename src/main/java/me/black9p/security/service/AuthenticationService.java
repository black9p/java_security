package me.black9p.security.service;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/**
 * 인증 서비스
 */
@Component
public class AuthenticationService {

    public byte[] generateMAC(String algorithm, SecretKey secretKey, String plainText) throws GeneralSecurityException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKey);
        return mac.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
    }
}
