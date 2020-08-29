package me.black9p.security.service;

import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 비밀키 서비스
 */
@Component
public class KeyService {

    /**
     * KeyGenerator 를 사용한 비밀키 생성
     * @param algorithm 사용할 알고리즘
     * @param keySize 키 사이즈
     * @return
     * @throws NoSuchAlgorithmException
     */
    public SecretKey createPrivateKeyByKeyGenerator(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);

        return keyGenerator.generateKey();
    }

    /**
     * SecretKeySpec 을 사용한 비밀키 생성
     * @param algorithm 사용할 알고리즘
     * @return
     */
    public SecretKeySpec createPrivateKeyBySecretKeySpec(String algorithm) {
        SecureRandom random = new SecureRandom();
        byte[] keyData = new byte[16];
        random.nextBytes(keyData);

        return new SecretKeySpec(keyData, algorithm);
    }

    /**
     * 공개키쌍 생성
     * @param algorithm 사용 알고리즘
     * @param keySize 키 사이즈
     * @return
     * @throws NoSuchAlgorithmException
     */
    public KeyPair createKeyPair(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
        generator.initialize(keySize);

        return generator.generateKeyPair();
    }
}
