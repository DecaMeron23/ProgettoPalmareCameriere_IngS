package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import Classi.Ordine;
import Classi.PiattoOrdinato;
import Classi.ResocontoTavolo;

/**
 * Panel della riguardante la parte SX del panel tavolo occupato
 */
class Panel_SX_Tavolo_Occupato extends JPanel {

	private static final long serialVersionUID = -2916448870874586511L;

	/**
	 * panel contenente gli ordini attuali
	 */
	private JPanel panel_sopra_ordini;

	/**
	 * label contenente il pulsante pagato e il label totale
	 */
	private JPanel panel_pulsante_label_sotto;

	/**
	 * Panel che contiene gli storici, il bottone pagato e il label del totale
	 */
	private JPanel panel_sotto;

	/**
	 * Bottone per inviare l'ordine
	 */
	private JButton btn_invia;

	/**
	 * Bottone per indicare se il cliente ha pagato
	 */
	private JButton btn_pagato;

	/**
	 * Label per il testo del prezzo totale
	 */
	private JLabel lbl_totale;

	/**
	 * Panel superiore
	 */
	private Panel_Tavolo_Occupato panel_tavolo_occupato;

	/**
	 * @param panel_tavolo_occupato: panel principale
	 */
	public Panel_SX_Tavolo_Occupato(Panel_Tavolo_Occupato panel_tavolo_occupato) {

		this.panel_tavolo_occupato = panel_tavolo_occupato;

		JPanel panel_sopra = new JPanel(new BorderLayout());
		panel_sopra_ordini = new JPanel();
		panel_sotto = new JPanel(new BorderLayout());

		panel_sopra_ordini.setLayout(new BoxLayout(panel_sopra_ordini, BoxLayout.Y_AXIS));

		panel_pulsante_label_sotto = new JPanel();

		btn_invia = new Bottone_Invia();
		btn_pagato = new Bottone_Pagato();

		lbl_totale = new JLabel();

		JScrollPane scroll_sopra = new JScrollPane(panel_sopra);
		JScrollPane scroll_sotto = new JScrollPane(panel_sotto);

		panel_sopra.add(panel_sopra_ordini, BorderLayout.CENTER);
		panel_sopra.add(btn_invia, BorderLayout.SOUTH);

		scroll_sopra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_sotto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		scroll_sopra.getVerticalScrollBar().setUnitIncrement(10);
		scroll_sotto.getVerticalScrollBar().setUnitIncrement(10);

		scroll_sopra.getViewport().setPreferredSize(new Dimension(50, 50));
		scroll_sotto.getViewport().setPreferredSize(new Dimension(50, 50));

		panel_pulsante_label_sotto.setLayout(new GridLayout(0, 3));
		panel_pulsante_label_sotto.add(btn_pagato);
		panel_pulsante_label_sotto.add(lbl_totale);

		panel_sotto.add(panel_pulsante_label_sotto, BorderLayout.SOUTH);

		setLayout(new GridLayout(0, 1));
		add(scroll_sopra);
		add(scroll_sotto);

		// aggiorniamo il prezzo totale
		aggiorna_prezzo_totale();
	}

	/**
	 * formatta un numero double in una stringa con due cifre decimali dopo
	 * 
	 * @param numero: valore da formattare
	 * @return una stringa con il valore del numero passato come parametro con due
	 *         cifre decimali
	 */
	private String formatta_prezzo(double numero) {
		DecimalFormat formato = new DecimalFormat("0.00");
		String numeroFormattato = formato.format(numero);
		return numeroFormattato;
	}

	/**
	 * funzione che aggionra il panel
	 */
	public void repaint_panel() {
		// aggiorniamo i due panel
		repaint_panel_sopra();
		repaint_panel_sotto();
	}

	/**
	 * aggiorniamo il panel sotto
	 */
	private void repaint_panel_sotto() {
		if (!panel_tavolo_occupato.tavolo.resoconto_tavolo.getLista_ordini().isEmpty()) {
			btn_pagato.setEnabled(true);
		}
		// aggiorniamo il label per il prezzo
		aggiorna_prezzo_totale();
		// aggiorniamo lo storico ordini
		aggiorna_ordini_effettuati();
	}

	/**
	 * aggiorniamo il panel sopra
	 */
	private void repaint_panel_sopra() {
		panel_sopra_ordini.removeAll();
		panel_sopra_ordini.revalidate();
		panel_sopra_ordini.repaint();

		aggiorna_piatti();

		// aggiornaimo i pulsanti
		if (!panel_tavolo_occupato.lista_piatti_ordinati.isEmpty()) {
			btn_invia.setEnabled(true);
		}
	}

	/**
	 * aggiunge i piatti selezionati al panel sopra
	 */
	private void aggiorna_piatti() {
		for (PiattoOrdinato piatto : panel_tavolo_occupato.lista_piatti_ordinati) {
			Piatto_Ordinato_Piu_Meno_Text element = new Piatto_Ordinato_Piu_Meno_Text(piatto);
			panel_sopra_ordini.add(element);
		}
	}

	/**
	 * aggiorniamo i piatti ordinati in precedenza
	 */
	private void aggiorna_ordini_effettuati() {
		DefaultListModel<String> model_piatto = new DefaultListModel<>();
		DefaultListModel<String> model_prezzo = new DefaultListModel<>();

		// aggiunta coperti
		int num_coperti = panel_tavolo_occupato.tavolo.resoconto_tavolo.getNum_coperti();
		model_piatto.addElement(num_coperti + "x Coperto");
		model_prezzo.addElement(formatta_prezzo(num_coperti * ResocontoTavolo.COSTO_COPERTO) + " €   ");

		// aggiungiamo gli ordini
		for (Ordine ordine : panel_tavolo_occupato.tavolo.resoconto_tavolo.getLista_ordini()) {
			for (PiattoOrdinato piatto_ordinato : ordine.getLista_piatti_ordinati()) {

				String str_nome_piatto = piatto_ordinato.getQuantita() + "x " + piatto_ordinato.getPiatto().getNome();
				String str_commento = piatto_ordinato.getCommento();

				String str_prezzo = formatta_prezzo(piatto_ordinato.getPrezzo()) + " €   ";

				model_piatto.addElement(str_nome_piatto);
				model_prezzo.addElement(str_prezzo);

				if (!str_commento.isBlank()) {
					// aggiungiamo la riga per il commento
					model_piatto.addElement(" > " + str_commento);
					// aggiungiamo un carattere invisibile (tale da mantenere l'ordine nelle liste)
					model_prezzo.addElement("\t");
				}
			}
		}

		// aggiungiamo gli elementi alle JList
		JList<String> lista_prezzi = new JList<>(model_prezzo);
		JList<String> lista_ordini_effettuati = new JList<>(model_piatto);

		lista_prezzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista_ordini_effettuati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		lista_ordini_effettuati.setEnabled(false);
		lista_prezzi.setEnabled(false);

		// aggiungiamo le liste al panel sotto
		panel_sotto.add(lista_ordini_effettuati, BorderLayout.CENTER);
		panel_sotto.add(lista_prezzi, BorderLayout.EAST);
	}

	/**
	 * aggiorniamo il prezzo totale
	 */
	private void aggiorna_prezzo_totale() {
		lbl_totale.setText("Importo Totale: "
				+ formatta_prezzo(panel_tavolo_occupato.tavolo.resoconto_tavolo.getPrezzo_totale()) + " €");
	}

	/**
	 * Classe per il bottone pagato
	 */
	class Bottone_Pagato extends JButton {

		private static final long serialVersionUID = -1400046920322830699L;

		public Bottone_Pagato() {
			super("Pagato");
			boolean btn_attivare = !panel_tavolo_occupato.tavolo.resoconto_tavolo.getLista_ordini().isEmpty();
			setEnabled(btn_attivare);
			addActionListener(new ActionListener_btn_pagato());
		}

		class ActionListener_btn_pagato implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// disabilitiamo il frame
				setEnabled(false);
				
				// creaiamo gli action listener necessari
				ActionListener listener_si = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// paghiamo il tavolo
						panel_tavolo_occupato.tavolo.pagato();
						// riattiviamo il frame principale
						setEnabled(true);
						// torniamo al panel tavoli
						GUIMain frame =(GUIMain) SwingUtilities.getWindowAncestor(panel_tavolo_occupato);
						frame.repaint_panel_tavoli();
						JComponent componente = (JComponent) e.getSource();
						SwingUtilities.getWindowAncestor(componente).dispose();
					}
				};

				ActionListener listener_no = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JComponent componente = (JComponent) e.getSource();
						SwingUtilities.getWindowAncestor(componente).dispose();
						setEnabled(true);
					}
				};

				// apriamo il frame che chiede la conferma
				new Frame_conferma("E' stato effettuato il pagamento?", listener_si, listener_no);
			}

			/**
			 * @param bool: valore se true abilitiamo il frame mentre lo disabilitiamo se l'opposto
			 */
			private void setEnabled(boolean bool) {
				JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(panel_tavolo_occupato);
				frame.setEnabled(bool);
			}

		}
	}

	/**
	 * Classe per il bottone invia
	 */

	class Bottone_Invia extends JButton {

		private static final long serialVersionUID = 5053523527111913425L;

		public Bottone_Invia() {
			super("Invia");
			setEnabled(false);
			addActionListener(new ActionListener_btn_invia());
		}

		/**
		 * implementazione action listener
		 */
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
