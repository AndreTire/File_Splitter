package FP_Handling;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
// import java.io.OutputStream;
import java.io.RandomAccessFile;


/**
 * 
 * @author Tirelli Andrea
 * Classe toolkit per lo split del file in processione nell'istante
 */

public class Split {	
	/**
	 * Funzione per lo split del file selezionato (dalla coda o dall'utente)
	 * @param path
	 * @param namefile
	 * @param num
	 * @param buffer
	 * @throws Exception
	 */
	public static void split(String path, String file, int num, int buffer) throws Exception {
		File f = new File(path + "/" + file);
		
		// https://docs.oracle.com/javase/7/docs/api/java/io/RandomAccessFile.html
        RandomAccessFile raf = new RandomAccessFile(f, "r"); // Apre in readonly in file     
        long numSplits = (long)num; // Numero di split per il file   
        long sourceSize = raf.length(); // Dimensione in byte del file aperto
        // System.out.println("Byte file size= " + sourceSize);
           
        long bytesPerSplit = sourceSize/numSplits ; // Numero di byte per ogni split richiesto
        // System.out.println("Byte per slipt= " + bytesPerSplit);
        
        long remainingBytes = sourceSize % numSplits; // Numero di byte rimanenti per l'ultimo split
        // System.out.println("Byte per last split= " + remainingBytes);

        int maxReadBufferSize = buffer * 1024; // Buffer di split
        
        System.out.println("File= " + f.getName() + "\nFile Descriptor= " + raf.getFD() + "\nSize= " + sourceSize + 
        		"\nNumber of Split= " + numSplits + "Bytes per Split= " + bytesPerSplit +
        		"\nRemaining Bytes= " + remainingBytes + "\nBuffer= " + maxReadBufferSize);
        
        // Creazione della path dove saranno creati gli split
        String splitPath = path + "/" + f.getName();
        // File p = new File(splitPath);
        // if(!p.exists())
        // p.mkdir();
        
        // Processo per la creazione degli split
        for(int destIx=1; destIx <= numSplits; destIx++) {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(splitPath + "/split."+destIx));
            if(bytesPerSplit > maxReadBufferSize) {
                long numReads = bytesPerSplit/maxReadBufferSize;
                long numRemainingRead = bytesPerSplit % maxReadBufferSize;
                for(int i=0; i<numReads; i++) {
                    readWrite(raf, bw, maxReadBufferSize);
                }
                if(numRemainingRead > 0) {
                    readWrite(raf, bw, numRemainingRead);
                }
            }else {
                readWrite(raf, bw, bytesPerSplit);
            }
            bw.close();
        }
        if(remainingBytes > 0) {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(splitPath + "/split."+(numSplits+1)));
            readWrite(raf, bw, remainingBytes);
            bw.close();
        }
            raf.close();
    }
	
	/**
	 * Funzione per la scrittura nei file splittati
	 * @param raf
	 * @param bw
	 * @param numBytes
	 * @throws IOException
	 */
    static void readWrite(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException {
        byte[] buf = new byte[(int) numBytes];
        int val = raf.read(buf);
        // System.out.println(val);
        if(val != -1) {
            bw.write(buf);
        }
    }
    
    /**
     * Funzione per la ricomposizione del file originale
     * @param FilePath
     * @param namefile
     */
    public void join(String path, String file) {
	    // long leninfile = 0;
	    long leng = 0;
	    int count = 1, data = 0;
	    try {
	        File filename = new File(path + "/" + file);
	        // System.out.println("path= " + filename.getAbsolutePath());
	        RandomAccessFile outfile = new RandomAccessFile(filename,"rw");

	        // OutputStream outfile = new BufferedOutputStream(new FileOutputStream(filename));
	        while (true) {
	            filename = new File(path + "/split." + count);
	            // System.out.println("path= " + filename.getAbsolutePath() + "\nfilename= " + filename.getName());
	            // System.out.println(filename.exists());
	            if (filename.exists()) {
	                // RandomAccessFile infile = new RandomAccessFile(filename,"r");
	                InputStream infile = new BufferedInputStream(new FileInputStream(filename));
	                data = infile.read();
	                while (data != -1) {
	                    outfile.write(data);
	                    data = infile.read();
	                }
	                leng++;
	                infile.close();
	                count++;
	            } else {
	                break;
	            }
	        }
	        System.out.println("File= " + filename.getName() + "\nFile Descriptor= " + outfile.getFD() + 
	        		"\nFile Size= " + outfile.length() + "Path= " + filename.getAbsolutePath());
	        
	        outfile.close();	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    
    /**
     * Metodo main per il debugging della classe
     * @param args
     */
	public static void main(String[] args) {
		Split s = new Split();
		String filename = "";
		String path = "";
		int numSplit = 0, buffer = 0;
		
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(in);
		
		try {
			System.out.println("Inserisci il numero di split per ogni file= ");
			numSplit = Integer.parseInt(b.readLine());
			
			System.out.println("Inserisci la grandezza del buffer= ");
			buffer = Integer.parseInt(b.readLine());
			
			s.split(path, filename, numSplit, buffer);
			s.join(path, filename);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

	
