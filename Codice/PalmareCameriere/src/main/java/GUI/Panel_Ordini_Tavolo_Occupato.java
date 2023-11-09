package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import Classi.Ordine;
import Classi.PiattoOrdinato;
import Classi.ResocontoTavolo;

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

		panel_sopra.add(panel_sopra_ordini, BorderLayout.CENTER);
		panel_sopra.add(btn_invia, BorderLayout.SOUTH);

		JScrollPane scroll_sopra = new JScrollPane(panel_sopra);
		JScrollPane scroll_sotto = new JScrollPane(panel_sotto);

		lbl_totale = new JLabel("Importo Totale: "
				+ formatta_prezzo(panel_tavolo_occupato.tavolo.resoconto_tavolo.getPrezzo_totale()) + " €");

		scroll_sopra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_sotto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		scroll_sopra.getVerticalScrollBar().setUnitIncrement(10);
		scroll_sotto.getVerticalScrollBar().setUnitIncrement(10);

		scroll_sopra.getViewport().setPreferredSize(new Dimension(50, 50));
		scroll_sotto.getViewport().setPreferredSize(new Dimension(50, 50));

		panel_pulsanti_sotto.setLayout(new GridLayout(0, 3));
		panel_pulsanti_sotto.add(btn_pagato);
		panel_pulsanti_sotto.add(lbl_totale);

		panel_sotto.add(panel_pulsanti_sotto, BorderLayout.SOUTH);

		/*
		 * JSplitPane divisore_orizzontale = new JSplitPane(JSplitPane.VERTICAL_SPLIT ,
		 * scroll_sopra, scroll_sotto); divisore_orizzontale.setDividerLocation(400);
		 * divisore_orizzontale.setEnabled(false);
		 */

		setLayout(new GridLayout(0, 1));
		add(scroll_sopra);
		add(scroll_sotto);
	}

	private String formatta_prezzo(double numero) {
		DecimalFormat formato = new DecimalFormat("0.00");
		String numeroFormattato = formato.format(numero);
		return numeroFormattato;
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
		if (!panel_tavolo_occupato.tavolo.resoconto_tavolo.getLista_ordini().isEmpty()) {
			btn_pagato.setEnabled(true);
		}
		// aggiorniamo il label per il prezzo
		aggiorna_prezzo();
		// aggiorniamo lo storico ordini
		aggiorna_ordini_effettuati();
	}

	private void aggiorna_ordini_effettuati() {
		DefaultListModel<String> model_piatto = new DefaultListModel<>();
		DefaultListModel<String> model_prezzo = new DefaultListModel<>();

		// aggiunta coperti
		int num_coperti = panel_tavolo_occupato.tavolo.resoconto_tavolo.getNum_coperti();
		model_piatto.addElement(num_coperti + "x Coperto");
		model_prezzo.addElement(formatta_prezzo(num_coperti * ResocontoTavolo.COSTO_COPERTO) + " €   ");
		for (Ordine ordine : panel_tavolo_occupato.tavolo.resoconto_tavolo.getLista_ordini()) {
			for (PiattoOrdinato piatto_ordinato : ordine.getLista_piatti_ordinati()) {
				/*
				 * JLabel lbl_piatto_ordinato = new Label_Ordine(piatto_ordinato);
				 * panel_sopra.add(lbl_piatto_ordinato);
				 */
				String str_nome = piatto_ordinato.getQuantita() + "x " + piatto_ordinato.getPiatto().getNome();
				String str_commento = piatto_ordinato.getCommento();
				DecimalFormat formato = new DecimalFormat("0.00");
				String numeroFormattato = formato.format(piatto_ordinato.getPrezzo());
				String str_prezzo = numeroFormattato + " €   ";
				model_piatto.addElement(str_nome);
				model_prezzo.addElement(str_prezzo);
				if (!str_commento.isBlank()) {
					model_piatto.addElement(" > " + str_commento);
					model_prezzo.addElement("\t");
				}
			}
		}

		JList<String> lista_prezzi = new JList<>(model_prezzo);
		JList<String> lista_ordini_effettuati = new JList<>(model_piatto);
		lista_prezzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista_ordini_effettuati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista_ordini_effettuati.setEnabled(false);
		lista_prezzi.setEnabled(false);
		panel_sotto.add(lista_ordini_effettuati, BorderLayout.CENTER);
		panel_sotto.add(lista_prezzi, BorderLayout.EAST);
	}

	private void aggiorna_prezzo() {
		lbl_totale.setText("Importo Totale: "
				+ formatta_prezzo(panel_tavolo_occupato.tavolo.resoconto_tavolo.getPrezzo_totale()) + " €");
	}

	class Bottone_Pagato extends JButton {

		private static final long serialVersionUID = -1400046920322830699L;

		public Bottone_Pagato() {
			super("Pagato");
			boolean btn_attivare = !panel_tavolo_occupato.tavolo.resoconto_tavolo.getLista_ordini().isEmpty();
			setEnabled(btn_attivare);
			addActionListener(new ActionListener_btn_paga());
		}

		class ActionListener_btn_paga implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				// creaiamo gli action listener necessari
				ActionListener listener_si = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
					}
				};

				ActionListener listener_no = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
					}
				};

				// apriamo il frame che chiede la conferma
				JFrame conferma = new Frame_conferma_uscita("ciaooo", listener_si, listener_no);

				conferma.addWindowListener(new WindowAdapter() {
				
					@Override
					public void windowClosed(WindowEvent e) {
						super.windowClosed(e);
						
					}
				
				});
				
				panel_tavolo_occupato.tavolo.pagato();
				// repaint_panel();
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
				panel_tavolo_occupato.tavolo.resoconto_tavolo.aggingiOrdine(ordine);
				panel_tavolo_occupato.lista_piatti_ordinati = new ArrayList<PiattoOrdinato>();
				repaint_panel();
			}

		}

	}

}
