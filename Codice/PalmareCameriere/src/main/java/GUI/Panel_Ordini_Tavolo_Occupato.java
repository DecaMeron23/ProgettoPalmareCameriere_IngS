package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classi.Ordine;
import Classi.PiattoOrdinato;

class Panel_Ordini_Tavolo_Occupato extends JPanel {

	private static final long serialVersionUID = -2916448870874586511L;

	private JPanel panel_sopra;
	private JPanel panel_sotto;
	private JButton btn_invia;
	private JButton btn_pagato;
	private JLabel lbl_totale;
	private PanelTavoloOccupato panel_tavolo_occupato;
	
	public Panel_Ordini_Tavolo_Occupato(PanelTavoloOccupato panel_tavolo_occupato) {
		this.panel_tavolo_occupato = panel_tavolo_occupato;

		panel_sopra = new JPanel();
		panel_sotto = new JPanel();
		
		btn_invia = new Bottone_Invia();
		btn_pagato = new Bottone_Pagato();

		lbl_totale = new JLabel("Importo Totale: " + panel_tavolo_occupato.resoconto_tavolo.getPrezzo_totale() + " €");

		panel_sopra.setLayout(new BoxLayout(panel_sopra, BoxLayout.Y_AXIS));

		panel_sotto.setLayout(new GridLayout(0, 3));
		panel_sotto.add(btn_pagato, BorderLayout.WEST);
		panel_sotto.add(btn_invia, BorderLayout.EAST);

		// mettere prezzo sopra pulsanti
		panel_sotto.add(lbl_totale, BorderLayout.CENTER);

		
		
		setLayout(new BorderLayout());
		add(panel_sopra, BorderLayout.CENTER);
		add(panel_sotto, BorderLayout.SOUTH);
	}

	public void repaint_panel() {
		panel_sopra.removeAll();
		panel_sopra.revalidate();
		panel_sopra.repaint();
		
		//aggiorniamo la lista degli elementi dell'ordine attuale
		for (int i= panel_tavolo_occupato.lista_piatti_ordinati.size() - 1  ; i >= 0 ; i--) {
			Piatto_Ordinato_Piu_Meno_Text element = new Piatto_Ordinato_Piu_Meno_Text(panel_tavolo_occupato.lista_piatti_ordinati.get(i));
			panel_sopra.add(element);
		}
		// aggiornaimo i pulsanti
		if (!panel_tavolo_occupato.lista_piatti_ordinati.isEmpty()) {
			btn_invia.setEnabled(true);
		}
		if (!panel_tavolo_occupato.resoconto_tavolo.getLista_ordini().isEmpty()) {
			btn_pagato.setEnabled(true);
		}
		// aggiorniamo il label per il prezzo
		aggiorna_prezzo();
		// aggiorniamo lo storico ordini
		aggiorna_ordini_effettuati();
	}

	private void aggiorna_ordini_effettuati() {
		for (Ordine ordine : panel_tavolo_occupato.resoconto_tavolo.getLista_ordini()) {
			for (PiattoOrdinato piatto_ordinato : ordine.getLista_piatti_ordinati()) {
				JLabel lbl_piatto_ordinato = new Label_Ordine(piatto_ordinato);
				panel_sopra.add(lbl_piatto_ordinato);
			}
		}
		
	}

	private void aggiorna_prezzo() {
		lbl_totale.setText("Importo Totale: " + panel_tavolo_occupato.resoconto_tavolo.getPrezzo_totale() + " €");
	}

	class Bottone_Pagato extends JButton {

		private static final long serialVersionUID = -1400046920322830699L;

		public Bottone_Pagato() {
			super("Pagato");
			boolean btn_attivare = !panel_tavolo_occupato.resoconto_tavolo.getLista_ordini().isEmpty();
			setEnabled(btn_attivare);
			addActionListener(new ActionListener_btn_paga());
		}

		class ActionListener_btn_paga implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		}
	}

	class Bottone_Invia extends JButton {

		private static final long serialVersionUID = 5053523527111913425L;

		public Bottone_Invia() {
			super("Invia");
			setEnabled(false);
			addActionListener(new ActionListener_btn_invia());
		}

		class ActionListener_btn_invia implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				Ordine ordine = new Ordine(panel_tavolo_occupato.lista_piatti_ordinati);
				panel_tavolo_occupato.resoconto_tavolo.aggingiOrdine(ordine);
				panel_tavolo_occupato.lista_piatti_ordinati = new ArrayList<PiattoOrdinato>();
				repaint_panel();
			}

		}

	}

}
