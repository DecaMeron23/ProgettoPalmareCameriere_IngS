/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package classi.ordine;

import classi.menu.Piatto;

/**
 * Class Piatto Ordinato.
 */
public class PiattoOrdinato {

	/** il piatto scelto */
	private Piatto piatto;

	/** la quantita scelta */
	private int quantita = 1;

	/** il commento per il piatto */
	private String commento;

	/**
	 * costuttore del piatto ordinato.
	 *
	 * @param piatto il piatto scelto
	 */
	public PiattoOrdinato(Piatto piatto) {
		this("", piatto);
	}

	/**
	 * costruttore del piatto ordinato con il commento.
	 *
	 * @param commento il commento del piatto
	 * @param piatto   il piatto scelto
	 */
	public PiattoOrdinato(String commento, Piatto piatto) {
		super();
		this.commento = commento;
		this.piatto = piatto;
	}

	/**
	 * prendi le quantita dei piatti ordinati.
	 *
	 * @return il numero dei piatti ordinati
	 */
	public int getQuantita() {
		return quantita;
	}

	/**
	 * prendi il prezzo.
	 *
	 * @return il prezzo
	 */
	public double getPrezzo() {
		return piatto.getPrezzo() * quantita;
	}

	/**
	 * prendi il commento.
	 *
	 * @return il commento
	 */
	public String getCommento() {
		return commento;
	}

	/**
	 * imposta la quantita dei piatti da ordinare.
	 *
	 * @param quantita la nuova quantita
	 */
	public void setQuantita(int quantita) {
		if (quantita < 0) {
			this.quantita = 0;
		}
		this.quantita = quantita;
	}

	/**
	 * imposta il commento.
	 *
	 * @param commento il nuovo commento
	 */
	public void setCommento(String commento) {
		this.commento = commento;
	}

	/**
	 * prendi il piatto.
	 *
	 * @return il piatto
	 */
	public Piatto getPiatto() {
		return piatto;
	}
	
	/**
	 * imposta il piatto.
	 *
	 * @param piatto il nuovo piatto
	 */
	public void setPiatto(Piatto piatto) {
		this.piatto = piatto;
	}
}