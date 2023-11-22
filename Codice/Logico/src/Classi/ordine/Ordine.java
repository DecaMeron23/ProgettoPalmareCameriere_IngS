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
	 * costruttore di ordine.
	 *
	 * @param listaPiattiOrdinati la lista dei piatti ordinati
	 */
	public Ordine(List<PiattoOrdinato> listaPiattiOrdinati) {
		this.listaPiattiOrdinati = listaPiattiOrdinati;
		eliminaOccorrenzeZero();
		aggiornaPrezzoParziale();
	}
	

	/**
	 * Elimina tutti i piatti ordinati che hanno le quantità poste a zero.
	 */
	private void eliminaOccorrenzeZero() {
		List<PiattoOrdinato> listaDaEliminare = new ArrayList<>();
		for (PiattoOrdinato piattoOrdinato : listaPiattiOrdinati) {
			if(piattoOrdinato.getQuantita() == 0) {
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
			prezzo += prezzo + piatto_ordinato.getPrezzo() * piatto_ordinato.getQuantita();
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
}