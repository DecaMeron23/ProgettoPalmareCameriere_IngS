package main.impostazioni;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class PanelImpostazioni extends JPanel {

	/**
	 * Posizione del divisore
	 */
	private static final int DIVIDER_LOCATION = 300;

	private static final long serialVersionUID = 5685747632097901109L;
		
	/**
	 * Panel Sinistro contenente i bottoni
	 */
	private PanelImpostazioniSinistro panelSx;

	/**
	 * Panel Destro contenente i panel per le impostazioni
	 */
	private PanelImpostazioniDestro panelDx;

	public PanelImpostazioni() {

		setLayout(new BorderLayout());
		// creazione panel sinistro
		panelSx = new PanelImpostazioniSinistro(new ActionListenerPulsanti());
		// creazione panel destro
		panelDx = new PanelImpostazioniDestro();

		// creazione split pane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelSx, panelDx);

		// set posizione del divisore, disabilitazione muovimento dello split
		splitPane.setDividerLocation(DIVIDER_LOCATION);
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
