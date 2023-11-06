package GUI;

import javax.swing.JButton;

import Classi.Piatto;

class Bottone_Piatto extends JButton {

	private Piatto piatto;

	public Bottone_Piatto(String name, Piatto piatto) {
		super(name);
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