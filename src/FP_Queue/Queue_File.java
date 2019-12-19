package FP_Queue;

/**
 * 
 * @author Tirelli Andrea
 * 
 * Classe che definisce la struttura dati per salvare i file da processare
 * La Coda definisce la struttura per lo store dei file da elaborare
 * La Coda definisce l'interfaccia per accedere ai file nella struttura
 * La Coda definisce le regole per accedere ai file nella struttura
 *
 * Si e deciso che la coda salverà solo i path dei file selezionati, in modo di avere
 * una lettura e scrittura più veloce su di essa.
 * 
 * Gli oggetti File vengono istanziati singolarmente a tempo di esecuziones
 */

import java.util.LinkedList; 
import java.util.Queue; 
import java.io.*;

public class Queue_File {
	/**
	 * Struttura dati coda di tipo File per salvare i file selezionati dall'utente
	 */
	private Queue<String> q;
	
	/**
	 * Costruttore dell'oggetto coda per i file
	 */
	public Queue_File() { q = new LinkedList<>(); }
	
	/**
	 * Metodo che aggiunge un file in coda
	 * @param f
	 */
	public void push(String f) { q.add(f); }
	
	/**
	 * Metodo che rimuove il primo file di posizione in coda
	 * @return
	 */
	public String pop() { return q.remove(); }
	
	/**
	 * Metodo che ritorna il primo Object in coda, senza rimuoverlo
	 * @return
	 */
	public String statusQueue() { return q.peek(); }

	/**
	 * Metodoo che ritorna la grandezza della coda nello stato attuale
	 * @return
	 */
	public int sizeQueue() { return q.size(); }
}
