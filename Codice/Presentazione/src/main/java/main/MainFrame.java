package main;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import classi.enumerativi.StatoTavolo;
import classi.menu.Componente;
import classi.tavolo.Tavolo;
import main.impostazioni.PanelImpostazioni;
import main.tavoli.BottoneTavolo;
import main.tavoli.PanelBottoniTavoli;
import main.tavoloOccupato.panel.PanelTavoloOccupato;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Container contentPane;
	// TODO from UCDetector: Field "MainFrame.btnTavoli" has 0 references
	JButton btnTavoli; // NO_UCD (unused code)
	JButton btnImpostazioni; // NO_UCD (unused code)
	// Panel_Bottoni_Tavoli panel_bottoni_tavoli;
	JPanel panelTavolo; // NO_UCD (unused code)

	private List<Tavolo> listaTavoli;
	private List<Componente> listaComponenti;

	/**
	 * Create the frame.
	 * 
	 * @param listaTavoli
	 * @param listaComponenti
	 */
	MainFrame(List<Tavolo> listaTavoli, List<Componente> listaComponenti) {

		this.listaTavoli = listaTavoli;
		this.listaComponenti = listaComponenti;

		setResizable(false);
		setTitle("Palmare Cameriere");

		contentPane = getContentPane();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setUndecorated(false);

		repaintPanelTavoli();

		JMenuBar menuBar = new BarraMenu(this);
		setJMenuBar(menuBar);

		setVisible(true);
	}

	/**
	 * Aggiorna la grafica del panel dei tavoli
	 */
	public void repaintPanelTavoli() {

		contentPane.removeAll();
		contentPane.repaint();
		contentPane.revalidate();

		JScrollPane panelBottoniTavoli = new PanelBottoniTavoli(listaTavoli, new AzioneBtnTavoli());

		contentPane.add(panelBottoniTavoli);

		panelBottoniTavoli.repaint();
		panelBottoniTavoli.revalidate();

	}

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

			new FrameSelezioneCoperti(tavolo, close);
			break;

		case OCCUPATO:
			JPanel panel = new PanelTavoloOccupato(tavolo, listaComponenti);
			contentPane.removeAll();
			contentPane.add(panel);
			contentPane.repaint();
			contentPane.revalidate();
			break;
		case DA_PULIRE:
			// creo gli action listener
			ActionListener actBtnSi = new AzioneSiFrameConferma(tavolo);
			ActionListener actBtnNo = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					contentPane.setEnabled(true);
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
			new FrameConfermaScelta("Il tavolo è pulito?", actBtnSi, actBtnNo, adapter);
			break;
		default:
			break;

		}
	}

	void openImpostazioni() {

		PanelImpostazioni panelImpostazioni = new PanelImpostazioni();
		contentPane.removeAll();
		contentPane.add(panelImpostazioni);

		repaint();
		revalidate();

	}

	private void enableFrame(boolean bool) {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
		frame.setEnabled(bool);
		if (bool) {
			frame.toFront();

		}
	}

	/**
	 * Classe per il frame conferma
	 */
	private class AzioneSiFrameConferma implements ActionListener {

		private Tavolo tavolo;

		public AzioneSiFrameConferma(final Tavolo tavolo) {
			this.tavolo = tavolo;
			repaintPanelTavoli();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			tavolo.setStato(StatoTavolo.LIBERO);
			SwingUtilities.getWindowAncestor((JButton) (e.getSource())).dispose();
			enableFrame(true);
			repaintPanelTavoli();
		}
	}

	private class AzioneBtnTavoli implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			BottoneTavolo btn_tavolo = (BottoneTavolo) e.getSource();

			Tavolo tavolo = btn_tavolo.getTavolo();
			openTavolo(tavolo);
			btn_tavolo.aggiornaStato();
		}

	}
}
