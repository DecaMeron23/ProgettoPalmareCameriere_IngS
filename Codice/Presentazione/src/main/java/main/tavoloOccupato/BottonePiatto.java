package main.tavoloOccupato;

import javax.swing.JButton;

import classi.menu.Piatto;

public class BottonePiatto extends JButton {

	private static final long serialVersionUID = -3693009103129179336L;
	
	private Piatto piatto;

	public BottonePiatto(String name, Piatto piatto) {
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
