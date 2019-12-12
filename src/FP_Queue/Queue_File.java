package FP_Queue;

/**
 * 
 * @author Tirelli Andrea
 * Classe che definisce la struttura dati Coda
 * La Coda definisce la struttura per lo store dei file selezionati dall'user
 * La Coda definisce l'interfaccia per il quale il software può accedere ai file in coda
 * La Coda definisce anche le regole per il quale i file vengono pushati e poppoati dalla coda 
 *
 */

import java.util.LinkedList; 
import java.util.Queue; 
import java.io.*;

public class Queue_File {
	/**
	 * Struttura dati coda ti tipo File per salvare i file selezionati dall'utente in coda
	 */
	private Queue<File> q;
	
	/**
	 * Costruttore dell'oggetto coda per i file
	 */
	public Queue_File() {
		q = new LinkedList<>();
	}
	
	/**
	 * Metodo che aggiunge un file in coda
	 * @param e
	 */
	public void push(File f) { q.add(f); }
	
	/**
	 * Metodo che rimuove il primo file di posizione in coda
	 * @return
	 */
	public File pop() { return q.remove(); }
	
	/**
	 * Metodo che ritorna il primo Object in coda, senza rimuoverlo
	 * @return
	 */
	public File statusQueue() { return q.peek(); }

	/**
	 * Metodoo che ritorna la grandezza della coda nello stato attuale
	 * @return
	 */
	public int sizeQueue() { return q.size(); }
}
