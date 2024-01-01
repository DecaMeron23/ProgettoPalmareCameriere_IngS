package main.tavoli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import classi.tavolo.Tavolo;

/**
 * Classe JButton serve per i pulsanti dei tavoli nella schermata principale
 */
public class BottoneTavolo extends JButton {

	private static final long serialVersionUID = 1024426268537840715L;

	/**
	 * Il tavolo a cui fa riferimento il pulsante
	 */
	private Tavolo tavolo;

	public BottoneTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;

		// creo i label
		JLabel lblNome = new JLabel("Tavolo Numero: " + tavolo.getNome());
		JLabel lblPosti = new JLabel("Posti Disponibili: " + tavolo.getNumPostiMassimi());
		
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblPosti.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosti.setHorizontalAlignment(SwingConstants.CENTER);

		setLayout(new BorderLayout());

		add(lblNome, BorderLayout.NORTH);
		add(lblPosti, BorderLayout.SOUTH);

		aggiornaStato();
	}

	/**
	 * Metodo che aggiorna lo stato in base al tavolo proprio tavolo
	 */
	public void aggiornaStato() {
		switch (tavolo.getStato()) {
		case LIBERO:
			this.setBackground(Color.green);
			break;

		case OCCUPATO:
			this.setBackground(Color.red);
			break;

		case DA_PULIRE:
			this.setBackground(Color.orange);
			break;

		default:
			break;
		}
	}

	/**
	 * @return il tavolo
	 */
	public Tavolo getTavolo() {
		return tavolo;
	}

	/**
	 * @param tavolo con cui ci confrontiamo
	 * @return	true se il tavolo del pulsanti è uguale a tavolo dato
	 */
	public boolean equals(Tavolo tavolo) {
		return this.tavolo.equals(tavolo);
	}
}
