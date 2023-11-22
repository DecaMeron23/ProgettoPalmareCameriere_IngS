/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Class Frame per la conferma della scelta.
 */
public class FrameConfermaScelta extends JFrame {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * costruttore frame conferma scelta.
	 *
	 * @param titolo        il titolo del frame
	 * @param string        la stringa che compare in centro allo schermo
	 * @param listenerBtnSi il listener per il bottone si
	 * @param listenerBtnNo il listener per il bottone no
	 * @param winAdapter    il listener per la chiusura del frame
	 */
	public FrameConfermaScelta(String titolo, String string, ActionListener listenerBtnSi, ActionListener listenerBtnNo,
			WindowAdapter winAdapter) {

		setTitle(titolo);
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
		JButton btnSi = new JButton("si");
		JButton btnNo = new JButton("no");

		// aggiungo action listener
		btnSi.addActionListener(listenerBtnSi);
		btnNo.addActionListener(listenerBtnNo);

		// aggiungo i pulsanti
		panelPulsanti.add(btnSi);
		panelPulsanti.add(btnNo);

		// aggiungo il panel pulsanti al panel principale
		panelPrincipale.add(lblTesto, BorderLayout.CENTER);
		panelPrincipale.add(panelPulsanti, BorderLayout.SOUTH);

		// aggiungo al content pane
		getContentPane().add(panelPrincipale);
		setVisible(true);

	}

}
