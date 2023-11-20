package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import classi.tavolo.Tavolo;

public class FrameConfermaScelta extends JFrame {

	private static final long serialVersionUID = 1L;

	public FrameConfermaScelta(String string, ActionListener listenerBtnSi, ActionListener listenerBtnNo , WindowAdapter winAdapter) {

		setTitle("Conferma Uscita");
		setSize(new Dimension(300, 200));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setType(Type.POPUP);

		addWindowListener(winAdapter);
		

		setUndecorated(false);
		setResizable(false);
		setAlwaysOnTop(true);

		Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();

		// Calcola le coordinate per centrare la finestra
		int x = (dimensioniSchermo.width - getWidth()) / 2;
		int y = (dimensioniSchermo.height - getHeight()) / 2;

		// Imposta la posizione della finestra
		setLocation(x, y);

		// creo i panel
		JPanel panelPrincipale = new JPanel(new BorderLayout());
		JPanel panelPulsanti = new JPanel(new GridLayout(0, 2, 75, 75));

		// creo il label per il testo
		JLabel lblTesto = new JLabel(string);
		lblTesto.setHorizontalAlignment(SwingConstants.CENTER);

		// creo i pulsanti
		JButton btnsi = new JButton("si");
		JButton btnNo = new JButton("no");

		// aggiungo action listener
		btnsi.addActionListener(listenerBtnSi);
		btnNo.addActionListener(listenerBtnNo);

		// aggiungo i pulsanti
		panelPulsanti.add(btnsi);
		panelPulsanti.add(btnNo);

		// aggiungo il panel pulsanti al panel principale
		panelPrincipale.add(lblTesto, BorderLayout.CENTER);
		panelPrincipale.add(panelPulsanti, BorderLayout.SOUTH);

		// aggiungo al content pane
		getContentPane().add(panelPrincipale);
		setVisible(true);

	}

}
