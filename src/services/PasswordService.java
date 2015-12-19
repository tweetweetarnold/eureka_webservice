package services;

//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import org.myorg.SystemUnavailableException;
//import sun.misc.BASE64Encoder;
//import sun.misc.CharacterEncoder;

public class PasswordService {
	/*
	 * This methods parses in a String object as an input for AES encrpytion returns a ciphertext in
	 * the form of a String object
	 */
	public static String encryptPassword(String inputPassword) {
		return inputPassword;
		// try {
		// // String text = "Hello World";
		// String key = "Bar12345Bar12345"; // 128 bit key
		//
		// // Create key and cipher
		// Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		// Cipher cipher = Cipher.getInstance("AES");
		//
		// // encrypt the text
		// cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		// byte[] encrypted = cipher.doFinal(inputPassword.getBytes());
		// // System.err.println(new String(encrypted));
		// return new String(encrypted);
		//
		// // decrypt the text
		// // cipher.init(Cipher.DECRYPT_MODE, aesKey);
		// // String decrypted = new String(cipher.doFinal(encrypted));
		// // System.err.println(decrypted);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return null;
	}

	/*
	 * This methods parses in a ciphertext String object as an input for AES decrpytion returns a
	 * plaintext in the form of String object
	 */
	public static String decryptPassword(String encryptedPassword) {
		return encryptedPassword;

		// try {
		// // String text = "Hello World";
		// String key = "Bar12345Bar12345"; // 128 bit key
		//
		// // Create key and cipher
		// Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		// Cipher cipher = Cipher.getInstance("AES");
		//
		// // decrypt the text
		// cipher.init(Cipher.DECRYPT_MODE, aesKey);
		// byte[] encrypted = encryptedPassword.getBytes();
		// String decrypted = new String(cipher.doFinal(encrypted));
		// // System.err.println(decrypted);
		// return decrypted;
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return null;
	}
}