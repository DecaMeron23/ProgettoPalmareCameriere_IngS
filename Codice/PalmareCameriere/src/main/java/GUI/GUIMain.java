package GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Classi.Componente;
import Classi.Tavolo;

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
		setBounds(100, 100, 500, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		btn_tavoli = new JButton("Tavoli");
		btn_impostazioni = new JButton("Impostazioni");

		btn_tavoli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				open_panel_tavoli();
			}
		});
		
		open_panel_tavoli();
		
		JMenuBar menuBar = new BarraMenu(btn_tavoli, btn_impostazioni);
		setJMenuBar(menuBar);

				setVisible(true);
	}

	protected void open_panel_tavoli() {	
		
		content_pane.removeAll();
		
		panel_tavoli = new PanelTavoli(lista_tavoli, new Azione_Btn_Tavoli());

		scrollPane_tavoli = new JScrollPane(panel_tavoli);

		scrollPane_tavoli.getViewport().setPreferredSize(new Dimension(1500, 800));
		content_pane.add(scrollPane_tavoli);
		
		content_pane.repaint();
		content_pane.revalidate();
	
	}

	void openTavolo(Tavolo tavolo) {
		// setTitle("Tavolo " + tavolo.getNome());
		switch (tavolo.getStato()) {
		case LIBERO:
			attivaGUI(false);
			FrameTavoloLiberoCoperti frameCoperti = new FrameTavoloLiberoCoperti(tavolo);
			frameCoperti.addWindowListener(new ListenerFrameCoperti());
			break;
		case OCCUPATO:
			JPanel panel = new PanelTavoloOccupato(tavolo, lista_componenti);
			content_pane.removeAll();
			content_pane.add(panel);
			content_pane.repaint();
			content_pane.revalidate();
			break;
		/*
		 * case DA_PULIRE: panel_tavolo = new PanelTavoloLibero(); break;
		 */
		default:
			break;

		}
	}

	private void attivaGUI(boolean b) {
		this.setEnabled(b);
		this.toFront();

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

	public class ListenerFrameCoperti implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			attivaGUI(true);
			FrameTavoloLiberoCoperti frame = (FrameTavoloLiberoCoperti) e.getSource();
			Tavolo tavolo = frame.getTavolo();
			openTavolo(tavolo);
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
