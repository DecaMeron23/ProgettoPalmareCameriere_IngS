/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Classi;

import java.util.ArrayList;
import java.util.List;

public class Ordine {

	private List<PiattoOrdinato> lista_piatti_ordinati;
	private double prezzo_parziale;

	public Ordine(List<PiattoOrdinato> lista_piatti_ordinati) {
		this.lista_piatti_ordinati = lista_piatti_ordinati;
		elimina_occorrenze_zero();
		aggiorna_prezzo_parziale();
	}
	

	private void elimina_occorrenze_zero() {
		List<PiattoOrdinato> lista_da_eliminare = new ArrayList<>();
		for (PiattoOrdinato piattoOrdinato : lista_piatti_ordinati) {
			if(piattoOrdinato.getQuantita() == 0) {
				lista_da_eliminare.add(piattoOrdinato);
			}
		}
		lista_piatti_ordinati.removeAll(lista_da_eliminare);
	}


	public double getPrezzo_parziale() {
		aggiorna_prezzo_parziale();
		return prezzo_parziale;
	}

	private void aggiorna_prezzo_parziale() {
		double prezzo = 0;
		for (PiattoOrdinato piatto_ordinato : lista_piatti_ordinati) {
			prezzo += prezzo + piatto_ordinato.getPrezzo() * piatto_ordinato.getQuantita();
		}
		prezzo_parziale = prezzo;
	}
	
	public List<PiattoOrdinato> getLista_piatti_ordinati() {
		return lista_piatti_ordinati;
	}
}