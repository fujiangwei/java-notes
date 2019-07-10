package com.notes.java.endecode;

import org.apache.tomcat.util.buf.HexUtils;

import java.util.Base64;

/**
 * 文件描述 加密解密方式
 **/
public class EnDecodeDemo {

    public static void main(String[] args) {
        //md5简单加密
        String text = "i am text";
        System.out.println(EnDecoderUtil.md5Encrypt(text));

        //base64进行加密解密,通常用作对二进制数据进行加密
        byte[] base64Encrypt = EnDecoderUtil.base64Encrypt("123456789");
        String toHexString = HexUtils.toHexString(base64Encrypt);
        System.out.println(toHexString);
        byte[] base64Decrypt = EnDecoderUtil.base64Decrypt(base64Encrypt);
        System.out.println(new String(base64Decrypt));

        //DES对称加密/解密
        //要求key至少长度为8个字符
        String key = "123456789";
        //加密
        byte[] encode_bytes = EnDecoderUtil.DESEncrypt(key, "Hello, DES");
        System.out.println(Base64.getEncoder().encodeToString(encode_bytes));
        //解密
        byte[] decode_bytes = EnDecoderUtil.DESDecrypt(key, encode_bytes);
        System.out.println(new String(decode_bytes));

        //RSA
        //数据使用私钥加密
        byte[] en_byte = EnDecoderUtil.RSAEncrypt("Hi, RSA");
        System.out.println(Base64.getEncoder().encodeToString(en_byte));

        //用户使用公钥解密
        byte[] de_byte = EnDecoderUtil.RSADecrypt(en_byte);
        System.out.println(new String(de_byte));

        //服务器根据私钥和加密数据生成数字签名
        byte[] sign_byte = EnDecoderUtil.getSignature(en_byte);
        System.out.println(Base64.getEncoder().encodeToString(sign_byte));

        //用户根据公钥、加密数据验证数据是否被修改过
        boolean verify_result = EnDecoderUtil.verifySignature(en_byte, sign_byte);
        System.out.println(verify_result);

        //SHA
        String sha = EnDecoderUtil.SHAEncrypt("Hi, RSA");
        System.out.println(sha);

        //HMAC
        byte[] mac_bytes = EnDecoderUtil.HMACEncrypt(key, "Hi, HMAC");
        System.out.println(HexUtils.toHexString(mac_bytes));
    }
}
