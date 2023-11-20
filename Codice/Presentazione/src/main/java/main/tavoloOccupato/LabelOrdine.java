package main.tavoloOccupato;

import javax.swing.JLabel;

import classi.ordine.PiattoOrdinato;

public class LabelOrdine extends JLabel {

	private static final long serialVersionUID = 3241149030087949702L;

	public LabelOrdine(PiattoOrdinato piatto_ordinato) {
		setText("<html>"+ piatto_ordinato.getQuantita() + " x " + piatto_ordinato.getPiatto().getNome() + "<br>" + piatto_ordinato.getCommento()+ "</html>");
	}

	
	
}
