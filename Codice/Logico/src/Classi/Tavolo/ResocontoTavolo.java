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
    
    private int counterOrdini = 0;
    
    /**
     * Costruttore del ResocontoTavolo.
     *
     * @param num_coperti the num coperti
     */
    public ResocontoTavolo(int num_coperti) {
    	this(num_coperti , new ArrayList<Ordine>() , 0);
    }
    
    /**
     * 
     * Costruttore del ResocontoTavolo.
     *
     * @param num_coperti il  numero dei coperti
     * @param listaOrdini la lista dei ordini effettuati
     * @param counterOridni il counter per numerare gli ordini
     */
    public ResocontoTavolo(int num_coperti , List<Ordine> listaOrdini , int counterOrdini) {
		super();
		this.numCoperti = num_coperti;
		this.listaOrdini = listaOrdini;
		this.counterOrdini = counterOrdini;
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

	/**
	 * questo metodo imposta la lista degli ordini
	 * 
	 * @param listaOrdini la lista degli ordini
	 */
	public void setListaOrdini(List<Ordine> listaOrdini) {
		this.listaOrdini = listaOrdini;
	}
	
	/**
	 * @return il counter degli ordini
	 */
	public int getNumeroOrdine() {
		return ++counterOrdini;
	}

	/**
	 * @return il numero attuale del counter
	 */
	public int getCounter() {
		return counterOrdini;
	}

//	/**
//	 * @return il tavolo a cui si riferisce il resoconto
//	 */
//	public Tavolo getTavolo() {
//		return tavolo;
//	}
}