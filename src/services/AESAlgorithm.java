package services;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESAlgorithm {
	public static String algo = "AES";
	String secret = "agsjdjegqbentkdh";
	public byte[] keyValue = secret.getBytes();

//	public AESAlgorithm(String key) {
//		keyValue = key.getBytes();
//	}

	public Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, algo);
		return key;
	}
	
	public String encrypt(String password) throws Exception{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(algo);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(password.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);		
		return encryptedValue;
	}
	
	public String Decrypt(String encryptedPassword) throws Exception{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(algo);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedPassword);
		byte[] decVal = c.doFinal(decodedValue);
		String decryptedPassword = new String(decVal);
		return decryptedPassword;
	}
	
	
}
