/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Classi;

import java.util.List;

public class Componente {

	private String nome;
	private List<Piatto> lista_piatti;

	public Componente(String nome , List<Piatto> lista_piatti) {
		super();
		this.nome = nome;
		this.lista_piatti = lista_piatti;
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Piatto> getLista_piatti() {
		return lista_piatti;
	}
	
	public void aggiungiPiatto(Piatto piatto){
		lista_piatti.add(piatto);
	}
}