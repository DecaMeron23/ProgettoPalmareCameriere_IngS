package main.impostazioni;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelImpostazioniSinistro extends JPanel {

	private static final long serialVersionUID = -641162938886101084L;

	// arrai dei bottoni
	final JButton[] btnImpostazioni;

	/**
	 * @param action: action listener dei bottoni
	 */
	public PanelImpostazioniSinistro(ActionListener action) {

		// set layout
		setLayout(new GridLayout(0, 1));

		// creazione dei pulsanti
		JButton btnTavoli = new JButton("Tavoli");
		JButton btnComponenti = new JButton("Componenti");
		JButton btnAggPiatto = new JButton("Aggiungi Piatto");
		JButton btnModPiatto = new JButton("Modifica Piatto");

		// aggiungiamo i bottoni
		btnImpostazioni = new JButton[] { btnTavoli, btnComponenti, btnAggPiatto, btnModPiatto };

		// settaggio identificativi bottoni
		btnTavoli.setName("tavoli");
		btnComponenti.setName("componenti");
		btnAggPiatto.setName("agg_piatto");
		btnModPiatto.setName("mod_piatto");

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
	public void repaintPanel(JButton button) {
		for (JButton btn : btnImpostazioni) {
			if (btn.equals(button)) {
				btn.setBackground(Color.cyan);
			} else {
				btn.setBackground(null);
			}
		}
	}

	public int getTipoBtn(JButton button) {
		for (int i = 0; i < btnImpostazioni.length; i++) {
			if(btnImpostazioni[i].equals(button)) {
				return i;
			}
		}
		return -1;
	}

}
