package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel_Impostazioni_Sinistro extends JPanel {

	private static final long serialVersionUID = -641162938886101084L;

	// arrai dei bottoni
	final JButton[] btn_impostazioni;

	// identificativi bottoni
	static final int TAVOLI = 0;
	static final int COMPONENTI = 1;
	static final int AGG_PIATTO = 2;
	static final int MOD_PIATTO = 3;

	/**
	 * @param action: action listener dei bottoni
	 */
	public Panel_Impostazioni_Sinistro(ActionListener action) {

		// set layout
		setLayout(new GridLayout(0, 1));

		// creazione dei pulsanti
		JButton btn_tavoli = new JButton("Tavoli");
		JButton btn_componenti = new JButton("Componenti");
		JButton btn_agg_piatto = new JButton("Aggiungi Piatto");
		JButton btn_mod_piatto = new JButton("Modifica Piatto");

		// aggiungiamo i bottoni
		btn_impostazioni = new JButton[] { btn_tavoli, btn_componenti, btn_agg_piatto, btn_mod_piatto };

		// settaggio identificativi bottoni
		btn_tavoli.setName("tavoli");
		btn_componenti.setName("componenti");
		btn_agg_piatto.setName("agg_piatto");
		btn_mod_piatto.setName("mod_piatto");

		// aggiunta action listener e aggiunta al panel
		for (JButton btn : btn_impostazioni) {
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
	public void repaint_panel(JButton button) {
		for (JButton btn : btn_impostazioni) {
			if (btn.equals(button)) {
				btn.setBackground(Color.cyan);
			} else {
				btn.setBackground(null);
			}
		}
	}

	public int get_tipo_btn(JButton button) {
		for (int i = 0; i < btn_impostazioni.length; i++) {
			if(btn_impostazioni[i].equals(button)) {
				return i;
			}
		}
		return -1;
	}

}
