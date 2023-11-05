package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Classi.Tavolo;
import Classi.Enum.Stato_Tavolo;

public class FrameTavoloLiberoCoperti extends JFrame {
	
	private final Tavolo tavolo;

	public FrameTavoloLiberoCoperti(Tavolo t) {
		super();
		this.tavolo = t;		
		setType(Type.POPUP);
		setAlwaysOnTop(true);
		setTitle("Coperti Tavolo " + tavolo.getNome());
		setBounds(100, 100, 500, 500);

		final JButton btn_piu = new JButton("+");
		btn_piu.setEnabled(false);
		JButton btn_invio = new JButton("invio");
		final JButton btn_meno = new JButton("-");
		final JLabel lbl_text = new JLabel("" + tavolo.getNum_posti_massimi());
		lbl_text.setHorizontalAlignment(SwingConstants.CENTER);

		btn_meno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int coperti = Integer.parseInt(lbl_text.getText());
				if (coperti > 1) {
					coperti--;
					btn_piu.setEnabled(true);
				} else {
					btn_meno.setEnabled(false);
				}
				lbl_text.setText("" + coperti);
			}
		});

		btn_piu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int coperti = Integer.parseInt(lbl_text.getText());
				if (coperti < tavolo.getNum_posti_massimi()) {
					coperti++;
					btn_meno.setEnabled(true);
				} else {
					btn_piu.setEnabled(false);
				}
				lbl_text.setText("" + coperti);
			}
		});

		btn_invio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int coperti = Integer.parseInt(lbl_text.getText());
				//tavolo.resoconto.setNumero_coperti(coperti);
				tavolo.setStato(Stato_Tavolo.OCCUPATO);
				dispose();
			}
		});

		getContentPane().setLayout(new GridLayout(4, 0, 20, 40));

		getContentPane().add(btn_meno);
		getContentPane().add(lbl_text);
		getContentPane().add(btn_piu);
		getContentPane().add(btn_invio);

		setVisible(true);
	}

	public Tavolo getTavolo() {
		return tavolo;
		
	}
}
