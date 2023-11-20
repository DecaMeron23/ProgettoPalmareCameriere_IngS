package main.tavoloOccupato.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import classi.ordine.Ordine;
import classi.ordine.PiattoOrdinato;
import classi.tavolo.ResocontoTavolo;
import main.FrameConfermaScelta;
import main.MainFrame;
import main.tavoloOccupato.PanelPiattoOrdinatoSelettore;

/**
 * Panel della riguardante la parte SX del panel tavolo occupato
 */
class PanelTavoloOccupatoSinistro extends JPanel {

	private static final long serialVersionUID = -2916448870874586511L;

	/**
	 * panel contenente gli ordini attuali
	 */
	private JPanel panelSopraOrdini;

	/**
	 * label contenente il pulsante pagato e il label totale
	 */
	private JPanel panelPulsanteLabelSotto;

	/**
	 * Panel che contiene gli storici, il bottone pagato e il label del totale
	 */
	private JPanel panelSotto;

	/**
	 * Bottone per inviare l'ordine
	 */
	private JButton btnInvia;

	/**
	 * Bottone per indicare se il cliente ha pagato
	 */
	private JButton btnPagato;

	/**
	 * Label per il testo del prezzo totale
	 */
	private JLabel lblTotale;

	/**
	 * Panel superiore
	 */
	private PanelTavoloOccupato panelTavoloOccupato;

	/**
	 * @param panel_tavolo_occupato: panel principale
	 */
	public PanelTavoloOccupatoSinistro(PanelTavoloOccupato panelTavoloOccupato) {

		this.panelTavoloOccupato = panelTavoloOccupato;

		JPanel panelSopra = new JPanel(new BorderLayout());
		panelSopraOrdini = new JPanel();
		panelSotto = new JPanel(new BorderLayout());

		panelSopraOrdini.setLayout(new BoxLayout(panelSopraOrdini, BoxLayout.Y_AXIS));

		panelPulsanteLabelSotto = new JPanel();

		btnInvia = new BottoneInvia();
		btnPagato = new BottonePagato();

		lblTotale = new JLabel();

		JScrollPane scrollSopra = new JScrollPane(panelSopra);
		JScrollPane scrollSotto = new JScrollPane(panelSotto);

		panelSopra.add(panelSopraOrdini, BorderLayout.CENTER);
		panelSopra.add(btnInvia, BorderLayout.SOUTH);

		scrollSopra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSotto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		scrollSopra.getVerticalScrollBar().setUnitIncrement(10);
		scrollSotto.getVerticalScrollBar().setUnitIncrement(10);

		scrollSopra.getViewport().setPreferredSize(new Dimension(50, 50));
		scrollSotto.getViewport().setPreferredSize(new Dimension(50, 50));

		panelPulsanteLabelSotto.setLayout(new GridLayout(0, 3));
		panelPulsanteLabelSotto.add(btnPagato);
		panelPulsanteLabelSotto.add(lblTotale);

		panelSotto.add(panelPulsanteLabelSotto, BorderLayout.SOUTH);

		setLayout(new GridLayout(0, 1));
		add(scrollSopra);
		add(scrollSotto);

		// aggiorniamo il prezzo totale
		aggiornaPrezzoTotale();
	}

	/**
	 * formatta un numero double in una stringa con due cifre decimali dopo
	 * 
	 * @param numero: valore da formattare
	 * @return una stringa con il valore del numero passato come parametro con due
	 *         cifre decimali
	 */
	private String formattaPrezzo(double numero) {
		DecimalFormat formato = new DecimalFormat("0.00");
		String numeroFormattato = formato.format(numero);
		return numeroFormattato;
	}

	/**
	 * funzione che aggionra il panel
	 */
	public void repaintPanel() {
		// aggiorniamo i due panel
		repaintPanelSopra();
		repaintPanelSotto();
	}

	/**
	 * aggiorniamo il panel sotto
	 */
	private void repaintPanelSotto() {
		if (!panelTavoloOccupato.tavolo.resocontoTavolo.getListaOrdini().isEmpty()) {
			btnPagato.setEnabled(true);
		}
		// aggiorniamo il label per il prezzo
		aggiornaPrezzoTotale();
		// aggiorniamo lo storico ordini
		aggiornaOrdiniEffettuati();
	}

	/**
	 * aggiorniamo il panel sopra
	 */
	private void repaintPanelSopra() {
		panelSopraOrdini.removeAll();
		panelSopraOrdini.revalidate();
		panelSopraOrdini.repaint();

		aggiornaPiatti();

		// aggiornaimo i pulsanti
		if (!panelTavoloOccupato.listaPiattiOrdinati.isEmpty()) {
			btnInvia.setEnabled(true);
		}
	}

	/**
	 * aggiunge i piatti selezionati al panel sopra
	 */
	private void aggiornaPiatti() {
		for (PiattoOrdinato piatto : panelTavoloOccupato.listaPiattiOrdinati) {
			PanelPiattoOrdinatoSelettore element = new PanelPiattoOrdinatoSelettore(piatto);
			panelSopraOrdini.add(element);
		}
	}

	/**
	 * aggiorniamo i piatti ordinati in precedenza
	 */
	private void aggiornaOrdiniEffettuati() {
		DefaultListModel<String> modelPiatto = new DefaultListModel<>();
		DefaultListModel<String> modelPrezzo = new DefaultListModel<>();

		// aggiunta coperti
		int numCoperti = panelTavoloOccupato.tavolo.resocontoTavolo.getNumCoperti();
		modelPiatto.addElement(numCoperti + "x Coperto");
		modelPrezzo.addElement(formattaPrezzo(numCoperti * ResocontoTavolo.COSTO_COPERTO) + " €   ");

		// aggiungiamo gli ordini
		for (Ordine ordine : panelTavoloOccupato.tavolo.resocontoTavolo.getListaOrdini()) {
			for (PiattoOrdinato piattoOrdinato : ordine.getListaPiattiOrdinati()) {

				String strNomePiatto = piattoOrdinato.getQuantita() + "x " + piattoOrdinato.getPiatto().getNome();
				String strCommento = piattoOrdinato.getCommento();

				String strPrezzo = formattaPrezzo(piattoOrdinato.getPrezzo()) + " €   ";

				modelPiatto.addElement(strNomePiatto);
				modelPrezzo.addElement(strPrezzo);

				if (!strCommento.isBlank()) {
					// aggiungiamo la riga per il commento
					modelPiatto.addElement(" > " + strCommento);
					// aggiungiamo un carattere invisibile (tale da mantenere l'ordine nelle liste)
					modelPrezzo.addElement("\t");
				}
			}
		}

		// aggiungiamo gli elementi alle JList
		JList<String> listaPrezzi = new JList<>(modelPrezzo);
		JList<String> listaOrdiniEffettuati = new JList<>(modelPiatto);

		listaPrezzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaOrdiniEffettuati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listaOrdiniEffettuati.setEnabled(false);
		listaPrezzi.setEnabled(false);

		// aggiungiamo le liste al panel sotto
		panelSotto.add(listaOrdiniEffettuati, BorderLayout.CENTER);
		panelSotto.add(listaPrezzi, BorderLayout.EAST);
	}

	/**
	 * aggiorniamo il prezzo totale
	 */
	private void aggiornaPrezzoTotale() {
		lblTotale.setText("Importo Totale: "
				+ formattaPrezzo(panelTavoloOccupato.tavolo.resocontoTavolo.getPrezzoTotale()) + " €");
	}
	
	private void enable_frame(boolean bol) {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelTavoloOccupato);
		frame.setAlwaysOnTop(bol);
		frame.setEnabled(bol);
	}

	/**
	 * Classe per il bottone pagato
	 */
	private class BottonePagato extends JButton {

		private static final long serialVersionUID = -1400046920322830699L;

		public BottonePagato() {
			super("Pagato");
			boolean btnAttivare = !panelTavoloOccupato.tavolo.resocontoTavolo.getListaOrdini().isEmpty();
			setEnabled(btnAttivare);
			addActionListener(new ActionListenerBtnPagato());
		}

		private class ActionListenerBtnPagato implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// disabilitiamo il frame
				setEnabled(false);
				
				// creaiamo gli action listener necessari
				ActionListener actSi = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// paghiamo il tavolo
						panelTavoloOccupato.tavolo.pagato();
						// riattiviamo il frame principale
						setEnabled(true);
						// torniamo al panel tavoli
						MainFrame frame =(MainFrame) SwingUtilities.getWindowAncestor(panelTavoloOccupato);
						frame.repaintPanelTavoli();
						JComponent componente = (JComponent) e.getSource();
						SwingUtilities.getWindowAncestor(componente).dispose();
					}
				};

				ActionListener actNo = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JComponent componente = (JComponent) e.getSource();
						SwingUtilities.getWindowAncestor(componente).dispose();
						setEnabled(true);
					}
				};
				
				WindowAdapter adapter = new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						enable_frame(true);
					}
				};

				// apriamo il frame che chiede la conferma
				new FrameConfermaScelta("E' stato effettuato il pagamento?", actSi, actNo , adapter);
			}

			/**
			 * @param bool: valore se true abilitiamo il frame mentre lo disabilitiamo se l'opposto
			 */
			private void setEnabled(boolean bool) {
				JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(panelTavoloOccupato);
				frame.setEnabled(bool);
			}

		}
	}

	/**
	 * Classe per il bottone invia
	 */

	private class BottoneInvia extends JButton {

		private static final long serialVersionUID = 5053523527111913425L;

		public BottoneInvia() {
			super("Invia");
			setEnabled(false);
			addActionListener(new ActionListenerBtnInvia());
		}

		/**
		 * implementazione action listener
		 */
		private class ActionListenerBtnInvia implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				Ordine ordine = new Ordine(panelTavoloOccupato.listaPiattiOrdinati);
				panelTavoloOccupato.tavolo.resocontoTavolo.aggingiOrdine(ordine);
				panelTavoloOccupato.listaPiattiOrdinati = new ArrayList<PiattoOrdinato>();
				repaintPanel();
			}

		}

	}

}
