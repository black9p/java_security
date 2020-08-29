package me.black9p.security.service;

import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * 비밀키 서비스
 */
@Component
public class PrivateKeyService {

    public void createPrivateKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(128);

        SecretKey key = keyGenerator.generateKey();
    }
}
