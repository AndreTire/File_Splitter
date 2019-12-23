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
	
	public static void decompress(String file, String path) throws IOException {
		FileInputStream fin = new FileInputStream(path + "/" + file);
		InflaterInputStream zip = new InflaterInputStream(fin);
		
		FileOutputStream fot = new FileOutputStream(path + "/" + file);
		
		while(zip.read() != -1)
			fot.write(zip.read());
		
		fot.close();
		zip.close();
	}
}
