package me.black9p.security.service;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * 난수 생성 서비스
 */
@Component
public class RandomService {

    public void generateRandomNumber() {
        SecureRandom secureRandom = new SecureRandom();

        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);

        System.out.println(Hex.toHexString(bytes));
    }
}
