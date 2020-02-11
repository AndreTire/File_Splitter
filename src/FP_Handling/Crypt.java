package FP_Handling;

/**
 * 
 * @author Tirelli Andrea
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Crypt {
	/**
	 * Metodo per generare un hash della chiave in MD5
	 * @param plaintext
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String hashMD5(String plaintext) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 )
		  hashtext = "0"+hashtext;
		return hashtext;
	}
	
	/**
	 * Metodo per il crypto del file; esso si adatta in base all IN di cipherMode che decide se 
	 * criptare o decriptare
	 * @param cipherMode
	 * @param key
	 * @param in
	 * @param out // rimosso
	 * @return
	 */
	public byte[] crypto(int cipherMode, String key, String file) {
		try {
			File in = new File(file);
			
			Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(cipherMode, secretKey);

			FileInputStream inputStream = new FileInputStream(in);
			byte[] inputBytes = new byte[(int) in.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);
	       
			return outputBytes;
	       /* 
	       FileOutputStream outputStream = new FileOutputStream(out);
	       outputStream.write(outputBytes);

	       inputStream.close();
	       outputStream.close(); */
		} catch (NoSuchPaddingException | NoSuchAlgorithmException 
				| InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Crypt c = new Crypt();
		String key = c.hashMD5("test");
		System.out.println(key);
		String file="";
		
		try {
			c.crypto(Cipher.ENCRYPT_MODE, key, file);
			c.crypto(Cipher.DECRYPT_MODE, key, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
