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
	PanelTavoli panel_tavoli;
	JPanel panel_tavolo;

	private JScrollPane scrollPane_tavoli;
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

		btn_tavoli = new JButton("Tavoli");
		btn_impostazioni = new JButton("Impostazioni");

		btn_tavoli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint_panel_tavoli();
			}
		});

		repaint_panel_tavoli();

		JMenuBar menuBar = new BarraMenu(btn_tavoli, btn_impostazioni);
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
		
		panel_tavoli = new PanelTavoli(lista_tavoli, new Azione_Btn_Tavoli());

		scrollPane_tavoli = new JScrollPane(panel_tavoli);
		scrollPane_tavoli.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane_tavoli.getViewport().setPreferredSize(new Dimension(1500, 800));
		content_pane.add(scrollPane_tavoli);

		panel_tavoli.repaint();
		panel_tavoli.revalidate();

	}

	void openTavolo(Tavolo tavolo) {
		switch (tavolo.getStato()) {
		case LIBERO:
			attivaGUI(false);
			
			WindowAdapter close = new WindowAdapter() {
				
				@Override
				public void windowClosed(WindowEvent e) {
					attivaGUI(true);
				}
			};
			
			Frame_Tavolo_Libero_Coperti frameCoperti = new Frame_Tavolo_Libero_Coperti(tavolo , close);
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

	private void attivaGUI(boolean bool) {
		this.setEnabled(bool);
		this.toFront();

	}

	public class Azione_Si_Frame_conferma implements ActionListener {

		private Tavolo tavolo;

		public Azione_Si_Frame_conferma(final Tavolo tavolo) {
			this.tavolo = tavolo;
			repaint_panel_tavoli();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			tavolo.setStato(Stato_Tavolo.LIBERO);
			SwingUtilities.getWindowAncestor((JButton) (e.getSource())).dispose();
			content_pane.setEnabled(true);
			repaint_panel_tavoli();
		}
	}

	public class Azione_Btn_Tavoli implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Bottone_tavolo btn_tavolo = (Bottone_tavolo) e.getSource();

			Tavolo tavolo = btn_tavolo.getTavolo();
			openTavolo(tavolo);
			btn_tavolo.aggiornaStato();
		}

	}

	public class ListenerFrameCoperti extends WindowAdapter {

		@Override
		public void windowClosed(WindowEvent e) {
			attivaGUI(true);
			Frame_Tavolo_Libero_Coperti frame = (Frame_Tavolo_Libero_Coperti) e.getSource();
			Tavolo tavolo = frame.getTavolo();
			openTavolo(tavolo);
		}
	}

}
