package main.tavoloOccupato.panel;

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

import classi.menu.Componente;
import classi.menu.Piatto;
import classi.ordine.PiattoOrdinato;
import main.tavoloOccupato.BottonePiatto;
import main.tavoloOccupato.TextAreaCommentoPiatto;

public class PanelTavoloOccupatoDestro extends JPanel {

	private static final long serialVersionUID = -5753067260932073798L;

	List<PanelComponente> listaPanelComponenti;
	private List<Componente> listaComponenti;
	private PanelTavoloOccupato panelTavoloOccupato;
	private TextAreaCommentoPiatto commentoPiatto;
	private JButton btnAggiungi;

	public PanelTavoloOccupatoDestro(List<Componente> listaComponenti, PanelTavoloOccupato panelTavoloOccupato) {

		this.panelTavoloOccupato = panelTavoloOccupato;

		commentoPiatto = new TextAreaCommentoPiatto("Commenti vanno Qui!!");
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListenerBtnAggiungi());

		this.listaComponenti = listaComponenti;
		JPanel panelDestroBasso = new JPanel();
		JTabbedPane tabbedCoponenti = new JTabbedPane();

		verificaBtnAggiungi();

		setLayout(new BorderLayout());
		panelDestroBasso.setLayout(new BorderLayout());

		aggiungiPanelComponenti(tabbedCoponenti);

		tabbedCoponenti.addChangeListener(new ChangeListenerTabbetComponenti());

		aggiungiElementiPanelDestro(tabbedCoponenti, panelDestroBasso);

	}

	// verifica se il bottone aggiungi deve stare attivo o disattivato
	private void verificaBtnAggiungi() {
		if (panelTavoloOccupato.piattoSelezionato == null
				|| panelTavoloOccupato.piattoSelezionato.getPiatto() == null) {
			btnAggiungi.setEnabled(false);
		} else {
			btnAggiungi.setEnabled(true);
		}
	}

	private void aggiungiPanelComponenti(JTabbedPane tabbed) {
		listaPanelComponenti = new ArrayList<>();
		for (Componente comp : listaComponenti) {
			// creo il panel per la componente
			PanelComponente panel = new PanelComponente(comp);
			// aggiunto lo scrollPane alla componente
			JScrollPane scroll = new JScrollPane(panel);
			scroll.getViewport().setPreferredSize(new Dimension(1000, 700));
			scroll.getVerticalScrollBar().setUnitIncrement(10);
			// aggiungo la componente al tab
			tabbed.add(scroll, comp.getNome());
			listaPanelComponenti.add(panel);
		}
	}

	public void repaintPanel() {
		for (PanelComponente panel : listaPanelComponenti) {
			panel.repaintAllBtnPiatti();
		}
	}

	private void aggiungiElementiPanelDestro(JTabbedPane tabbedCoponenti, JPanel panelBassoDestro) {
		panelBassoDestro.add(commentoPiatto, BorderLayout.CENTER);
		panelBassoDestro.add(btnAggiungi, BorderLayout.EAST);
		add(tabbedCoponenti, BorderLayout.NORTH);
		add(panelBassoDestro);
	}

	private class PanelComponente extends JPanel {

		private static final long serialVersionUID = -8073574984234371067L;

		List<BottonePiatto> listaBottoniPiatti;

		public PanelComponente(Componente componente) {
			super();

			setLayout(new GridLayout(0, 1, 5, 5));
			aggiungiBtnPiatti(componente.getListaPiatti());
		}

		void aggiungiBtnPiatti(List<Piatto> listaPiatti) {
			List<BottonePiatto> listaBottoniPiatti = new ArrayList<>();
			for (Piatto piatto : listaPiatti) {
				BottonePiatto btn = new BottonePiatto(piatto.getNome(), piatto);
				btn.addActionListener(new ActionPulsantePiatto());
				btn.setPreferredSize(new Dimension(100, 100));
				btn.setFont(new Font("Verdana", Font.BOLD, 20));
				add(btn);
				listaBottoniPiatti.add(btn);
			}
			this.listaBottoniPiatti = listaBottoniPiatti;
		}

		public void repaintAllBtnPiatti() {
			for (BottonePiatto btn : listaBottoniPiatti) {
				if (panelTavoloOccupato.piattoSelezionato == null
						|| !btn.equals_piatto(panelTavoloOccupato.piattoSelezionato.getPiatto())) {
					btn.setBackground(null);
				} else {
					btn.setBackground(Color.cyan);
				}
			}
			verificaBtnAggiungi();

		}

		class ActionPulsantePiatto implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				BottonePiatto btn = (BottonePiatto) e.getSource();
				if (panelTavoloOccupato.piattoSelezionato == null) {
					panelTavoloOccupato.piattoSelezionato = new PiattoOrdinato(btn.getPiatto());
				} else {
					if (panelTavoloOccupato.piattoSelezionato.getPiatto() == null) {
						panelTavoloOccupato.piattoSelezionato.setPiatto(btn.getPiatto());
					} else if (panelTavoloOccupato.piattoSelezionato.getPiatto().equals(btn.getPiatto())) {
						panelTavoloOccupato.piattoSelezionato.setPiatto(null);
					} else {
						panelTavoloOccupato.piattoSelezionato.setPiatto(btn.getPiatto());
					}
				}
				repaintAllBtnPiatti();
			}

		}

	}

	private class ChangeListenerTabbetComponenti implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			repaintPanel();
		}

	}

	private class ActionListenerBtnAggiungi implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			panelTavoloOccupato.aggiungiPiatto(commentoPiatto);

		}

	}

}
