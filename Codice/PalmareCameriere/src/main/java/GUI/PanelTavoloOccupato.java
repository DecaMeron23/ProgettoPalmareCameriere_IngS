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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import Classi.Componente;
import Classi.Piatto;
import Classi.Tavolo;

public class PanelTavoloOccupato extends JPanel {

	private Tavolo tavolo;
	private JButton aggiungi;
	private ArrayList<PanelComponente> lista_panel_componenti;

	public PanelTavoloOccupato(Tavolo tavolo, List<Componente> lista_componenti) {
		super();
		this.tavolo = tavolo;

		setLayout(new BorderLayout());

		JPanel ordini = new PanelOrdini();
		JPanel panelDX = new JPanel();
		JTabbedPane tabbedCoponenti = new JTabbedPane();
		aggiungiComponenti(tabbedCoponenti, lista_componenti);

		tabbedCoponenti.addChangeListener(new ChangeListener_tabbet_componenti());

		panelDX.setLayout(new BorderLayout());

		panelDX.add(tabbedCoponenti, BorderLayout.NORTH);

		JPanel panelDX_Basso = new JPanel();
		panelDX_Basso.setLayout(new BorderLayout());
		JTextArea commenti = new JTextArea("Commenti vanno Qui!!");
		aggiungi = new JButton("Aggiungi");
		aggiungi.setEnabled(false);

		aggiungi.addActionListener(new ActionListener_Aggiungi());

		panelDX_Basso.add(commenti, BorderLayout.CENTER);
		panelDX_Basso.add(aggiungi, BorderLayout.EAST);

		panelDX.add(panelDX_Basso);

		add(panelDX, BorderLayout.EAST);

		add(ordini, BorderLayout.WEST);

	}

	private void aggiungiComponenti(JTabbedPane tabbed, List<Componente> lista_componenti) {

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

	class ChangeListener_tabbet_componenti implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			repaintAll();
		}

	}

	private void repaintAll() {
		for (PanelComponente panel : lista_panel_componenti) {
			panel.repaintAll();
		}
	}

	class ActionListener_Aggiungi implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			repaintAll();
		}

	}

	class PanelOrdini extends JPanel {

		public PanelOrdini() {
			super();
			setLayout(new GridLayout(1, 0, 0, 0));
		}

	}

	class PanelComponente extends JPanel {

		List<Button_Piatto> lista_bottoni_piatti;

		public PanelComponente(Componente componente) {
			super();

			setLayout(new GridLayout(0, 1, 5, 5));
			aggiungiPiatti(componente.getLista_piatti());
		}

		void aggiungiPiatti(List<Piatto> lista_piatti) {
			List<Button_Piatto> lista_bottoni_piatti = new ArrayList<>();
			for (Piatto piatto : lista_piatti) {
				Button_Piatto btn = new Button_Piatto(piatto.getNome(), piatto);
				btn.addActionListener(new Action_Pulsante_Piatto());
				btn.setPreferredSize(new Dimension(100, 100));
				btn.setFont(new Font("Verdana", Font.BOLD, 20));
				add(btn);
				lista_bottoni_piatti.add(btn);
			}
			this.lista_bottoni_piatti = lista_bottoni_piatti;
		}

		private void repaintAll() {
			for (Button_Piatto btn : lista_bottoni_piatti) {
				btn.setBackground(null);
			}

		}

		class Action_Pulsante_Piatto implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaintAll();
				Button_Piatto btn = (Button_Piatto) e.getSource();
				btn.setBackground(Color.blue);
				if (!aggiungi.isEnabled()) {
					aggiungi.setEnabled(true);
				}

			}

		}

		class Button_Piatto extends JButton {

			Piatto piatto;

			public Button_Piatto(String name, Piatto piatto) {
				super(name);
				this.piatto = piatto;
			}
		}

	}

}
