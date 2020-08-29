package me.black9p.security.service;

import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * 전자서명 관련 서비스
 */
@Component
public class SignatureService {

    /**
     * 전자서명
     * @param privateKey
     * @param plainData
     * @return
     */
    public byte[] sign(PrivateKey privateKey, byte[] plainData) throws GeneralSecurityException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(plainData);
        return signature.sign();
    }

    /**
     * 전자서명 검증
     * @param publicKey
     * @param signatureData
     * @param plainData
     * @return
     * @throws GeneralSecurityException
     */
    public boolean verify(PublicKey publicKey, byte[] signatureData, byte[] plainData) throws GeneralSecurityException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(plainData);
        return signature.verify(signatureData);
    }
}
