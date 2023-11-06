/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Classi;

public class PiattoOrdinato {

	private Piatto piatto;
	private int quantita = 1;
	private double prezzo;
	private String commento;

	public PiattoOrdinato(Piatto piatto) {
		this("", piatto);
	}

	public PiattoOrdinato(String commento, Piatto piatto) {
		super();
		this.commento = commento;
		this.piatto = piatto;
	}

	public int getQuantita() {
		return quantita;
	}

	public double getPrezzo() {
		aggiornaPrezzo();
		return prezzo;
	}

	private void aggiornaPrezzo() {
		prezzo = piatto.getPrezzo() * quantita;
	}

	public String getCommento() {
		return commento;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public Piatto getPiatto() {
		return piatto;
	}

	public void setPiatto(Piatto piatto) {
		this.piatto = piatto;
	}

	public void incrementa_quantità() {
		quantita++;
	}

	public void decrementa_quantità() {
		if (quantita > 1) {
			quantita--;
		} else {
			quantita = 0;
		}
	}

}