/**
 *  @author Benedetta Vitale & Emilio Meroni
 */
package classi.tavolo;

import java.util.ArrayList;
import java.util.List;

import classi.ordine.Ordine;

/**
 * The Class Resoconto del Tavolo.
 */
public class ResocontoTavolo {
    
	/** costante del costo del coperto COSTO_COPERTO. */
	public static final double COSTO_COPERTO  = 2;
	
	/** il numero dei coperti */
	private int numCoperti;
    
    /** il prezzo totale. */
    private double prezzoTotale;
    
    /** la lista ordini richiesti */
    private List<Ordine> listaOrdini;
    
    /**
     * Costruttore del ResocontoTavolo.
     *
     * @param num_coperti the num coperti
     */
    public ResocontoTavolo(int num_coperti) {
		super();
		this.numCoperti = num_coperti;
		listaOrdini = new ArrayList<Ordine>();
	}

	/**
	 * prendi il numero dei coperti.
	 *
	 * @return il numero dei coperti
	 */
	public int getNumCoperti() {
        return numCoperti;
    }

    /**
     * prendi il prezzo totale.
     *
     * @return il prezzo totale
     */
    public double getPrezzoTotale() {
        aggiornaPrezzoTotale();
        return prezzoTotale;
    }

    /**
     * Aggiorna il prezzo totale.
     */
    private void aggiornaPrezzoTotale() {
    	double prezzo = numCoperti * COSTO_COPERTO;
    	for (Ordine ordine : listaOrdini) {
			prezzo += ordine.getPrezzo_parziale();
		}
    	prezzoTotale = prezzo;
	}

    /**
     * Aggingi un ordine al resoconto.
     *
     * @param ordine l'ordine da aggiungere
     */
    public void aggingiOrdine(Ordine ordine) {
		listaOrdini.add(ordine);
	}
    
    /**
     * prendi la lista degli ordini.
     *
     * @return la lista ordini
     */
    public List<Ordine> getListaOrdini() {
		return listaOrdini;
	}
}