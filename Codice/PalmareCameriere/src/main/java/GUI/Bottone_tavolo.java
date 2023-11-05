package GUI;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Classi.Tavolo;

public class Bottone_tavolo extends JButton {

	private Tavolo tavolo;

	public Bottone_tavolo(Tavolo tavolo) {
		JLabel lblNome = new JLabel("Tavolo Numero: " + tavolo.getNome()); 
		JLabel lblPosti = new JLabel("Posti Disponibili: " + tavolo.getNum_posti_massimi()); 
		
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		//lblNome.setVerticalAlignment(SwingConstants.NORTH);
		
		lblPosti.setHorizontalAlignment(SwingConstants.CENTER);
		//lblPosti.setVerticalAlignment(SwingConstants.SOUTH);
		
		setLayout(new BorderLayout());
		
		add(lblNome , BorderLayout.NORTH);
		add(lblPosti , BorderLayout.SOUTH);
		
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
	
	}

	public Tavolo getTavolo() {
		return tavolo;
	}
	
	public boolean equals(Tavolo tavolo) {
		return this.tavolo.equals(tavolo);
	}
}
