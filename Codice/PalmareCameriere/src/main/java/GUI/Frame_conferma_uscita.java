package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_conferma_uscita extends JFrame {

	private static final long serialVersionUID = 1L;

	public Frame_conferma_uscita(String string, ActionListener listener_btn_si, ActionListener listener_btn_no) {

		setTitle("Conferma Uscita");
		setSize(new Dimension(300	, 200));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false);

		// creo i panel
		JPanel panel_principale = new JPanel(new BorderLayout());
		JPanel panel_pulsanti = new JPanel(new GridLayout(0, 2, 75, 75));

		// creo il label per il testo
		JLabel lbl_testo = new JLabel(string);
		lbl_testo.setHorizontalAlignment(SwingConstants.CENTER);

		
		Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Calcola le coordinate per centrare la finestra
        int x = (dimensioniSchermo.width - getWidth()) / 2;
        int y = (dimensioniSchermo.height - getHeight()) / 2;

        // Imposta la posizione della finestra
        setLocation(x, y);
		
		// creo i pulsanti
		JButton btn_si = new JButton("si");
		JButton btn_no = new JButton("no");

		// aggiungo action listener
		btn_si.addActionListener(listener_btn_si);
		btn_no.addActionListener(listener_btn_no);

		//aggiungo i pulsanti
		panel_pulsanti.add(btn_si); 	
		panel_pulsanti.add(btn_no); 
		
		
		// aggiungo il panel pulsanti al panel principale
		panel_principale.add(lbl_testo , BorderLayout.CENTER);
		panel_principale.add(panel_pulsanti, BorderLayout.SOUTH);

		// aggiungo al content pane
		getContentPane().add(panel_principale);
		setVisible(true);

	}
}
