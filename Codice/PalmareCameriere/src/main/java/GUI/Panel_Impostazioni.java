package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Panel_Impostazioni extends JPanel {

	private static final long serialVersionUID = 5685747632097901109L;

	/**
	 * Panel Sinistro contenente i bottoni
	 */
	Panel_Impostazioni_Sinistro panel_sx;

	/**
	 * Panel Destro contenente i panel per le impostazioni
	 */
	Panel_Impostazioni_Destro panel_dx;

	public Panel_Impostazioni() {

		setLayout(new BorderLayout());
		// creazione panel sinistro
		panel_sx = new Panel_Impostazioni_Sinistro(new Action_Listener_Pulsanti());
		// creazione panel destro
		panel_dx = new Panel_Impostazioni_Destro();

		// creazione split pane
		JSplitPane split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_sx, panel_dx);

		// set posizione del divisore, disabilitazione muovimento dello split
		split_pane.setDividerLocation(500);
		split_pane.setEnabled(false);
		split_pane.setDividerSize(1);

		// aggiunta dello split al panel principale
		add(split_pane , BorderLayout.CENTER);

	}

	/**
	 * Action listener per i bottoni
	 */
	private class Action_Listener_Pulsanti implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			panel_sx.repaint_panel(btn);

			int tipo = panel_sx.get_tipo_btn(btn);
			panel_dx.seleziona_panel(tipo);
		}
	}

}
