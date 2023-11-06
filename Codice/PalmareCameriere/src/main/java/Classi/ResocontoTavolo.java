/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Classi;

import java.util.ArrayList;
import java.util.List;

public class ResocontoTavolo {
    
	private static final double COSTO_COPERTO  = 2;
	
	private int num_coperti;
    private double prezzo_totale;
    private List<Ordine> lista_ordini;
    private int numero_ordini;
    
    public ResocontoTavolo(int num_coperti) {
		super();
		this.num_coperti = num_coperti;
		lista_ordini = new ArrayList<Ordine>();
		numero_ordini = 0;
	}

	private int getNum_coperti() {
        return num_coperti;
    }

    public double getPrezzo_totale() {
        aggiorna_prezzo_totale();
        return prezzo_totale;
    }

    private void aggiorna_prezzo_totale() {
    	double prezzo = num_coperti * COSTO_COPERTO;
    	for (Ordine ordine : lista_ordini) {
			prezzo += ordine.getPrezzo_parziale();
		}
	}

	private void setPrezzo_totale(double prezzo_totale) {
        this.prezzo_totale = prezzo_totale;
    }

    public void aggingiOrdine(Ordine ordine) {
		lista_ordini.add(ordine);
	}
    public List<Ordine> getLista_ordini() {
		return lista_ordini;
	}
}