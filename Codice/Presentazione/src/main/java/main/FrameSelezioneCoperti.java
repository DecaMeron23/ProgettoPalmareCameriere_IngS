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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import classi.tavolo.Tavolo;
import logico.Logico;

/**
 * The Class Frame per selezionare il numero dei coperti.
 */
class FrameSelezioneCoperti extends JDialog {

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
	 * @param t         il tavolo a cui fa riferimento il frame
	 * @param close     il listener del frame
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
					if(coperti <= 1) {
						btnMeno.setEnabled(false);
						btnPiu.setEnabled(true);
					}else {
						btnMeno.setEnabled(true);
						btnPiu.setEnabled(true);
					}
				lblText.setText("" + coperti);
			}
		}});

		btnPiu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int coperti = Integer.parseInt(lblText.getText());
				if (coperti < tavolo.getNumPostiMassimi()) {
					coperti++;
					if(coperti >= tavolo.getNumPostiMassimi()) {
						btnPiu.setEnabled(false);
						btnMeno.setEnabled(true);
					}else {
						btnPiu.setEnabled(true);
						btnMeno.setEnabled(true);
					}
				lblText.setText("" + coperti);
			}
		}});

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
	 * @return il valore in intero del testo all'interno del label
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
			try {
				Logico.occupaTavolo(tavolo, coperti);
			} catch (Exception e1) {
				System.err.println(e1.getMessage());
			}
			mainFrame.paintPanelTavoli();
			dispose();
		}
	}
}
