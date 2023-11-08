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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Classi.Componente;
import Classi.Piatto;
import Classi.PiattoOrdinato;

public class Panel_Componenti_Tavolo_Occupato extends JPanel {

	private static final long serialVersionUID = -5753067260932073798L;

	List<PanelComponente> lista_panel_componenti;
	private List<Componente> lista_componenti;
	private PanelTavoloOccupato panelTavoloOccupato;
	private Text_Commento_Piatto commento_piatto;
	private JButton btn_aggiungi;

	public Panel_Componenti_Tavolo_Occupato(List<Componente> lista_componenti, PanelTavoloOccupato panelTavoloOccupato) {

		this.panelTavoloOccupato = panelTavoloOccupato;

		commento_piatto = new Text_Commento_Piatto("Commenti vanno Qui!!");
		btn_aggiungi = new JButton("Aggiungi");
		btn_aggiungi.addActionListener(new ActionListener_Btn_Aggiungi());

		this.lista_componenti = lista_componenti;
		JPanel panelDX_Basso = new JPanel();
		JTabbedPane tabbedCoponenti = new JTabbedPane();

		verifica_btn_aggiungi();

		setLayout(new BorderLayout());
		panelDX_Basso.setLayout(new BorderLayout());

		aggiungi_panel_componenti(tabbedCoponenti);

		tabbedCoponenti.addChangeListener(new ChangeListener_Tabbet_Componenti());

		aggiungi_elementi_panel_dx(tabbedCoponenti, panelDX_Basso);

	}

	// verifica se il bottone aggiungi deve stare attivo o disattivato
	private void verifica_btn_aggiungi() {
		if (panelTavoloOccupato.piatto_ordinato_attuale == null
				|| panelTavoloOccupato.piatto_ordinato_attuale.getPiatto() == null) {
			btn_aggiungi.setEnabled(false);
		} else {
			btn_aggiungi.setEnabled(true);
		}
	}

	private void aggiungi_panel_componenti(JTabbedPane tabbed) {
		lista_panel_componenti = new ArrayList<>();
		for (Componente comp : lista_componenti) {
			// creo il panel per la componente
			PanelComponente panel = new PanelComponente(comp);
			// aggiunto lo scrollPane alla componente
			JScrollPane scroll = new JScrollPane(panel);
			scroll.getViewport().setPreferredSize(new Dimension(1000, 700));
			scroll.getVerticalScrollBar().setUnitIncrement(10);
			// aggiungo la componente al tab
			tabbed.add(scroll, comp.getNome());
			lista_panel_componenti.add(panel);
		}
	}

	public void repaint_panel() {
		for (PanelComponente panel : lista_panel_componenti) {
			panel.repaint_all_btn_piatti();
		}
	}

	private void aggiungi_elementi_panel_dx(JTabbedPane tabbedCoponenti, JPanel panelDX_Basso) {
		panelDX_Basso.add(commento_piatto, BorderLayout.CENTER);
		panelDX_Basso.add(btn_aggiungi, BorderLayout.EAST);
		add(tabbedCoponenti, BorderLayout.NORTH);
		add(panelDX_Basso);
	}

	class PanelComponente extends JPanel {

		private static final long serialVersionUID = -8073574984234371067L;

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

		public void repaint_all_btn_piatti() {
			for (Bottone_Piatto btn : lista_bottoni_piatti) {
				if (panelTavoloOccupato.piatto_ordinato_attuale == null
						|| !btn.equals_piatto(panelTavoloOccupato.piatto_ordinato_attuale.getPiatto())) {
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
				if (panelTavoloOccupato.piatto_ordinato_attuale == null) {
					panelTavoloOccupato.piatto_ordinato_attuale = new PiattoOrdinato(btn.getPiatto());
				} else {
					if (panelTavoloOccupato.piatto_ordinato_attuale.getPiatto() == null) {
						panelTavoloOccupato.piatto_ordinato_attuale.setPiatto(btn.getPiatto());
					} else if (panelTavoloOccupato.piatto_ordinato_attuale.getPiatto().equals(btn.getPiatto())) {
						panelTavoloOccupato.piatto_ordinato_attuale.setPiatto(null);
					} else {
						panelTavoloOccupato.piatto_ordinato_attuale.setPiatto(btn.getPiatto());
					}
				}
				repaint_all_btn_piatti();
			}

		}

	}

	class ChangeListener_Tabbet_Componenti implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			repaint_panel();
		}

	}

	class ActionListener_Btn_Aggiungi implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			panelTavoloOccupato.aggiungi_piatto(commento_piatto);

		}

	}

}
