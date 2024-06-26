/**
 *  @author Benedetta Vitale & Emilio Meroni
 */

package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import classi.tavolo.Tavolo;
import logico.Logico;
import main.impostazioni.PanelImpostazioni;
import main.tavoli.BottoneTavolo;
import main.tavoli.PanelBottoniTavoli;
import main.tavoloOccupato.panel.PanelTavoloOccupato;

/**
 * Class MainFrame, il frame principale.
 */
public class MainFrame extends JFrame {

	/** il serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** il main panel */
	private JPanel mainPanel;

	/** The btn tavoli. */
	JButton btnTavoli; 

	/** The btn impostazioni. */
	JButton btnImpostazioni;

	/** The panel tavolo. */
	// Panel_Bottoni_Tavoli panel_bottoni_tavoli;
	JPanel panelTavolo;
	
	/**
	 * costruttore del frame.
	 *
	 * @param listaTavoli     la lista dei tavoli
	 * @param listaComponenti la lista dei componenti
	 */
	MainFrame() {
		
		setResizable(false);
		setTitle("Palmare Cameriere");

		mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setUndecorated(true);

		paintPanelTavoli();

		JMenuBar menuBar = new BarraMenu(this);
		setJMenuBar(menuBar);

		setVisible(true);
	}

	/**
	 * Aggiorna la grafica del panel con tutti i pulsanti dei tavoli.
	 */
	public void paintPanelTavoli() {

		mainPanel.removeAll();

		JScrollPane panelBottoniTavoli = new PanelBottoniTavoli(new AzioneBtnTavoli());

		mainPanel.add(panelBottoniTavoli);

		mainPanel.repaint();
		mainPanel.revalidate();

	}

	/**
	 * Metodo che apre le diverse opzioni per il tavolo in base allo stato.
	 *
	 * @param tavolo il a cui ci stiamo riferendo
	 */
	private void openTavolo(Tavolo tavolo) {
		switch (tavolo.getStato()) {
		case LIBERO:
			enableFrame(false);
			WindowAdapter close = new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
					enableFrame(true);
				}
			};

			new FrameSelezioneCoperti(tavolo, close, this);
			break;

		case OCCUPATO:
			PanelTavoloOccupato panel = new PanelTavoloOccupato(tavolo , this);
			mainPanel.removeAll();
			mainPanel.add(panel);
			mainPanel.repaint();
			mainPanel.revalidate();
			break;
		case DA_PULIRE:
			// creo gli action listener
			ActionListener actBtnSi = new AzioneSiFrameConferma(tavolo);
			ActionListener actBtnNo = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mainPanel.setEnabled(true);
					SwingUtilities.getWindowAncestor((JButton) e.getSource()).dispose();
				}
			};
			WindowAdapter adapter = new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
					enableFrame(true);
				}
			};
			// disabilito il frame
			enableFrame(false);
			new FrameConfermaScelta("Conferma Tavolo Pulito" , "Il tavolo è pulito?", actBtnSi, actBtnNo, adapter);
			break;
		default:
			break;

		}
	}

	/**
	 * apre la parte dedicata alle impostazioni.
	 */
	void openImpostazioni() {

		PanelImpostazioni panelImpostazioni = new PanelImpostazioni();
		mainPanel.removeAll();
		mainPanel.add(panelImpostazioni);

		repaint();
		revalidate();

	}

	/**
	 * attiva da .
	 *
	 * @param bool il valore boleano per decidere se attivare il frame o no
	 */
	private void enableFrame(boolean bool) {
		setEnabled(bool);
		if (bool) {
			toFront();

		}
	}

	/**
	 * Classe per il frame conferma.
	 */
	private class AzioneSiFrameConferma implements ActionListener {

		/** The tavolo. */
		private Tavolo tavolo;

		/**
		 * Instantiates a new azione si frame conferma.
		 *
		 * @param tavolo the tavolo
		 */
		public AzioneSiFrameConferma(final Tavolo tavolo) {
			this.tavolo = tavolo;
			paintPanelTavoli();
		}

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Logico.tavoloPulito(tavolo);
			} catch (Exception e1) {
				System.err.println(e1.getMessage());
			}
			SwingUtilities.getWindowAncestor((JButton) (e.getSource())).dispose();
			enableFrame(true);
			paintPanelTavoli();
		}
	}

	/**
	 * The Class AzioneBtnTavoli.
	 */
	private class AzioneBtnTavoli implements ActionListener {

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			BottoneTavolo btnTavolo = (BottoneTavolo) e.getSource();

			Tavolo tavolo = btnTavolo.getTavolo();
			openTavolo(tavolo);
			btnTavolo.aggiornaStato();
		}
	}
}
