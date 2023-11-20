package main.impostazioni;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class PanelImpostazioni extends JPanel {

	private static final long serialVersionUID = 5685747632097901109L;

	static final int TAVOLI = 0;
	static final int COMPONENTI = 1;
	static final int AGG_PIATTO = 2;
	static final int MOD_PIATTO = 3;

		
	/**
	 * Panel Sinistro contenente i bottoni
	 */
	PanelImpostazioniSinistro panelSx;

	/**
	 * Panel Destro contenente i panel per le impostazioni
	 */
	PanelImpostazioniDestro panelDx;

	public PanelImpostazioni() {

		setLayout(new BorderLayout());
		// creazione panel sinistro
		panelSx = new PanelImpostazioniSinistro(new ActionListenerPulsanti());
		// creazione panel destro
		panelDx = new PanelImpostazioniDestro();

		// creazione split pane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelSx, panelDx);

		// set posizione del divisore, disabilitazione muovimento dello split
		splitPane.setDividerLocation(500);
		splitPane.setEnabled(false);
		splitPane.setDividerSize(1);

		// aggiunta dello split al panel principale
		add(splitPane , BorderLayout.CENTER);

	}

	/**
	 * Action listener per i bottoni
	 */
	private class ActionListenerPulsanti implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			panelSx.repaintPanel(btn);

			int tipo = panelSx.getTipoBtn(btn);
			panelDx.seleziona_panel(tipo);
		}
	}

}
