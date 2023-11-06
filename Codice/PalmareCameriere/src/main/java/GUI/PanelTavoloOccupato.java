package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Classi.Componente;
import Classi.Ordine;
import Classi.Piatto;
import Classi.PiattoOrdinato;
import Classi.ResocontoTavolo;
import Classi.Tavolo;

public class PanelTavoloOccupato extends JPanel {

	private static final int POS_SEPARAZIONE_DX_SX = 550;
	private ResocontoTavolo resoconto_tavolo;
	private JButton aggiungi;
	private List<PanelComponente> lista_panel_componenti;
	private List<PiattoOrdinato> lista_piatti_ordinati;
	private PiattoOrdinato piatto_ordinato_attuale;
	private Text_Commento_Piatto commento_piatto;
	private Panel_Ordini_Tavolo_Occupato panel_sx;
	private JLabel lbl_totale;
	private Bottone_Invia btn_invia;
	private Bottone_Paga btn_paga;

	public PanelTavoloOccupato(Tavolo tavolo, List<Componente> lista_componenti) {
		resoconto_tavolo = tavolo.resoconto_tavolo;
		lista_piatti_ordinati = new ArrayList<>();
		commento_piatto = new Text_Commento_Piatto("Commenti vanno Qui!!");
		aggiungi = new JButton("Aggiungi");

		lbl_totale = new JLabel("Importo Totale: " + resoconto_tavolo.getPrezzo_totale() + " €");
		btn_invia = new Bottone_Invia();
		btn_paga = new Bottone_Paga();
		panel_sx = new Panel_Ordini_Tavolo_Occupato(btn_invia, btn_paga, lbl_totale);
		JPanel panel_dx = new JPanel();
		JPanel panelDX_Basso = new JPanel();
		JTabbedPane tabbedCoponenti = new JTabbedPane();
		JScrollPane scroll_pane_sx = new JScrollPane(panel_sx);

		// setting Layout
		setLayout(new BorderLayout());
		panel_dx.setLayout(new BorderLayout());
		panelDX_Basso.setLayout(new BorderLayout());

		// Aggiunta dei Panel delle componenti
		aggiungi_panel_componenti(tabbedCoponenti, lista_componenti);

		// Action Listener
		tabbedCoponenti.addChangeListener(new ChangeListener_Tabbet_Componenti());
		aggiungi.addActionListener(new ActionListener_Btn_Aggiungi());

		// Aggiunta degli Elementi al panel DX
		aggiungi_elementi_panel_dx(panel_dx, tabbedCoponenti, panelDX_Basso);

		// Verifica del Bottone Aggiungi
		verifica_btn_aggiungi();

		JSplitPane split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_sx, panel_dx);
		split_pane.setDividerLocation(POS_SEPARAZIONE_DX_SX);
		split_pane.setEnabled(false);
		add(split_pane);

	}

	private void aggiungi_elementi_panel_dx(JPanel panel_dx, JTabbedPane tabbedCoponenti, JPanel panelDX_Basso) {
		panelDX_Basso.add(commento_piatto, BorderLayout.CENTER);
		panelDX_Basso.add(aggiungi, BorderLayout.EAST);
		panel_dx.add(tabbedCoponenti, BorderLayout.NORTH);
		panel_dx.add(panelDX_Basso);
	}

	// verifica se il bottone aggiungi deve stare attivo o disattivato
	private void verifica_btn_aggiungi() {
		if (piatto_ordinato_attuale == null || piatto_ordinato_attuale.getPiatto() == null) {
			aggiungi.setEnabled(false);
		} else {
			aggiungi.setEnabled(true);
		}
	}

	private void aggiungi_panel_componenti(JTabbedPane tabbed, List<Componente> lista_componenti) {
		lista_panel_componenti = new ArrayList<>();
		for (Componente comp : lista_componenti) {
			// creo il panel per la componente
			PanelComponente panel = new PanelComponente(comp);
			// aggiunto lo scrollPane alla componente
			JScrollPane scroll = new JScrollPane(panel);
			scroll.getViewport().setPreferredSize(new Dimension(1000, 700));
			// aggiungo la componente al tab
			tabbed.add(scroll, comp.getNome());
			lista_panel_componenti.add(panel);
		}
	}

	private void repaint_panel_dx() {
		for (PanelComponente panel : lista_panel_componenti) {
			panel.repaint_all_btn_piatti();
		}
	}

	class ChangeListener_Tabbet_Componenti implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			repaint_panel_dx();
		}

	}

	class ActionListener_Btn_Aggiungi implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// aggiungiamo il commento(se inseirito) nel piatto_ordinato_attuale
			piatto_ordinato_attuale.setCommento(commento_piatto.getText());
			// resettiamo i commenti
			commento_piatto.reset();
			// salviamo il piatto nella lista dei piatti ordinati
			lista_piatti_ordinati.add(piatto_ordinato_attuale);
			// resettiamo il piatto_ordinato_attuale
			piatto_ordinato_attuale = null;
			// aggiorniamo il panel_DX
			repaint_panel_dx();
			// il panel_SX
			panel_sx.repaint_panel();

		}

	}

	class Bottone_Invia extends JButton {

		public Bottone_Invia() {
			super("Invia");
			setEnabled(false);
			addActionListener(new ActionListener_btn_invia());
		}

		class ActionListener_btn_invia implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				Ordine ordine = new Ordine(lista_piatti_ordinati);
				resoconto_tavolo.aggingiOrdine(ordine);
				lista_piatti_ordinati = new ArrayList<PiattoOrdinato>();
				panel_sx.repaint_panel();
			}

		}

	}

	class Bottone_Paga extends JButton {

		public Bottone_Paga() {
			super("Pagato");
			boolean btn_attivare = !resoconto_tavolo.getLista_ordini().isEmpty();
			setEnabled(btn_attivare);
			addActionListener(new ActionListener_btn_paga());
		}

		class ActionListener_btn_paga implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		}
	}

	// classe per il panel di sinistra
	class Panel_Ordini_Tavolo_Occupato extends JPanel {

		public JPanel panel_sopra;
		public JPanel panel_sotto;

		public Panel_Ordini_Tavolo_Occupato(Bottone_Invia btn_invia, Bottone_Paga btn_paga, JLabel lbl_totale) {

			panel_sopra = new JPanel();
			panel_sotto = new JPanel();

			panel_sopra.setLayout(new BoxLayout(panel_sopra, BoxLayout.Y_AXIS));

			panel_sotto.setLayout(new GridLayout(0, 3));
			panel_sotto.add(btn_paga, BorderLayout.WEST);
			panel_sotto.add(btn_invia, BorderLayout.EAST);

			// mettere prezzo sopra pulsanti
			panel_sotto.add(lbl_totale, BorderLayout.CENTER);

			setLayout(new BorderLayout());
			add(panel_sopra, BorderLayout.CENTER);
			add(panel_sotto, BorderLayout.SOUTH);
		}
		
		public void repaint_panel() {
			panel_sopra.removeAll();
			panel_sopra.removeAll();
			panel_sopra.revalidate();
			panel_sopra.repaint();
			for (PiattoOrdinato piatto_ordinato : lista_piatti_ordinati) {
				Piatto_Ordinato_Piu_Meno_Text element = new Piatto_Ordinato_Piu_Meno_Text(piatto_ordinato);
				panel_sx.panel_sopra.add(element);
			}
			if (lista_piatti_ordinati.size() > 0) {
				btn_invia.setEnabled(true);
			}
			aggiorna_prezzo();
		}

		private void aggiorna_prezzo() {
			lbl_totale.setText("Importo Totale: " + resoconto_tavolo.getPrezzo_totale() + " €");
		}

	}
	
	

	class PanelComponente extends JPanel {

		List<Bottone_Piatto> lista_bottoni_piatti;

		public PanelComponente(Componente componente) {
			super();

			setLayout(new GridLayout(0, 1, 5, 5));
			aggiungi_btn_piatti(componente.getLista_piatti());
		}

		void aggiungi_btn_piatti(List<Piatto> lista_piatti) {
			List<Bottone_Piatto> lista_bottoni_piatti = new ArrayList<>();
			for (Piatto piatto : lista_piatti) {
				Bottone_Piatto btn = new Bottone_Piatto(piatto.getNome(), piatto);
				btn.addActionListener(new Action_Pulsante_Piatto());
				btn.setPreferredSize(new Dimension(100, 100));
				btn.setFont(new Font("Verdana", Font.BOLD, 20));
				add(btn);
				lista_bottoni_piatti.add(btn);
			}
			this.lista_bottoni_piatti = lista_bottoni_piatti;
		}

		private void repaint_all_btn_piatti() {
			for (Bottone_Piatto btn : lista_bottoni_piatti) {
				if (piatto_ordinato_attuale == null || !btn.equals_piatto(piatto_ordinato_attuale.getPiatto())) {
					btn.setBackground(null);
				} else {
					btn.setBackground(Color.cyan);
				}
			}
			verifica_btn_aggiungi();

		}

		class Action_Pulsante_Piatto implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				Bottone_Piatto btn = (Bottone_Piatto) e.getSource();
				if (piatto_ordinato_attuale == null) {
					piatto_ordinato_attuale = new PiattoOrdinato(btn.getPiatto());
				} else {
					if (piatto_ordinato_attuale.getPiatto() == null) {
						piatto_ordinato_attuale.setPiatto(btn.getPiatto());
					} else if (piatto_ordinato_attuale.getPiatto().equals(btn.getPiatto())) {
						piatto_ordinato_attuale.setPiatto(null);
					} else {
						piatto_ordinato_attuale.setPiatto(btn.getPiatto());
					}
				}
				repaint_all_btn_piatti();
			}

		}

	}

}
