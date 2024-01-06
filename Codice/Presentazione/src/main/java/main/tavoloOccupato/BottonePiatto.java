package main.tavoloOccupato;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;

import classi.menu.Piatto;

public class BottonePiatto extends JButton {

	private static final long serialVersionUID = -3693009103129179336L;

	private Piatto piatto;

	public BottonePiatto(Piatto piatto) {
		setLayout(new BorderLayout());
		
		JLabel lblNome = new JLabel(piatto.getNome());
		int size = 23;
		lblNome.setFont(new Font(getFont().getName(), Font.PLAIN, size));
		
		double prezzo = piatto.getPrezzo();
        // Definisci il pattern del formato desiderato
        DecimalFormat formato = new DecimalFormat("#,##0.00");

        // Formatta il numero utilizzando il pattern definito
        String prezzoFormattato = formato.format(prezzo);

        // Stampa il risultato formattato
		JLabel lblPrezzo = new JLabel(prezzoFormattato + " €");
		lblPrezzo.setFont(new Font(getFont().getName(), Font.PLAIN, size));
		add(lblNome , BorderLayout.CENTER);
		add(lblPrezzo , BorderLayout.EAST);
		
		this.piatto = piatto;
	}

	public boolean equals_piatto(Piatto piatto) {
		if (piatto == null) {
			return false;
		}

		return this.piatto.equals(piatto);
	}

	public Piatto getPiatto() {
		return piatto;
	}
}
