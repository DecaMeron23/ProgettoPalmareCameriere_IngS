package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Classi.Tavolo;

public class Panel_Bottoni_Tavoli extends JScrollPane {

	private static final long serialVersionUID = 1L;

	/**
	 * lista dei pulsanti tavoli
	 */
	private List<Bottone_tavolo> btn_tavolo;

	/**
	 * panel contenente i pulsanti
	 */
	private JPanel panel_pulsanti;

	public Panel_Bottoni_Tavoli(List<Tavolo> lista_tavolo, ActionListener actionListener_tavoli) {

		// creaiamo il panel
		panel_pulsanti = new JPanel(new GridLayout(0, 3, 50, 50));

		// creaiamo e aggiungiamo la lista dei bottoni dei tavoli
		btn_tavolo = addTavoli(lista_tavolo, actionListener_tavoli);

		// impostazioni dello scroll pane
		getVerticalScrollBar().setUnitIncrement(10);
		//getViewport().setPreferredSize(new Dimension(1500, 800));

		// aggiungiamo il panel
		setViewportView(panel_pulsanti);
		
	}

	private List<Bottone_tavolo> addTavoli(List<Tavolo> lista_tavolo, ActionListener actionListener_tavoli) {
		List<Bottone_tavolo> bottoni_tavolo = new ArrayList<>();

		for (Tavolo tavolo : lista_tavolo) {
			// creazione del bottone
			Bottone_tavolo btn = new Bottone_tavolo(tavolo);
			// aggiunta del action listener
			btn.addActionListener(actionListener_tavoli);
			// modifica dimensioni
			btn.setSize(new Dimension(300, 100));
			// agginta al panel
			panel_pulsanti.add(btn);
			// aggiunta alla lista
			bottoni_tavolo.add(btn);
		}
		return bottoni_tavolo;
	}

	public List<Bottone_tavolo> getBtn_tavolo() {
		return btn_tavolo;
	}

	public JButton getPulstanteTavolo(Tavolo tavolo) {
		for (Bottone_tavolo btn : btn_tavolo) {
			if (btn.equals(tavolo)) {
				return btn;
			}
		}
		return null;
	}

}
