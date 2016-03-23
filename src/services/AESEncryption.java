package services;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESEncryption {
	private static String algo = "AES";
	private static String secret = "agsjdjegqbentkdh";
	private static byte[] keyValue = secret.getBytes();

	public static String decrypt(String encryptedPassword) {
		String decryptedPassword = null;
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(algo);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedPassword);
			byte[] decVal = c.doFinal(decodedValue);
			decryptedPassword = new String(decVal);
		} catch (Exception e) {
			System.out.println("Error occurred at AES Encryption: " + e.getMessage());
			e.printStackTrace();
		}
		return decryptedPassword;
	}

	public static String encrypt(String password) {
		String encryptedValue = null;
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(algo);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(password.getBytes());
			encryptedValue = new BASE64Encoder().encode(encVal);
		} catch (Exception e) {
			System.out.println("Error occurred at AES Encryption: " + e.getMessage());
			e.printStackTrace();
		}
		return encryptedValue;
	}

	public static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, algo);
		return key;
	}

}
