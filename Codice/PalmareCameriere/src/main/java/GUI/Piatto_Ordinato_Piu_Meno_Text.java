package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Classi.PiattoOrdinato;

public class Piatto_Ordinato_Piu_Meno_Text extends JPanel {

	private static final long serialVersionUID = 4372601481061137751L;
	PiattoOrdinato piatto_ordinato;
	JLabel lbl_nome_piatto;
	JLabel lbl_numero_ordini;
	JButton btn_meno;
	JButton btn_piu;

	public Piatto_Ordinato_Piu_Meno_Text(PiattoOrdinato piatto_ordinato) {

		this.piatto_ordinato = piatto_ordinato;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Panel_Up panel_sopra = new Panel_Up(piatto_ordinato);
		Panel_Down panel_sotto = new Panel_Down(piatto_ordinato);

		int altezza = panel_sopra.getPreferredSize().height + panel_sotto.getPreferredSize().height ;
		
		setMaximumSize(new Dimension(500 , altezza));

		add(panel_sopra);
		add(panel_sotto);
	}

	class ActionListener_Bottone_Piu implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			piatto_ordinato.incrementa_quantità();
			lbl_numero_ordini.setText("" + piatto_ordinato.getQuantita());
		}

	}

	class ActionListener_Bottone_Meno implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			piatto_ordinato.decrementa_quantità();
			lbl_numero_ordini.setText("" + piatto_ordinato.getQuantita());
		}
	}

	class Panel_Up extends JPanel {

		private static final long serialVersionUID = -131067429834709115L;

		public Panel_Up(PiattoOrdinato piatto_ordinato) {
			lbl_nome_piatto = new JLabel(piatto_ordinato.getPiatto().getNome());
			btn_meno = new JButton("-");
			lbl_numero_ordini = new JLabel(piatto_ordinato.getQuantita() + "");
			btn_piu = new JButton("+");
			lbl_nome_piatto.setPreferredSize(new Dimension(350, 30));
			lbl_numero_ordini.setPreferredSize(new Dimension(30, 30));

			// modifica allineamento
			lbl_numero_ordini.setHorizontalAlignment(SwingConstants.CENTER);

			// add Action Listener
			btn_meno.addActionListener(new ActionListener_Bottone_Meno());
			btn_piu.addActionListener(new ActionListener_Bottone_Piu());

			add(lbl_nome_piatto);
			add(btn_meno);
			add(lbl_numero_ordini);
			add(btn_piu);
		}

	}

	class Panel_Down extends JPanel {

	
		private static final long serialVersionUID = 2888129150603015263L;

		public Panel_Down(PiattoOrdinato piatto_ordinato) {
			setLayout(new BorderLayout());
			String txt_commento = "";
			if (!piatto_ordinato.getCommento().isBlank()) {
				txt_commento = "<html>Commenti: " + piatto_ordinato.getCommento() + "</html>";
			}
			JLabel commento = new JLabel(txt_commento);
	        // Imposta il wrap del testo
	        commento.setHorizontalAlignment(SwingConstants.LEFT);
			add(commento, BorderLayout.CENTER);

		}

	}
}
