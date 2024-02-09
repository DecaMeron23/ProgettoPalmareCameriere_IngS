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
	 * Id del piatto
	 */
	private int idPiatto;

	/**
	 * costuttore del piatto ordinato.
	 *
	 * @param piatto il piatto scelto
	 */
	public PiattoOrdinato(Piatto piatto , int quantita) {
		this("", piatto , 0 , quantita);
	}

	/**
	 * costruttore del piatto ordinato con il commento.
	 *
	 * @param commento il commento del piatto
	 * @param piatto   il piatto scelto
	 * @param id 
	 */
	public PiattoOrdinato(String commento, Piatto piatto, int id , int quantita) {
		super();
		this.commento = commento;
		this.piatto = piatto;
		this.idPiatto = id;
		this.quantita = quantita;
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
		} else {
			this.quantita = quantita;
		}
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

	public int getIdPiatto() {			
		return idPiatto;
	}

	public void setIDPiatto(int i) {
		idPiatto = i;
	}
	
	/**
	 * @param p il Piatto ordinato da confrontare
	 * @return true se il piatto, la quantità e il commento sono uguali, sennò false
	 */
	public boolean equals(PiattoOrdinato p) {
		return this.piatto.equals(p.getPiatto()) && this.quantita == p.quantita && this.commento.equals(p.getCommento());
	}
}