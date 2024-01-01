package main.tavoli;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classi.dataBase.DataService;
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

	public PanelBottoniTavoli(ActionListener actionListenerTavoli) {

		// creaiamo il panel
		panelPulsanti = new JPanel(new GridLayout(0, 3, 60, 60));

		// creaiamo e aggiungiamo la lista dei bottoni dei tavoli
		btnTavolo = addTavoli(actionListenerTavoli);

		// impostazioni dello scroll pane
		getVerticalScrollBar().setUnitIncrement(10);

		// aggiungiamo il panel
		setViewportView(panelPulsanti);
		
	}

	private List<BottoneTavolo> addTavoli(ActionListener actionListenerTavoli) {
		List<BottoneTavolo> bottoniTavolo = new ArrayList<>();

		for (Tavolo tavolo : DataService.getTavoli()) {
			// creazione del bottone
			BottoneTavolo btn = new BottoneTavolo(tavolo);
			// aggiunta del action listener
			btn.addActionListener(actionListenerTavoli);
			// modifica dimensioni
			btn.setSize(new Dimension(400, 200));
			 // Aggiungi uno spazio intorno al pulsante
			int valore = 70;
            btn.setBorder(BorderFactory.createEmptyBorder(valore, valore, valore, valore));
			// agginta al panel
			panelPulsanti.add(btn);
			// aggiunta alla lista
			bottoniTavolo.add(btn);
		}
		
		//aggiungo bottoni invisibili se non ce ne sono abbastanza
		if(DataService.getTavoli().size() < 12 ) {
			for (int i = 0; i < 12 - bottoniTavolo.size(); i++) {
				JButton btn = new JButton();
				btn.setSize(new Dimension(400, 200));
				int valore = 70;
	            btn.setBorder(BorderFactory.createEmptyBorder(valore, valore, valore, valore));
				panelPulsanti.add(btn);
				btn.setVisible(false);
				btn.setEnabled(false);
			}
		}
		
		return bottoniTavolo;
	}

	public List<BottoneTavolo> getBtnTavolo() {
		return btnTavolo;
	}

// TODO Remove unused code found by UCDetector
// 	public JButton getPulstanteTavolo(Tavolo tavolo) {
// 		for (BottoneTavolo btn : btnTavolo) {
// 			if (btn.equals(tavolo)) {
// 				return btn;
// 			}
// 		}
// 		return null;
// 	}

}
