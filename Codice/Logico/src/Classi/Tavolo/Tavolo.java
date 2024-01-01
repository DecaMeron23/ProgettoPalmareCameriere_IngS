/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package classi.tavolo;

import classi.enumerativi.StatoTavolo;

/**
 * Classe che rappresenta il tavolo del ristorante
 */
public class Tavolo {

	/** nome del tavolo un intero */
	private int nome;

	/** il numero dei posti massimi che ha il tavolo */
	private int numPostiMassimi;

	/** Stato del tavolo */
	private StatoTavolo stato;

	/**
	 * Costruttore del tavolo.
	 *
	 * @param nome             il nome del tavolo
	 * @param numPostiMassimi il numero dei posti massimi del tavolo
	 * @param resconto 
	 */
	public Tavolo(int nome, int numPostiMassimi , StatoTavolo stato) {
		super();
		this.nome = nome;
		this.numPostiMassimi = numPostiMassimi;
		this.stato = stato;
	}

	/**
	 * prendi il nome
	 *
	 * @return il nome del tavolo
	 */
	public int getNome() {
		return nome;
	}

	/**
	 * prendi il numero dei posti massimi.
	 *
	 * @return il numero dei posti massimi al tavolo
	 */
	public int getNumPostiMassimi() {
		return numPostiMassimi;
	}

	/**
	 * predi lo stato del tavolo.
	 *
	 * @return lo stato del tavolo
	 */
	public StatoTavolo getStato() {
		return stato;
	}

	/**
	 * imposta il numero massiomo dei posti.
	 *
	 * @param num_post_massimi il nuovo numero massimo
	 */
	public void setNumPostiMassimi(int num_post_massimi) {
		this.numPostiMassimi = num_post_massimi;
	}

	/**
	 * imposta lo stato del tavolo.
	 *
	 * @param stato il nuovo stato del tavolo
	 */
	public void setStato(StatoTavolo stato) {
		this.stato = stato;
	}

	/**
	 * imposta il nuovo nome del tavolo.
	 *
	 * @param nome il nuovo nome
	 */

	public void setNome(int nome) {
		this.nome = nome;
	}


	/**
	 * metodo toString
	 *
	 * @return il tavolo in forma di stringa
	 */
	@Override
	public String toString() {
		return "Tavolo n° " + nome + ", numero posti: " + numPostiMassimi;
	}
	
	/**
	 * @param t2 il tavolo da conforntare 
	 * @return true se il nome dei due tavoli sono uguali, sennò false
	 */
	public boolean equals(Tavolo t2) {
		Tavolo t1 = this;
		return t1.nome == t2.nome;
	}
}