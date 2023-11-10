package GUI;

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

import Classi.ResocontoTavolo;
import Classi.Tavolo;
import Classi.Enum.Stato_Tavolo;

public class Frame_Tavolo_Libero_Coperti extends JFrame {
	
	private static final long serialVersionUID = 6266921115114034123L;
	
	private final Tavolo tavolo;

	private JLabel lbl_text;

	public Frame_Tavolo_Libero_Coperti(Tavolo t, WindowAdapter close) {
		super();
		this.tavolo = t;		
		setType(Type.POPUP);
		setUndecorated(false);
		setAlwaysOnTop(true);
		setTitle("Coperti Tavolo " + tavolo.getNome());
		setBounds(100, 100, 500, 500);
		
		addWindowListener(close);
		
		Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Calcola le coordinate per centrare la finestra
        int x = (dimensioniSchermo.width - getWidth()) / 2;
        int y = (dimensioniSchermo.height - getHeight()) / 2;

        // Imposta la posizione della finestra
        setLocation(x, y);

		
		final JButton btn_piu = new JButton("+");
		btn_piu.setEnabled(false);
		JButton btn_invio = new JButton("invio");
		final JButton btn_meno = new JButton("-");
		lbl_text = new JLabel("" + tavolo.getNum_posti_massimi());
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

		btn_invio.addActionListener(new Action_Btn_Invio());

		getContentPane().setLayout(new GridLayout(4, 0, 20, 40));

		getContentPane().add(btn_meno);
		getContentPane().add(lbl_text);
		getContentPane().add(btn_piu);
		getContentPane().add(btn_invio);

		setVisible(true);
	}

	Tavolo getTavolo() {
		return tavolo;
		
	}
	
	private int get_int_label() {
		return Integer.parseInt(lbl_text.getText());
	}
	
	class Action_Btn_Invio implements ActionListener{
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int coperti = get_int_label();
			tavolo.setStato(Stato_Tavolo.OCCUPATO);
			tavolo.resoconto_tavolo = new ResocontoTavolo(coperti);
			dispose();
		}
	}
}
