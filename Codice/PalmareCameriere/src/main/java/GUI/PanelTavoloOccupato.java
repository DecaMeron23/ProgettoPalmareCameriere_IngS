package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import Classi.Componente;
import Classi.Piatto;
import Classi.Tavolo;

public class PanelTavoloOccupato extends JPanel {

	private Tavolo tavolo;

	public PanelTavoloOccupato(Tavolo tavolo, List<Componente> lista_componenti) {
		super();
		this.tavolo = tavolo;

		setLayout(new BorderLayout());

		JPanel ordini = new PanelOrdini();

		JPanel panelDX = new JPanel();
		panelDX.setLayout(new BorderLayout());

		JTabbedPane tabbedCoponenti = new JTabbedPane();

		aggiungiComponenti(tabbedCoponenti, lista_componenti);
		panelDX.add(tabbedCoponenti, BorderLayout.NORTH);

		JPanel panelDX_Basso = new JPanel();
		panelDX_Basso.setLayout(new BorderLayout());
		JTextArea commenti = new JTextArea("Commenti vanno Qui!!");
		JButton conferma = new JButton("Conferma");

		panelDX_Basso.add(commenti , BorderLayout.CENTER);
		panelDX_Basso.add(conferma, BorderLayout.EAST);

		panelDX.add(panelDX_Basso);

		add(panelDX, BorderLayout.EAST);

		add(ordini, BorderLayout.WEST);

	}

	private void aggiungiComponenti(JTabbedPane tabbed, List<Componente> lista_componenti) {
		for (Componente comp : lista_componenti) {
			// creo il panel per la componente
			JPanel panel = new PanelComponente(comp);
			// aggiunto lo scrollPane alla componente
			JScrollPane scroll = new JScrollPane(panel);
			scroll.getViewport().setPreferredSize(new Dimension(1000, 700));
			// aggiungo la componente al tab
			tabbed.add(scroll, comp.getNome());

		}
	}

	class PanelOrdini extends JPanel {

		public PanelOrdini() {
			super();
			setLayout(new GridLayout(1, 0, 0, 0));
		}

	}

	class PanelComponente extends JPanel {

		public PanelComponente(Componente componente) {
			super();

			setLayout(new GridLayout(0, 1, 5, 5));
			aggiungiPiatti(componente.getLista_piatti());
		}

		void aggiungiPiatti(List<Piatto> lista_piatti) {
			for (Piatto piatto : lista_piatti) {
				
				//JLabel
				
				
				JButton btn = new JButton(piatto.getNome());
				btn.setPreferredSize(new Dimension(100, 100));
				add(btn);
			}
		}

	}

}
