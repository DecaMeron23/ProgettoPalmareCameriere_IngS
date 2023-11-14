package GUI;

import java.awt.Container;
import java.awt.Dimension;
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

import Classi.Componente;
import Classi.Tavolo;
import Classi.Enum.Stato_Tavolo;

public class GUIMain extends JFrame {

	private static final long serialVersionUID = 1L;
	Container content_pane;
	JButton btn_tavoli;
	JButton btn_impostazioni;
	// Panel_Bottoni_Tavoli panel_bottoni_tavoli;
	JPanel panel_tavolo;

	private List<Tavolo> lista_tavoli;
	private List<Componente> lista_componenti;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { GUIMain frame = new
	 * GUIMain(lista_tavoli); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 * 
	 * @param lista_tavoli
	 * @param menu
	 */
	public GUIMain(List<Tavolo> lista_tavoli, List<Componente> lista_componenti) {

		this.lista_tavoli = lista_tavoli;
		this.lista_componenti = lista_componenti;

		setResizable(false);
		setTitle("Palmare Cameriere");

		content_pane = getContentPane();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		repaint_panel_tavoli();

		JMenuBar menuBar = new BarraMenu(this);
		setJMenuBar(menuBar);

		setVisible(true);
	}

	/**
	 * Aggiorna la grafica del panel dei tavoli
	 */
	public void repaint_panel_tavoli() {

		content_pane.removeAll();
		content_pane.repaint();
		content_pane.revalidate();

		JScrollPane panel_bottoni_tavoli = new Panel_Bottoni_Tavoli(lista_tavoli, new Azione_Btn_Tavoli());

		content_pane.add(panel_bottoni_tavoli);

		panel_bottoni_tavoli.repaint();
		panel_bottoni_tavoli.revalidate();

	}

	void openTavolo(Tavolo tavolo) {
		switch (tavolo.getStato()) {
		case LIBERO:
			content_pane.setEnabled(false);
			WindowAdapter close = new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
					content_pane.setEnabled(true);
				}
			};

			Frame_Tavolo_Libero_Coperti frameCoperti = new Frame_Tavolo_Libero_Coperti(tavolo, close);
			frameCoperti.addWindowListener(new ListenerFrameCoperti());
			break;
		case OCCUPATO:
			JPanel panel = new Panel_Tavolo_Occupato(tavolo, lista_componenti);
			content_pane.removeAll();
			content_pane.add(panel);
			content_pane.repaint();
			content_pane.revalidate();
			break;
		case DA_PULIRE:
			// creo gli action listener
			ActionListener act_btn_si = new Azione_Si_Frame_conferma(tavolo);
			ActionListener act_btn_no = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					content_pane.setEnabled(true);
					SwingUtilities.getWindowAncestor((JButton) e.getSource()).dispose();
				}
			};

			// disabilito il frame
			content_pane.setEnabled(false);
			new Frame_conferma("Il tavolo è pulito?", act_btn_si, act_btn_no);
			break;
		default:
			break;

		}
	}
	
	public void open_impostazioni() {

		Panel_Impostazioni panel_impostazioni = new Panel_Impostazioni();
		content_pane.removeAll();
		content_pane.add(panel_impostazioni);
		
		repaint();
		revalidate();
		
	}

	private class Azione_Si_Frame_conferma implements ActionListener {

		private Tavolo tavolo;

		public Azione_Si_Frame_conferma(final Tavolo tavolo) {
			this.tavolo = tavolo;
			repaint_panel_tavoli();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			tavolo.setStato(Stato_Tavolo.LIBERO);
			SwingUtilities.getWindowAncestor((JButton) (e.getSource())).dispose();
			setEnabled(true);
			repaint_panel_tavoli();
		}
	}

	private class Azione_Btn_Tavoli implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Bottone_tavolo btn_tavolo = (Bottone_tavolo) e.getSource();

			Tavolo tavolo = btn_tavolo.getTavolo();
			openTavolo(tavolo);
			btn_tavolo.aggiornaStato();
		}

	}

	private class ListenerFrameCoperti extends WindowAdapter {

		@Override
		public void windowClosed(WindowEvent e) {
			content_pane.setEnabled(true);
			Frame_Tavolo_Libero_Coperti frame = (Frame_Tavolo_Libero_Coperti) e.getSource();
			Tavolo tavolo = frame.getTavolo();
			openTavolo(tavolo);
		}
	}

}
