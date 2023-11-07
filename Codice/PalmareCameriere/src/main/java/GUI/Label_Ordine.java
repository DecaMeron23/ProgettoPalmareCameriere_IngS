package GUI;

import javax.swing.JLabel;

import Classi.PiattoOrdinato;

public class Label_Ordine extends JLabel {

	private static final long serialVersionUID = 3241149030087949702L;

	public Label_Ordine(PiattoOrdinato piatto_ordinato) {
		setText(piatto_ordinato.getQuantita() + " x " + piatto_ordinato.getPiatto().getNome() + "\n" + piatto_ordinato.getCommento());
	}

	
	
}
