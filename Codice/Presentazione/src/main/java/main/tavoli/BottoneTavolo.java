package main.tavoli;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import classi.tavolo.Tavolo;

public class BottoneTavolo extends JButton {

	private static final long serialVersionUID = 1024426268537840715L;

	private Tavolo tavolo;

	public BottoneTavolo(Tavolo tavolo) {
		JLabel lblNome = new JLabel("Tavolo Numero: " + tavolo.getNome());
		JLabel lblPosti = new JLabel("Posti Disponibili: " + tavolo.getNumPostiMassimi());

		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		// lblNome.setVerticalAlignment(SwingConstants.NORTH);

		lblPosti.setHorizontalAlignment(SwingConstants.CENTER);
		// lblPosti.setVerticalAlignment(SwingConstants.SOUTH);

		setLayout(new BorderLayout());

		add(lblNome, BorderLayout.NORTH);
		add(lblPosti, BorderLayout.SOUTH);

		this.tavolo = tavolo;
		aggiornaStato();
	}

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
		this.repaint();
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public boolean equals(Tavolo tavolo) {
		return this.tavolo.equals(tavolo);
	}
}