/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package classi.ordine;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che raggruppa tutti i piatti ordinati.
 */
public class Ordine {

	/** The lista piatti ordinati. */
	private List<PiattoOrdinato> listaPiattiOrdinati;

	/** The prezzo parziale. */
	private double prezzoParziale;

	/**
	 * Il numero dell'ordine;
	 */
	private int numeroOrdine;

	/**
	 * Il contatore per i piatti ordinati
	 */
	private int counterPiatto = 0;

	/**
	 * costruttore di ordine.
	 *
	 * @param listaPiattiOrdinati la lista dei piatti ordinati
	 */
	public Ordine(List<PiattoOrdinato> listaPiattiOrdinati, int numeroOrdine, int counterPiatto) {
		this.counterPiatto = counterPiatto;
		this.listaPiattiOrdinati = listaPiattiOrdinati;
		this.numeroOrdine = numeroOrdine;
		eliminaOccorrenzeZero();
		aggiornaPrezzoParziale();
	}

	/**
	 * @param listaPiattiOrdinati la lista dei piatti ordinati
	 * @param numeroOrdine        il numero dell'ordine
	 */
	public Ordine(List<PiattoOrdinato> listaPiattiOrdinati, int numeroOrdine) {
		this(listaPiattiOrdinati, numeroOrdine, 0);
	}

	/**
	 * Elimina tutti i piatti ordinati che hanno le quantità poste a zero.
	 */
	private void eliminaOccorrenzeZero() {
		List<PiattoOrdinato> listaDaEliminare = new ArrayList<>();
		for (PiattoOrdinato piattoOrdinato : listaPiattiOrdinati) {
			if (piattoOrdinato.getQuantita() == 0) {
				listaDaEliminare.add(piattoOrdinato);
			}
		}
		listaPiattiOrdinati.removeAll(listaDaEliminare);
	}

	/**
	 * prendi il prezzo parziale.
	 *
	 * @return il prezzo parziale
	 */
	public double getPrezzo_parziale() {
		aggiornaPrezzoParziale();
		return prezzoParziale;
	}

	/**
	 * Aggiorna prezzo parziale.
	 */
	private void aggiornaPrezzoParziale() {
		double prezzo = 0;
		for (PiattoOrdinato piatto_ordinato : listaPiattiOrdinati) {
			prezzo += piatto_ordinato.getPrezzo();
		}
		prezzoParziale = prezzo;
	}

	/**
	 * prendi la lista piatti ordinati.
	 *
	 * @return la lista dei piatti ordinati
	 */
	public List<PiattoOrdinato> getListaPiattiOrdinati() {
		return listaPiattiOrdinati;
	}

	/**
	 * @return il numero dell'ordine
	 */
	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public int getIDPiatto() {
		return ++counterPiatto;
	}

	public int getCounter() {
		return counterPiatto;
	}

}