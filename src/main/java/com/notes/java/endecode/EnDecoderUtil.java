package com.notes.java.endecode;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.util.Base64;

/**
 * 文件描述 加密解密工具类
 **/
public class EnDecoderUtil {

    private static final String ALGORITHM_MD5 = "md5";
    private static final String ALGORITHM_DES = "des";
    private static final String ALGORITHM_RSA = "rsa";
    private static final String ALGORITHM_MD5_RSA = "MD5withRSA";
    private static final String ALGORITHM_SHA = "sha";
    private static final String ALGORITHM_MAC = "HmacMD5";

    private static SecureRandom secureRandom;
    private static KeyPair keyPair;

    static {
        secureRandom = new SecureRandom();
        try {
            //创建密钥对KeyPair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            //密钥长度推荐为1024位
            keyPairGenerator.initialize(1024);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * MD5简单加密
     *
     * @param content 加密内容
     * @return String
     */
    public static String md5Encrypt(final String content) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(ALGORITHM_MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        md5.update(text.getBytes());
        //digest()最后返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        //BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        BigInteger digest = new BigInteger(md5.digest(content.getBytes()));
        //32位
        return digest.toString(16);
    }

    /**
     * base64加密
     *
     * @param content 待加密内容
     * @return byte[]
     */
    public static byte[] base64Encrypt(final String content) {
        return Base64.getEncoder().encode(content.getBytes());
    }

    /**
     * base64解密
     *
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public static byte[] base64Decrypt(final byte[] encoderContent) {
        return Base64.getDecoder().decode(encoderContent);
    }

    /**
     * 根据key生成秘钥
     *
     * @param key 给定key,要求key至少长度为8个字符
     * @return SecretKey
     */
    public static SecretKey getSecretKey(final String key) {
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory instance = SecretKeyFactory.getInstance(ALGORITHM_DES);
            SecretKey secretKey = instance.generateSecret(desKeySpec);
            return secretKey;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * DES加密
     *
     * @param key     秘钥key
     * @param content 待加密内容
     * @return byte[]
     */
    public static byte[] DESEncrypt(final String key, final String content) {
        return processCipher(content.getBytes(), getSecretKey(key), Cipher.ENCRYPT_MODE, ALGORITHM_DES);
    }

    /**
     * DES解密
     *
     * @param key            秘钥key
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public static byte[] DESDecrypt(final String key, final byte[] encoderContent) {
        return processCipher(encoderContent, getSecretKey(key), Cipher.DECRYPT_MODE, ALGORITHM_DES);
    }

    /**
     * 加密/解密处理
     *
     * @param processData 待处理的数据
     * @param key         提供的密钥
     * @param opsMode     工作模式
     * @param algorithm   使用的算法
     * @return byte[]
     */
    private static byte[] processCipher(final byte[] processData, final Key key,
                                        final int opsMode, final String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            //初始化
            cipher.init(opsMode, key, secureRandom);
            return cipher.doFinal(processData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取私钥，用于RSA非对称加密.
     *
     * @return PrivateKey
     */
    public static PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    /**
     * 获取公钥，用于RSA非对称加密.
     *
     * @return PublicKey
     */
    public static PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    /**
     * 获取数字签名，用于RSA非对称加密.
     *
     * @return byte[]
     */
    public static byte[] getSignature(final byte[] encoderContent) {
        try {
            Signature signature = Signature.getInstance(ALGORITHM_MD5_RSA);
            signature.initSign(keyPair.getPrivate());
            signature.update(encoderContent);
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证数字签名，用于RSA非对称加密.
     *
     * @return byte[]
     */
    public static boolean verifySignature(final byte[] encoderContent, final byte[] signContent) {
        try {
            Signature signature = Signature.getInstance(ALGORITHM_MD5_RSA);
            signature.initVerify(keyPair.getPublic());
            signature.update(encoderContent);
            return signature.verify(signContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * RSA加密
     *
     * @param content 待加密内容
     * @return byte[]
     */
    public static byte[] RSAEncrypt(final String content) {
        return processCipher(content.getBytes(), keyPair.getPrivate(), Cipher.ENCRYPT_MODE, ALGORITHM_RSA);
    }

    /**
     * RSA解密
     *
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public static byte[] RSADecrypt(final byte[] encoderContent) {
        return processCipher(encoderContent, keyPair.getPublic(), Cipher.DECRYPT_MODE, ALGORITHM_RSA);
    }

    /**
     * SHA加密
     *
     * @param content 待加密内容
     * @return String
     */
    public static String SHAEncrypt(final String content) {
        try {
            MessageDigest sha = MessageDigest.getInstance(ALGORITHM_SHA);
            byte[] sha_byte = sha.digest(content.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (byte b : sha_byte) {
                //将其中的每个字节转成十六进制字符串：byte类型的数据最高位是符号位，通过和0xff进行与操作，转换为int类型的正整数。
                String toHexString = Integer.toHexString(b & 0xff);
                hexValue.append(toHexString.length() == 1 ? "0" + toHexString : toHexString);
            }
            return hexValue.toString();

//            StringBuffer hexValue2 = new StringBuffer();
//            for (int i = 0; i < sha_byte.length; i++) {
//                int val = ((int) sha_byte[i]) & 0xff;
//                if (val < 16) {
//                    hexValue2.append("0");
//                }
//                hexValue2.append(Integer.toHexString(val));
//            }
//            return hexValue2.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * HMAC加密
     *
     * @param key     给定秘钥key
     * @param content 待加密内容
     * @return String
     */
    public static byte[] HMACEncrypt(final String key, final String content) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM_MAC);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            //初始化mac
            mac.init(secretKey);
            return mac.doFinal(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
