package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import Classi.Tavolo;

public class PanelTavoli extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<Bottone_tavolo> btn_tavolo;

	public PanelTavoli(List<Tavolo> lista_tavolo, ActionListener actionListener_tavoli) {
		// setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(0, 3, 50, 50));
		// setVisible(true);
		btn_tavolo = addTavoli(lista_tavolo, actionListener_tavoli);

	}

	private List<Bottone_tavolo> addTavoli(List<Tavolo> lista_tavolo, ActionListener actionListener_tavoli) {
		List<Bottone_tavolo> bottoni_tavolo = new ArrayList();

		for (Tavolo tavolo : lista_tavolo) {
			// nome del tavolo
			String nome_tavolo = tavolo.getNome() + "";
			// creazione del bottone
			Bottone_tavolo btn = new Bottone_tavolo(tavolo);
			// aggiunta del action listener
			btn.addActionListener(actionListener_tavoli);
			// modifica dimensioni
			btn.setPreferredSize(new Dimension(300, 100));
			// agginta al panel
			add(btn);
			// aggiunta alla lista
			bottoni_tavolo.add(btn);
		}
		return bottoni_tavolo;
	}

	public List<Bottone_tavolo>  getBtn_tavolo() {
		return btn_tavolo;
	}
	
	public JButton getPulstanteTavolo(Tavolo tavolo){
		for (Bottone_tavolo btn : btn_tavolo) {
			if(btn.equals(tavolo)) {
				return btn;
			}
		}
		return null;
	}
	
}
