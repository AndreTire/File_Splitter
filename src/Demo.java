
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import FP_Handling.Split;
import FP_Queue.Queue_File;

public class Demo {

	public static void main(String[] args) throws Exception {
		Split s = new Split();
		Queue_File q = new Queue_File();
		
		int numSplit = 0, buffer = 0;
		
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(in);
		
		try {
			System.out.println("Inserisci il numero di split per ogni file= ");
			numSplit = b.read();
			
			System.out.println("Inserisci la grandezza del buffer= ");
			buffer = b.read();
			
			System.out.println("Inerisci le path dei file da mettere in coda ('END' per terminare)\npath= ");
			String input = b.readLine();
			while(input.equals("END")) {
				q.push(input);
				System.out.println("path= ");
				input = b.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(q.sizeQueue() != 0)
			s.split(q.pop(), q.pop(), numSplit, buffer);

	}

}
