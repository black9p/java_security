package me.black9p.security.service;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.GeneralSecurityException;

/**
 * 암/복호화 서비스
 */
@Component
public class CipherService {

    public byte[] encrypt(String transformation, SecretKey secretKey, byte[] plainData, byte[] iv) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(transformation);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(plainData);
    }

    public byte[] decrypt(String transformation, SecretKey secretKey, byte[] encryptData, byte[] iv) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(transformation);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.update(encryptData);
    }
}
