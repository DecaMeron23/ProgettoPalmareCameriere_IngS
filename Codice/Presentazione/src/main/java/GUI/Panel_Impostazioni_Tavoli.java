package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Classi.Tavolo;

/**
 * Classe che estende JPanel, utilizzata nelle impostazioni, in particolare
 * nelle modifiche dei tavoli
 */
public class Panel_Impostazioni_Tavoli extends JPanel {

	private static final long serialVersionUID = 2122068881007738339L;

	Tavolo[] lista_di_prova = new Tavolo[] { new Tavolo(1, 3) };

	/**
	 * Data Base
	 */
//	private ModificheDB db;
	/**
	 * bottone per aggiungere un nuovo tavolo
	 */
	private JButton btn_aggiungi;
	/**
	 * bottone per modificare un tavolo
	 */
	private JButton btn_modifica;
	/**
	 * bottone per eliminare un tavolo
	 */
	private JButton btn_elimina;

	JPanel panel_sopra;

	Tavolo tavolo_selezionato;

	JLabel lbl_tavolo_selezionato;

	public Panel_Impostazioni_Tavoli() {

		setLayout(new BorderLayout());
		panel_sopra = new JPanel(new GridLayout(0, 4, 100, 50));
		JPanel panel_sotto = new JPanel(new GridLayout(0, 3, 75, 75));

		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(panel_sopra);
		jScrollPane.setPreferredSize(new Dimension(900, 700));

		// creiamo la classe che si occupa del DB, la Jlist con tutti i tavoli
		// db = new ModificheDB();
		btn_aggiungi = new JButton("Aggiungi Tavolo");
		btn_modifica = new JButton("Modifica Tavolo");
		btn_elimina = new JButton("Elimina Tavolo");

		lbl_tavolo_selezionato = new JLabel();

		// aggiorniamo la JList
		aggiorna_list_tavoli(panel_sopra);

		// aggiungo action listener
		btn_aggiungi.addActionListener(new Action_Btn_Aggiungi_Tavolo());
		btn_elimina.addActionListener(new Action_Btn_Elimina_Tavolo());
		btn_modifica.addActionListener(new Action_Btn_Modifica_Tavolo());

		// aggiungiamo gli elementi
		panel_sotto.add(btn_aggiungi);
		panel_sotto.add(btn_modifica);
		panel_sotto.add(btn_elimina);

		// aggiungiamo i panel
		add(lbl_tavolo_selezionato, BorderLayout.NORTH);
		add(jScrollPane, BorderLayout.CENTER);
		add(panel_sotto, BorderLayout.SOUTH);
	}

	/**
	 * metodo che aggiorna i tavoli della JList
	 */
	private void aggiorna_list_tavoli(JPanel panel) {

		panel_sopra.removeAll();
		
		for (Tavolo tavolo : lista_di_prova) {
			Bottone_Tavolo btn = new Bottone_Tavolo(tavolo);
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					tavolo_selezionato = ((Bottone_Tavolo) e.getSource()).getTavolo();
					btn_modifica.setEnabled(true);
					btn_elimina.setEnabled(true);
					lbl_tavolo_selezionato.setText("Tavolo Selezionato n° " + tavolo_selezionato.getNome());
				}
			});

			setSize(new Dimension(150, 100));

			panel.add(btn);
		}

		lbl_tavolo_selezionato.setText("Tavolo Selezionato n° ");
		
		btn_modifica.setEnabled(false);
		btn_elimina.setEnabled(false);
		
		repaint();
		revalidate();

	}
	
	private void enable_frame(boolean bool) {
		JFrame frame = ((JFrame)SwingUtilities.getWindowAncestor(this));
		frame.setEnabled(bool);
		if (bool) {
			frame.toFront();
		}
		
	}

	/**
	 * Classe che implementa Action Listener, utilizzata dal bottone modifica tavolo
	 */
	private class Action_Btn_Modifica_Tavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// creiamo il frame di modifica
			new Frame_Aggiungi_Modifica_Tavolo(tavolo_selezionato, Frame_Aggiungi_Modifica_Tavolo.MODIFICA);
			enable_frame(false);
		}
	}

	/**
	 * classe che implementa Action listener utilizzata dal bottone elimina tavolo
	 */
	private class Action_Btn_Elimina_Tavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// creaimo gli action listener
			ActionListener act_si = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					// rimoviamo il tavolo TODO
					aggiorna_list_tavoli(panel_sopra);
					frame.dispose();
					enable_frame(true);
				}
			};

			ActionListener act_no = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					frame.dispose();
					enable_frame(true);
				}
			};
			
			WindowAdapter close_windAdapter = new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					enable_frame(true);
				}
			};

			// apriamo il frame che chiede la conferma
			new Frame_conferma("Sicuro di chiudere il tavolo n° " + tavolo_selezionato.getNome(), act_si,
					act_no , close_windAdapter);
			enable_frame(false);
		}
	}

	/**
	 * classe che implementa Action listener utilizzata dal bottone aggiungi tavolo
	 */
	private class Action_Btn_Aggiungi_Tavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new Frame_Aggiungi_Modifica_Tavolo(null , Frame_Aggiungi_Modifica_Tavolo.AGGIUNGI);
			enable_frame(false);
		}

	}

	/**
	 * Frame per le modifiche della lista dei tavoli
	 */
	private class Frame_Aggiungi_Modifica_Tavolo extends JFrame {

		private static final long serialVersionUID = 1L;

		public static final int AGGIUNGI = 0;
		public static final int MODIFICA = 1;


		public Frame_Aggiungi_Modifica_Tavolo(Tavolo tavolo, int tipo) {

			String titolo = (tipo == MODIFICA ? ("Modifica Tavolo n°" + tavolo.getNome()) : "Aggiungi Tavolo");

			// inizzializzazione Frame
			setTitle(titolo);
			setSize(new Dimension(300, 200));
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());

			setType(Type.POPUP);


			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					enable_frame(true);
				}
			});
						
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
			JPanel panel_principale = new JPanel(new GridLayout(0, 2, 10, 60));
			JPanel panel_pulsanti = new JPanel(new GridLayout(0, 2, 75, 75));

			// creo i label e text area
			JLabel lbl_nome_tavolo = new JLabel("Tavolo n° ");
			String str_nome_tavolo = (tipo == AGGIUNGI ? "" : "" + tavolo.getNome());
			JTextArea txt_area_nome_tavolo = new JTextArea(str_nome_tavolo);

			JLabel lbl_posti_tavolo = new JLabel("Tavolo n° ");
			String str_posti_tavolo = (tipo == AGGIUNGI ? "" : "" + tavolo.getNum_posti_massimi());
			JTextArea txt_area_posti_tavolo = new JTextArea(str_posti_tavolo);

			// creazione dei pulsanti
			JButton btn_conferma = new JButton("Conferma");
			JButton btn_annulla = new JButton("Annulla");

			// creazione listener
			ActionListener act_lis_annulla = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					enable_frame(true);
				}
			};
			// TODO
			ActionListener act_lis_conferma = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO aggiungere al DB
					dispose();
					enable_frame(true);
				}
			};

			// aggiungiamo gli action listener ai bottoni
			btn_conferma.addActionListener(act_lis_conferma);
			btn_annulla.addActionListener(act_lis_annulla);

			// aggiungiamo i componenti al panel principale
			panel_principale.add(lbl_nome_tavolo);
			panel_principale.add(txt_area_nome_tavolo);
			panel_principale.add(lbl_posti_tavolo);
			panel_principale.add(txt_area_posti_tavolo);

			// aggiungiamo i componenti al panel pulsanti
			panel_pulsanti.add(btn_conferma);
			panel_pulsanti.add(btn_annulla);

			// aggiungiamo i panel al content pane
			getContentPane().add(panel_principale, BorderLayout.CENTER);
			getContentPane().add(panel_pulsanti, BorderLayout.SOUTH);

			setAlwaysOnTop(true);
			setVisible(true);
		}
	}
}
