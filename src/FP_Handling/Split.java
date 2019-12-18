package FP_Handling;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author Tirelli Andrea
 *
 */

public class Split {
	/* TODO scegliere se optare per la queue che incoda i file o solo le string deti path dei file 
	 * da aprire in un secondo momento nella funzione solo quando necessario */
	public static void split(File f) throws Exception {
		String path = f.getAbsolutePath();
		
		// Apre il file corrente 
        RandomAccessFile raf = new RandomAccessFile(f, "r");
        
        // Numero di split per il file
        long numSplits = 10; //from user input, extract it from args
        
        // Dimensione in byte del file aperto
        long sourceSize = raf.length();
        System.out.println("Byte file size= " + sourceSize);
        
        // Numero di byte per ogni split richiesto
        long bytesPerSplit = sourceSize/numSplits ; 
        System.out.println("Byte per slipt= " + bytesPerSplit);
        
        // Numero di byte rimanenti per l'ultimo split
        long remainingBytes = sourceSize % numSplits;
        System.out.println("Byte per last split= " + remainingBytes);

        // Buffer di split di 8KB
        int maxReadBufferSize = 8 * 1024; //8KB
        
        for(int destIx=1; destIx <= numSplits; destIx++) {
        	// TODO gestione della creazione della dir per salvare gli split
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(path + "/split."+destIx));
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
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(path + "split."+(numSplits+1)));
            readWrite(raf, bw, remainingBytes);
            bw.close();
        }
            raf.close();
    }

    static void readWrite(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException {
        byte[] buf = new byte[(int) numBytes];
        int val = raf.read(buf);
        System.out.println(val);
        if(val != -1) {
            bw.write(buf);
        }
    }
}
