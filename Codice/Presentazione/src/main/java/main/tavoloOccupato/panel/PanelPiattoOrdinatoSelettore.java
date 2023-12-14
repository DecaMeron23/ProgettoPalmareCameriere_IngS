package main.tavoloOccupato.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import classi.ordine.PiattoOrdinato;

class PanelPiattoOrdinatoSelettore extends JPanel {

	private static final long serialVersionUID = 4372601481061137751L;
	private PiattoOrdinato piattoOrdinato;
	private JLabel lblNomePiatto;
	private JLabel lblNumeroOrdini;
	private JButton btnMeno;
	private JButton btnPiu;

	PanelPiattoOrdinatoSelettore(PiattoOrdinato piattoOrdinato) {

		this.piattoOrdinato = piattoOrdinato;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		PanelUp panelSopra = new PanelUp(piattoOrdinato);
		PanelDown panelSotto = new PanelDown(piattoOrdinato);

		int altezza = panelSopra.getPreferredSize().height + panelSotto.getPreferredSize().height ;
		
		setMaximumSize(new Dimension(500 , altezza));

		add(panelSopra);
		add(panelSotto);
	}

	private class ActionListenerBottonePiu implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int quantita = piattoOrdinato.getQuantita();
			piattoOrdinato.setQuantita(quantita++);
			lblNumeroOrdini.setText("" + piattoOrdinato.getQuantita());
		}

	}

	private class ActionListenerBottoneMeno implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int quantita = piattoOrdinato.getQuantita();
			piattoOrdinato.setQuantita(quantita--);
			lblNumeroOrdini.setText("" + piattoOrdinato.getQuantita());
		}
	}

	private class PanelUp extends JPanel {

		private static final long serialVersionUID = -131067429834709115L;

		public PanelUp(PiattoOrdinato piatto_ordinato) {
			lblNomePiatto = new JLabel(piatto_ordinato.getPiatto().getNome());
			btnMeno = new JButton("-");
			lblNumeroOrdini = new JLabel(piatto_ordinato.getQuantita() + "");
			btnPiu = new JButton("+");
			lblNomePiatto.setPreferredSize(new Dimension(350, 30));
			lblNumeroOrdini.setPreferredSize(new Dimension(30, 30));

			// modifica allineamento
			lblNumeroOrdini.setHorizontalAlignment(SwingConstants.CENTER);

			// add Action Listener
			btnMeno.addActionListener(new ActionListenerBottoneMeno());
			btnPiu.addActionListener(new ActionListenerBottonePiu());

			add(lblNomePiatto);
			add(btnMeno);
			add(lblNumeroOrdini);
			add(btnPiu);
		}

	}

	private class PanelDown extends JPanel {

	
		private static final long serialVersionUID = 2888129150603015263L;

		public PanelDown(PiattoOrdinato piatto_ordinato) {
			setLayout(new BorderLayout());
			String txtCommento = "";
			if (!piatto_ordinato.getCommento().isBlank()) {
				txtCommento = "<html>Commenti: " + piatto_ordinato.getCommento() + "</html>";
			}
			JLabel commento = new JLabel(txtCommento);
	        // Imposta il wrap del testo
	        commento.setHorizontalAlignment(SwingConstants.LEFT);
			add(commento, BorderLayout.CENTER);

		}

	}
}
