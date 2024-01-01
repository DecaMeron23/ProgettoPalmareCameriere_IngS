/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

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

import classi.dataBase.DataService;
import classi.menu.Componente;
import classi.menu.Piatto;
import classi.ordine.PiattoOrdinato;
import main.tavoloOccupato.BottonePiatto;
import main.tavoloOccupato.TextAreaCommentoPiatto;

/**
 * The Class Panel Tavolo Occupato Destro.
 */
class PanelTavoloOccupatoDestro extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5753067260932073798L;

	/** The lista panel componenti. */
	private List<PanelComponente> listaPanelComponenti;

	/** The panel tavolo occupato. */
	private PanelTavoloOccupato panelTavoloOccupato;

	/** The commento piatto. */
	private TextAreaCommentoPiatto commentoPiatto;

	/** The btn aggiungi. */
	private JButton btnAggiungi;

	/**
	 * Instantiates a new panel tavolo occupato destro.
	 *
	 * @param listaComponenti     the lista componenti
	 * @param panelTavoloOccupato the panel tavolo occupato
	 */
	PanelTavoloOccupatoDestro(PanelTavoloOccupato panelTavoloOccupato) {

		this.panelTavoloOccupato = panelTavoloOccupato;

		commentoPiatto = new TextAreaCommentoPiatto("Commenti vanno Qui!!");
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		btnAggiungi.addActionListener(new ActionListenerBtnAggiungi());

		JPanel panelDestroBasso = new JPanel();
		JTabbedPane tabbedCoponenti = new JTabbedPane();
		tabbedCoponenti.setFont(new Font(getFont().getName(), Font.PLAIN, 20));

		verificaBtnAggiungi();

		setLayout(new BorderLayout());
		panelDestroBasso.setLayout(new BorderLayout());

		aggiungiPanelComponenti(tabbedCoponenti);

		tabbedCoponenti.addChangeListener(new ChangeListenerTabbetComponenti());

		aggiungiElementiPanelDestro(tabbedCoponenti, panelDestroBasso);

	}

	/**
	 * Verifica btn aggiungi.
	 */
	// verifica se il bottone aggiungi deve stare attivo o disattivato
	private void verificaBtnAggiungi() {
		if (panelTavoloOccupato.piattoSelezionato == null
				|| panelTavoloOccupato.piattoSelezionato.getPiatto() == null) {
			btnAggiungi.setEnabled(false);
		} else {
			btnAggiungi.setEnabled(true);
		}
	}

	/**
	 * Aggiungi panel componenti.
	 *
	 * @param tabbed the tabbed
	 */
	private void aggiungiPanelComponenti(JTabbedPane tabbed) {
		listaPanelComponenti = new ArrayList<>();
		for (Componente comp : DataService.getComponenti()) {
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

	/**
	 * Repaint panel.
	 */
	public void repaintPanel() {
		for (PanelComponente panel : listaPanelComponenti) {
			panel.repaintAllBtnPiatti();
		}
	}

	/**
	 * Aggiungi elementi panel destro.
	 *
	 * @param tabbedCoponenti  the tabbed coponenti
	 * @param panelBassoDestro the panel basso destro
	 */
	private void aggiungiElementiPanelDestro(JTabbedPane tabbedCoponenti, JPanel panelBassoDestro) {
		panelBassoDestro.add(commentoPiatto, BorderLayout.CENTER);
		panelBassoDestro.add(btnAggiungi, BorderLayout.EAST);
		add(tabbedCoponenti, BorderLayout.NORTH);
		add(panelBassoDestro);
	}

	/**
	 * The Class PanelComponente.
	 */
	private class PanelComponente extends JPanel {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -8073574984234371067L;

		/** The lista bottoni piatti. */
		List<BottonePiatto> listaBottoniPiatti;

		/**
		 * Instantiates a new panel componente.
		 *
		 * @param componente the componente
		 */
		public PanelComponente(Componente componente) {
			super();

			setLayout(new GridLayout(0, 1, 5, 5));
			aggiungiBtnPiatti(componente.getListaPiatti());
		}

		/**
		 * Aggiungi btn piatti.
		 *
		 * @param listaPiatti the lista piatti
		 */
		void aggiungiBtnPiatti(List<Piatto> listaPiatti) {
			List<BottonePiatto> listaBottoniPiatti = new ArrayList<>();
			for (Piatto piatto : listaPiatti) {
				BottonePiatto btn = new BottonePiatto(piatto);
				btn.addActionListener(new ActionPulsantePiatto());
				btn.setPreferredSize(new Dimension(100, 50));
				add(btn);
				listaBottoniPiatti.add(btn);
			}
			
			int pulsantiMancanti = 13 - listaPiatti.size();
			if (pulsantiMancanti > 0) {
				for (int i = 0; i < pulsantiMancanti; i++) {
					JButton btn = new JButton();
					btn.setPreferredSize(new Dimension(100, 50));
					btn.setVisible(false);
					btn.setEnabled(false);
					add(btn);
				}
			}
			
			this.listaBottoniPiatti = listaBottoniPiatti;
		}

		/**
		 * Repaint all btn piatti.
		 */
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

		/**
		 * The Class ActionPulsantePiatto.
		 */
		class ActionPulsantePiatto implements ActionListener {

			/**
			 * Action performed.
			 *
			 * @param e the e
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				BottonePiatto btn = (BottonePiatto) e.getSource();
				if (panelTavoloOccupato.piattoSelezionato == null) {
					panelTavoloOccupato.piattoSelezionato = new PiattoOrdinato(btn.getPiatto() , 1); // da sistemare
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

	/**
	 * The Class ChangeListenerTabbetComponenti.
	 */
	private class ChangeListenerTabbetComponenti implements ChangeListener {

		/**
		 * State changed.
		 *
		 * @param e the e
		 */
		@Override
		public void stateChanged(ChangeEvent e) {
			repaintPanel();
		}

	}

	/**
	 * The Class ActionListenerBtnAggiungi.
	 */
	private class ActionListenerBtnAggiungi implements ActionListener {

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			panelTavoloOccupato.aggiungiPiatto(commentoPiatto);

		}

	}

}
