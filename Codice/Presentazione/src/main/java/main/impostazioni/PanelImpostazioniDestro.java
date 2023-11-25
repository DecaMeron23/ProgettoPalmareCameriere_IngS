package main.impostazioni;

import javax.swing.JPanel;

class PanelImpostazioniDestro extends JPanel {

	private static final long serialVersionUID = 3686468376115049646L;

	// array panel impostazioni
	private JPanel[] panelImpostazioni;

	public PanelImpostazioniDestro() {

		// creazione panel
		JPanel panelTavoli = new PanelImpostazioniTavoli();
		JPanel panelComponenti = new PanelImpostazioniComponenti();
		JPanel panelPiatti = new PanelImpostazioniPiatti();

		// aggiunta panel nell'array
		panelImpostazioni = new JPanel[] { panelTavoli, panelComponenti, panelPiatti};
	}

	/**
	 * 
	 * @param panel: il panel da attivare
	 */
	void seleziona_panel(int panel) {
		removeAll();
		for (int i = 0; i < panelImpostazioni.length; i++) {
			if (i == panel) {
				add(panelImpostazioni[i]);
			}
		}
		repaint();
		revalidate();
	}

}
