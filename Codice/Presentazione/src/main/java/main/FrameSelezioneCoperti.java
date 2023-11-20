package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import classi.enumerativi.StatoTavolo;
import classi.tavolo.ResocontoTavolo;
import classi.tavolo.Tavolo;

class FrameSelezioneCoperti extends JFrame {

	private static final long serialVersionUID = 6266921115114034123L;

	private final Tavolo tavolo;

	private JLabel lblText;

	FrameSelezioneCoperti(Tavolo t, WindowAdapter close) {
		super();
		this.tavolo = t;
		setType(Type.POPUP);
		setUndecorated(false);
		setTitle("Coperti Tavolo " + tavolo.getNome());
		setBounds(100, 100, 500, 500);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		addWindowListener(close);

		Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();

		// Calcola le coordinate per centrare la finestra
		int x = (dimensioniSchermo.width - getWidth()) / 2;
		int y = (dimensioniSchermo.height - getHeight()) / 2;

		// Imposta la posizione della finestra
		setLocation(x, y);

		final JButton btnPiu = new JButton("+");
		btnPiu.setEnabled(false);
		JButton btnInvio = new JButton("invio");
		final JButton btnMeno = new JButton("-");
		lblText = new JLabel("" + tavolo.getNumPostiMassimi());
		lblText.setHorizontalAlignment(SwingConstants.CENTER);

		btnMeno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int coperti = Integer.parseInt(lblText.getText());
				if (coperti > 1) {
					coperti--;
					btnPiu.setEnabled(true);
				} else {
					btnMeno.setEnabled(false);
				}
				lblText.setText("" + coperti);
			}
		});

		btnPiu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int coperti = Integer.parseInt(lblText.getText());
				if (coperti < tavolo.getNumPostiMassimi()) {
					coperti++;
					btnMeno.setEnabled(true);
				} else {
					btnPiu.setEnabled(false);
				}
				lblText.setText("" + coperti);
			}
		});

		btnInvio.addActionListener(new ActionBtnInvio());

		getContentPane().setLayout(new GridLayout(4, 0, 20, 40));

		getContentPane().add(btnMeno);
		getContentPane().add(lblText);
		getContentPane().add(btnPiu);
		getContentPane().add(btnInvio);

		setVisible(true);
	}

	private int getIntLabel() {
		return Integer.parseInt(lblText.getText());
	}

	private class ActionBtnInvio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int coperti = getIntLabel();
			tavolo.setStato(StatoTavolo.OCCUPATO);
			tavolo.resocontoTavolo = new ResocontoTavolo(coperti);
			dispose();
		}
	}
}
