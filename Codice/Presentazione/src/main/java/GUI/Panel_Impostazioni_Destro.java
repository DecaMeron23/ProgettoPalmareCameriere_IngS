package GUI;

import javax.swing.JPanel;

public class Panel_Impostazioni_Destro extends JPanel {

	private static final long serialVersionUID = 3686468376115049646L;

	// array panel impostazioni
	private JPanel[] panel_impostazioni;

	// identificativi bottoni
	static final int TAVOLI = 0;
	static final int COMPONENTI = 1;
	static final int AGG_PIATTO = 2;
	static final int MOD_PIATTO = 3;

	public Panel_Impostazioni_Destro() {

		// creazione panel
		JPanel panel_tavoli = new Panel_Impostazioni_Tavoli();
		JPanel panel_componenti = new Panel_Impostazioni_Componenti();
		JPanel panel_agg_piatto = new Panel_Impostazioni_Aggiungi();
		JPanel panel_mod_piatto = new Panel_Impostazioni_Modifica();

		// aggiunta panel nell'array
		panel_impostazioni = new JPanel[] { panel_tavoli, panel_componenti, panel_agg_piatto, panel_mod_piatto };
	}

	/**
	 * 
	 * @param panel: il panel da attivare
	 */
	public void seleziona_panel(int panel) {
		removeAll();
		for (int i = 0; i < panel_impostazioni.length; i++) {
			if (i == panel) {
				add(panel_impostazioni[i]);
			}
		}
		repaint();
		revalidate();
	}

}
