package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import Classi.Ordine;
import Classi.PiattoOrdinato;

class Panel_Ordini_Tavolo_Occupato extends JPanel {

	private static final long serialVersionUID = -2916448870874586511L;

	private JPanel panel_sopra_ordini;
	private JPanel panel_pulsanti_sotto;
	private JPanel panel_sotto;
	private JButton btn_invia;
	private JButton btn_pagato;
	private JLabel lbl_totale;
	private PanelTavoloOccupato panel_tavolo_occupato;

	public Panel_Ordini_Tavolo_Occupato(PanelTavoloOccupato panel_tavolo_occupato) {
		
		this.panel_tavolo_occupato = panel_tavolo_occupato;

		JPanel panel_sopra = new JPanel(new BorderLayout());
		panel_sopra_ordini = new JPanel();
		panel_sotto = new JPanel(new BorderLayout());
			
		panel_sopra_ordini.setLayout(new BoxLayout(panel_sopra_ordini, BoxLayout.Y_AXIS));
		
		panel_pulsanti_sotto = new JPanel();
		
		btn_invia = new Bottone_Invia();
		btn_pagato = new Bottone_Pagato();

		panel_sopra.add(panel_sopra_ordini , BorderLayout.CENTER);
		panel_sopra.add(btn_invia , BorderLayout.SOUTH);
		
		JScrollPane scroll_sopra = new JScrollPane(panel_sopra);
		JScrollPane scroll_sotto = new JScrollPane(panel_sotto);

		lbl_totale = new JLabel("Importo Totale: " + panel_tavolo_occupato.resoconto_tavolo.getPrezzo_totale() + " €");

		scroll_sopra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_sotto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		
		scroll_sopra.getVerticalScrollBar().setUnitIncrement(10);
		scroll_sotto.getVerticalScrollBar().setUnitIncrement(10);
		
		
		scroll_sopra.getViewport().setPreferredSize(new Dimension(50, 50));
		scroll_sotto.getViewport().setPreferredSize(new Dimension(50, 50));
		

		panel_pulsanti_sotto.setLayout(new GridLayout(0, 3));
		panel_pulsanti_sotto.add(btn_pagato);
		panel_pulsanti_sotto.add(lbl_totale);

		panel_sotto.add(panel_pulsanti_sotto , BorderLayout.SOUTH);
		
		/*
		JSplitPane divisore_orizzontale = new JSplitPane(JSplitPane.VERTICAL_SPLIT , scroll_sopra, scroll_sotto);
		divisore_orizzontale.setDividerLocation(400);
		divisore_orizzontale.setEnabled(false);
		*/
		
		setLayout(new GridLayout( 0 , 1));
		add(scroll_sopra);
		add(scroll_sotto);
	}

	public void repaint_panel() {
		panel_sopra_ordini.removeAll();
		panel_sopra_ordini.revalidate();
		panel_sopra_ordini.repaint();

		// aggiorniamo la lista degli elementi dell'ordine attuale
		for (int i = panel_tavolo_occupato.lista_piatti_ordinati.size() - 1; i >= 0; i--) {
			Piatto_Ordinato_Piu_Meno_Text element = new Piatto_Ordinato_Piu_Meno_Text(
					panel_tavolo_occupato.lista_piatti_ordinati.get(i));
			panel_sopra_ordini.add(element);
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
		DefaultListModel<String> model = new DefaultListModel<>();
		for (Ordine ordine : panel_tavolo_occupato.resoconto_tavolo.getLista_ordini()) {
			for (PiattoOrdinato piatto_ordinato : ordine.getLista_piatti_ordinati()) {
				/*
				 * JLabel lbl_piatto_ordinato = new Label_Ordine(piatto_ordinato);
				 * panel_sopra.add(lbl_piatto_ordinato);
				 */
				String str = piatto_ordinato.getQuantita() + " x " + piatto_ordinato.getPiatto().getNome() + "\n"
						+ piatto_ordinato.getCommento();
				model.addElement(str);
			}
		}
		JList<String> lista_ordini_effettuati = new JList<>(model);
		lista_ordini_effettuati.setEnabled(true);
		panel_sotto.add(lista_ordini_effettuati);
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
