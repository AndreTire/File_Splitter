package FP_Handling;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.zip.Deflater; 
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * 
 * @author Tirelli Andrea
 *
 */

public class Zip {
	/**
	 * Metodo per la compressione del file
	 * @param file
	 * @param path
	 * @throws IOException
	 */
	public static void compress(String file, String path) throws IOException  {
		FileInputStream fin = new FileInputStream(path + "/" + file);
		
		FileOutputStream fzip = new FileOutputStream(path + "/" + file + ".zip");
		DeflaterOutputStream deflat = new DeflaterOutputStream(fzip);
		
		while((int)fin.read() != -1) {
			deflat.write(fin.read());
		}
		
		fin.close();
		deflat.close();		
	}
	
	/**
	 * Metodo per la decompressione del file
	 * @param file
	 * @param path
	 * @throws IOException
	 */
	public static void decompress(String file, String path) throws IOException {
		FileInputStream fin = new FileInputStream(path + "/" + file);
		InflaterInputStream zip = new InflaterInputStream(fin);
		
		FileOutputStream fot = new FileOutputStream(path + "/" + file);
		
		while(zip.read() != -1)
			fot.write(zip.read());
		
		fot.close();
		zip.close();
	}
	
	public static void main(String[] args) {
		String file = "";
		String path = "";
		
		Zip z = new Zip();
		
		try {
			z.compress(file, path);
			z.decompress(file, path);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
