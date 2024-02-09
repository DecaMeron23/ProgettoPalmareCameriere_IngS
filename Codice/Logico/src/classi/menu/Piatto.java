/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package classi.menu;

/**
 * The Class Piatto.
 */
public class Piatto {

	/** Il nome del piatto. */
	private String nome;
	
	/** il prezzo del piatto. */
	private double prezzo;

	/**
	 * costruttore del piatto.
	 *
	 * @param nome il nome del piatto
	 * @param prezzo il prezzo del piatto
	 */
	public Piatto(String nome, double prezzo) {
		this.nome = nome;
		this.prezzo = prezzo;
	}

	/**
	 * prendi il nome del piatto.
	 *
	 * @return il nome del piatto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * prendi il prezzo del piatto.
	 *
	 * @return il prezzo del piatto
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * imposta il nome del piatto.
	 *
	 * @param nome il nome del piatto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * imposta il prezzo del piatto.
	 *
	 * @param prezzo il nuovo prezzo
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * metodo equals per piatto
	 *
	 * @param piatto il piatto con cui fare il confronto
	 * @return true, se il piatto ha lo stesso nome e prezzo
	 */
	public boolean equals(Piatto piatto) {
		if(piatto == null) {
			return false;
		}
		return this.nome.equals(piatto.nome) && this.prezzo == piatto.prezzo;

	}
}