package main.impostazioni;

import javax.swing.JPanel;

/**
 * Panel che si occupa della parte destra delle impostazioni
 */
class PanelImpostazioniDestro extends JPanel {

	private static final long serialVersionUID = 3686468376115049646L;

	/**
	 * Il valore che identifica il panel del tavolo
	 */
	private static final int TAVOLO = 0;
	/**
	 * Il valore che identifica il panel della componente
	 */
	private static final int COMPONENTE = 1;
	/**
	 * Il valore che identifica il panel del piatto
	 */
	private static final int PIATTO = 2;

	/**
	 * Questo metodo si occupa di creare il panel richesto
	 * 
	 * @param panel il valore intero del panel da inserire
	 */
	void seleziona_panel(int panel) {
		removeAll();
		if (panel == TAVOLO) {
			add(new PanelImpostazioniTavoli());
		} else if (panel == COMPONENTE) {
			add(new PanelImpostazioniComponenti());
		} else if (panel == PIATTO) {
			add(new PanelImpostazioniPiatti());
		}
		repaint();
		revalidate();
	}

}
