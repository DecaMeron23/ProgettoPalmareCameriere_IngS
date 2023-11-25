package main.impostazioni;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class PanelImpostazioniSinistro extends JPanel {

	private static final long serialVersionUID = -641162938886101084L;

	// array dei bottoni
	private final JButton[] btnImpostazioni;

	/**
	 * @param action: action listener dei bottoni
	 */
	PanelImpostazioniSinistro(ActionListener action) {

		// set layout
		setLayout(new GridLayout(0, 1));

		// creazione dei pulsanti
		JButton btnTavoli = new JButton("Tavoli");
		JButton btnComponenti = new JButton("Componenti");
		JButton btnPiatti = new JButton("Piatti");

		// aggiungiamo i bottoni
		btnImpostazioni = new JButton[] { btnTavoli, btnComponenti , btnPiatti};

		// settaggio identificativi bottoni
		btnTavoli.setName("tavoli");
		btnComponenti.setName("componenti");
		btnPiatti.setName("piatto");

		// aggiunta action listener e aggiunta al panel
		for (JButton btn : btnImpostazioni) {
			btn.addActionListener(action);
			add(btn);
		}

	}

	/**
	 * funzione che setta il colore standard dei pulsanti eccetto quello passato
	 * come parametro
	 * 
	 * @param button: il bottone che verrà colorato
	 */
	void repaintPanel(JButton button) {
		for (JButton btn : btnImpostazioni) {
			if (btn.equals(button)) {
				btn.setBackground(Color.cyan);
			} else {
				btn.setBackground(null);
			}
		}
	}

	/**
	 * 	Funzione che idenditifica il bottone
	 * 
	 * @param button: Bottone da identificare
	 * @return i intero che identifica il bottone
	 */
	int getTipoBtn(JButton button) {
		for (int i = 0; i < btnImpostazioni.length; i++) {
			if(btnImpostazioni[i].equals(button)) {
				return i;
			}
		}
		return -1;
	}

}
