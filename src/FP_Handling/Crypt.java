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
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Crypt {
	
	/**
	 * Metodo per generare una chiave per l'algoritmo AES
	 * @param keyValue
	 * @param ALGO
	 * @return
	 * @throws Exception
	 */
	private Key generateKey(byte[] keyValue, String ALGO) throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
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
	
	public static void main(String[] args) {
		Crypt c = new Crypt();
		String key="";
		String file="";
		
		try {
			c.crypto(Cipher.ENCRYPT_MODE, key, file);
			c.crypto(Cipher.DECRYPT_MODE, key, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
