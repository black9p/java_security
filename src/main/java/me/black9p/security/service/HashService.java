package me.black9p.security.service;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 해시함수 관련 서비스
 */
@Component
public class HashService {

    public String generateHashValue(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(plainText.getBytes(StandardCharsets.UTF_8));

        return Hex.toHexString(md.digest());
    }
}
