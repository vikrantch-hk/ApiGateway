package com.devglan.gatewayservice.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by ashish.sethi on 10/9/2015.
 */
public class Crypt {
  private static String password = "edf31";
  private static byte[] iv = "0000000000000000".getBytes();

  public static String encrypt(String content) throws Exception {
    byte[] input = content.getBytes("utf-8");

    MessageDigest md = MessageDigest.getInstance("SHA-1");
    byte[] thedigest = md.digest(password.getBytes("utf-8"));
    thedigest = Arrays.copyOf(thedigest, 16); // use only first 128 bit
    SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, skc, new IvParameterSpec(iv));

    byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
    int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
    ctLength += cipher.doFinal(cipherText, ctLength);
    return DatatypeConverter.printHexBinary(cipherText);
  }


  public static String decrypt(String encrypted) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-1");
    byte[] thedigest = md.digest(password.getBytes("utf-8"));
    thedigest = Arrays.copyOf(thedigest, 16); // use only first 128 bit
    SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
    Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    dcipher.init(Cipher.DECRYPT_MODE, skc, new IvParameterSpec(iv));

    byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
    return new String(clearbyte);
  }

  private static byte[] toByte(String hexString) {
    int len = hexString.length()/2;
    byte[] result = new byte[len];
    for (int i = 0; i < len; i++) {
      result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
    }
    return result;
  }
}

