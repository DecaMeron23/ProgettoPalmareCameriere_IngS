/**
 *  @author Benedetta Vitale & Emilio Meroni
 */
package classi.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe dei componenti (per esempio un componente può essere un "primo")
 */
public class Componente {

	/** nome del componente. */
	private String nome;

	/** Lista dei piatti che contiene il componente */
	private List<Piatto> listaPiatti;

	/**
	 * La precedenza con cui vengono visualizzate le componenti
	 */
	private int precendenza;

	/**
	 * Costruttore
	 *
	 * @param nome         il nome del componente
	 * @param lista_piatti la lista dei piatti contenuti nel componente
	 */
	public Componente(String nome, List<Piatto> lista_piatti, int precedenza) {
		super();
		this.nome = nome;
		this.listaPiatti = lista_piatti;
		this.setPrecendenza(precedenza);
	}

	/**
	 * @param nome il nome del piatto
	 */
	public Componente(String nome) {
		this(nome, new ArrayList<Piatto>(), 2000);
	}

	/**
	 * Prendi il nome del componente
	 *
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Prende la lista dei componenti
	 *
	 * @return la lista dei piatti
	 */
	public List<Piatto> getListaPiatti() {
		return listaPiatti;
	}

	/**
	 * Aggiungi piatto al componente.
	 *
	 * @param piatto il piatto da aggiungere
	 */
	public void aggiungiPiatto(Piatto piatto) { // NO_UCD (unused code)
		listaPiatti.add(piatto);
	}

	/**
	 * QUesto metodo imposta la lista dei piatti
	 * 
	 * @param piatti la lista dei piatti
	 */
	public void setListaPiatti(List<Piatto> piatti) {
		listaPiatti = piatti;
	}

	/**
	 * @param comp2 la componente con cui ci confrontiamo
	 * @return true se hanno lo stesso nome sennò false
	 */
	public boolean equals(Componente comp2) {
		Componente comp1 = this;
		return comp1.getNome().equals(comp2.getNome());
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the precendenza
	 */
	public int getPrecendenza() {
		return precendenza;
	}

	/**
	 * @param precendenza the precendenza to set
	 */
	public void setPrecendenza(int precendenza) {
		this.precendenza = precendenza;
	}
}