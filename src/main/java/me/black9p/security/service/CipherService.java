package me.black9p.security.service;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;

/**
 * 암/복호화 서비스
 */
@Component
public class CipherService {

    public byte[] encrypt(String transformation, SecretKey secretKey, byte[] plainData) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plainData);
    }

    public byte[] decrypt(String transformation, SecretKey secretKey, byte[] encryptData) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.update(encryptData);
    }
}
