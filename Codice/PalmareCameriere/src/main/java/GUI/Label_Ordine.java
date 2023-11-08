package GUI;

import javax.swing.JLabel;

import Classi.PiattoOrdinato;

public class Label_Ordine extends JLabel {

	private static final long serialVersionUID = 3241149030087949702L;

	public Label_Ordine(PiattoOrdinato piatto_ordinato) {
		setText("<html>"+ piatto_ordinato.getQuantita() + " x " + piatto_ordinato.getPiatto().getNome() + "<br>" + piatto_ordinato.getCommento()+ "</html>");
	}

	
	
}
