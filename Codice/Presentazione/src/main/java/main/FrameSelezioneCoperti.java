/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

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

/**
 * The Class Frame per selezionare il numero dei coperti.
 */
class FrameSelezioneCoperti extends JFrame {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 6266921115114034123L;

	/** tavolo a cui si fa riferimento. */
	private final Tavolo tavolo;

	/** il label di testo. */
	private JLabel lblText;

	/** The main frame. */
	private MainFrame mainFrame;
	
	/**
	 * Instantiates a new frame selezione coperti.
	 *
	 * @param t il tavolo a cui fa riferimento il frame
	 * @param close il listener del frame
	 * @param mainFrame il main frame 
	 */
	FrameSelezioneCoperti(Tavolo t, WindowAdapter close, MainFrame mainFrame) {
		super();
		this.tavolo = t;
		this.mainFrame = mainFrame;
		setType(Type.POPUP);
		setUndecorated(false);
		setTitle("Coperti Tavolo " + t.getNome());
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
		lblText = new JLabel("" + t.getNumPostiMassimi());
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

	/**
	 * prendi il label in numero intero.
	 *
	 * @return  il valore in intero del testo all'interno del label 
	 */
	private int getIntLabel() {
		return Integer.parseInt(lblText.getText());
	}

	/**
	 * Class ActionBtnInvio.
	 */
	private class ActionBtnInvio implements ActionListener {

		/**
		 * Action performed.
		 *
		 * @param e the l'evento
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int coperti = getIntLabel();
			tavolo.setStato(StatoTavolo.OCCUPATO);
			tavolo.resocontoTavolo = new ResocontoTavolo(coperti);
			mainFrame.repaintPanelTavoli();
			dispose();
		}
	}
}
