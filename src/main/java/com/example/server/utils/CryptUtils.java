package com.example.server.utils;

import com.google.common.base.Charsets;
import io.netty.handler.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * <功能描述> AES加解密工具
 *
 * @author
 * @date 2021/1/7 13:11
 */
public class CryptUtils {

    /**
     * 加密方式
     */
    private static final String ENCRYPT_TYPE = "AES";
    /**
     * 加密密盐
     */
    private static final byte[] CRYPT_KEY = new byte[]{0x48, 0x50, 0x52, 0x4d, 0x43, 0x5f, 0x41, 0x45, 0x53, 0x5f, 0x4b, 0x45, 0x59};
    /**
     * 单例
     */
    private static CryptUtils cryptUtils;
    /**
     * 加密cipher
     */
    private static Cipher encryptCipher;
    /**
     * 解密cipher
     */
    private static Cipher decryptCipher;

    /**
     * encryptCipher、decryptCipher初始化
     */
    private static void init() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        encryptCipher = Cipher.getInstance(ENCRYPT_TYPE);
        decryptCipher = Cipher.getInstance(ENCRYPT_TYPE);
        encryptCipher.init(Cipher.ENCRYPT_MODE, generateAesKey(new String(CRYPT_KEY)));
        decryptCipher.init(Cipher.DECRYPT_MODE, generateAesKey(new String(CRYPT_KEY)));
    }

    /**
     * 单例获取方法实现
     *
     * @return 单例
     */
    public static synchronized CryptUtils getInstance() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        if (cryptUtils == null) {
            cryptUtils = new CryptUtils();
            init();
        }
        return cryptUtils;
    }

    /**
     * 加密算法
     *
     * @param encryptString 待加密字符串
     * @return 加密字符串
     */
    public String aesEncrypt(String encryptString) throws BadPaddingException, IllegalBlockSizeException {
        return new String(Hex.encodeHex(encryptCipher.doFinal(encryptString.getBytes(Charsets.UTF_8)))).toUpperCase();
    }

    /**
     * 解密算法
     *
     * @param decryptString 待解密字符串
     * @return 解密字符串
     */
    public String aesDecrypt(String decryptString) throws DecoderException, BadPaddingException, IllegalBlockSizeException, org.apache.commons.codec.DecoderException {
        return new String(decryptCipher.doFinal(Hex.decodeHex(decryptString.toCharArray())));
    }

    /**
     * 产生mysql-aes_encrypt
     *
     * @param key 加密的密盐
     * @return key
     */
    private static Key generateAesKey(final String key) {
        final byte[] finalKey = new byte[16];
        int i = 0;
        for (byte b : key.getBytes(Charsets.UTF_8)) {
            finalKey[i++ % 16] ^= b;
        }
        return new SecretKeySpec(finalKey, ENCRYPT_TYPE);
    }
}
