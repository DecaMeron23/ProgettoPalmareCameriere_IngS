package main.tavoli;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classi.tavolo.Tavolo;

public class PanelBottoniTavoli extends JScrollPane {

	private static final long serialVersionUID = 1L;

	/**
	 * lista dei pulsanti tavoli
	 */
	private List<BottoneTavolo> btnTavolo;

	/**
	 * panel contenente i pulsanti
	 */
	private JPanel panelPulsanti;

	public PanelBottoniTavoli(List<Tavolo> listaTavolo, ActionListener actionListenerTavoli) {

		// creaiamo il panel
		panelPulsanti = new JPanel(new GridLayout(0, 3, 50, 50));

		// creaiamo e aggiungiamo la lista dei bottoni dei tavoli
		btnTavolo = addTavoli(listaTavolo, actionListenerTavoli);

		// impostazioni dello scroll pane
		getVerticalScrollBar().setUnitIncrement(10);
		//getViewport().setPreferredSize(new Dimension(1500, 800));

		// aggiungiamo il panel
		setViewportView(panelPulsanti);
		
	}

	private List<BottoneTavolo> addTavoli(List<Tavolo> listaTavolo, ActionListener actionListenerTavoli) {
		List<BottoneTavolo> bottoniTavolo = new ArrayList<>();

		for (Tavolo tavolo : listaTavolo) {
			// creazione del bottone
			BottoneTavolo btn = new BottoneTavolo(tavolo);
			// aggiunta del action listener
			btn.addActionListener(actionListenerTavoli);
			// modifica dimensioni
			btn.setSize(new Dimension(300, 100));
			// agginta al panel
			panelPulsanti.add(btn);
			// aggiunta alla lista
			bottoniTavolo.add(btn);
		}
		return bottoniTavolo;
	}

	public List<BottoneTavolo> getBtnTavolo() {
		return btnTavolo;
	}

	public JButton getPulstanteTavolo(Tavolo tavolo) {
		for (BottoneTavolo btn : btnTavolo) {
			if (btn.equals(tavolo)) {
				return btn;
			}
		}
		return null;
	}

}
