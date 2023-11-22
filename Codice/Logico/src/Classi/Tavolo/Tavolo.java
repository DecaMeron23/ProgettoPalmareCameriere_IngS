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
	
	/** il numero dei posti massimi che ha il tavolo*/
	private int num_posti_massimi;
	
	/** Stato del tavolo */
	private StatoTavolo stato;
	
	/** il resoconto del tavolo */
	public ResocontoTavolo resocontoTavolo;

	/**
	 * Costruttore del tavolo.
	 *
	 * @param nome il nome del tavolo
	 * @param num_post_massimi il numero dei posti massimi del tavolo
	 */
	public Tavolo(int nome, int num_post_massimi) {
		super();
		this.nome = nome;
		this.num_posti_massimi = num_post_massimi;
		stato = StatoTavolo.LIBERO;
		
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
		return num_posti_massimi;
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
		this.num_posti_massimi = num_post_massimi;
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
	 * Libera il tavolo e segna che il tavolo è da pulire.
	 */
	public void pagato() {
		setStato(StatoTavolo.DA_PULIRE);
		resocontoTavolo = null;
	}
	
	/**
	 * metodo toString
	 *
	 * @return il tavolo in forma di stringa
	 */
	@Override
	public String toString() {
		return "Tavolo n° " + nome + ", numero posti: " + num_posti_massimi;
	}
}