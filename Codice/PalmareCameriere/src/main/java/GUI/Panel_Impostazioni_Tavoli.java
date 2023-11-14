package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Classi.Tavolo;

/**
 * Classe che estende JPanel, utilizzata nelle impostazioni, in particolare
 * nelle modifiche dei tavoli
 */
public class Panel_Impostazioni_Tavoli extends JPanel {

	private static final long serialVersionUID = 2122068881007738339L;

	Tavolo[] lista_di_prova = new Tavolo[] { new Tavolo(1, 3), new Tavolo(3, 3), new Tavolo(2, 3), new Tavolo(7, 3),
			new Tavolo(5, 3) };

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
	/**
	 * JListi dei Tavoli
	 */
	private JList<Tavolo> jlist_tavoli;

	public Panel_Impostazioni_Tavoli() {

		setLayout(new BorderLayout());

		JPanel panel_sopra = new JPanel();
		JPanel panel_sotto = new JPanel(new GridLayout(0, 3, 75, 75));

		// creiamo la classe che si occupa del DB, la Jlist con tutti i tavoli
		// db = new ModificheDB();
		jlist_tavoli = new JList<Tavolo>();
		btn_aggiungi = new JButton("Aggiungi Tavolo");
		btn_modifica = new JButton("Modifica Tavolo");
		btn_elimina = new JButton("Elimina Tavolo");

		//jlist_tavoli.setPreferredSize(new Dimension(800, 750));
		jlist_tavoli.setCellRenderer(new CustomListRenderer(32));

		// aggiorniamo la JList
		aggiorna_list_tavoli();

		// aggiungo action listener
		btn_aggiungi.addActionListener(new Action_Btn_Aggiungi_Tavolo());
		btn_elimina.addActionListener(new Action_Btn_Elimina_Tavolo());
		btn_modifica.addActionListener(new Action_Btn_Modifica_Tavolo());
		jlist_tavoli.addListSelectionListener(new Action_JList());

		// aggiungiamo gli elementi
		panel_sopra.add(jlist_tavoli);
		panel_sotto.add(btn_aggiungi);
		panel_sotto.add(btn_modifica);
		panel_sotto.add(btn_elimina);

		// aggiungiamo i panel
		add(panel_sopra, BorderLayout.CENTER);
		add(panel_sotto, BorderLayout.SOUTH);
	}

	/**
	 * metodo che aggiorna i tavoli della JList
	 */
	private void aggiorna_list_tavoli() {
		// prendiamo tutti i tavoli
		// List<Tavolo> lista_tavoli = db.get_tavoli(ModificheDB.ALL);
		// creiamo il modello per la JList
		DefaultListModel<Tavolo> list_model = new DefaultListModel<>();
		Tavolo[] lista_tavoli = lista_di_prova;
		// aggiungiamo i tavoli al Modello
		for (Tavolo tavolo : lista_tavoli) {
			list_model.addElement(tavolo);
		}
		// aggiungiamo il modello alla Jlist
		jlist_tavoli.setModel(list_model);

		btn_modifica.setEnabled(false);
		btn_elimina.setEnabled(false);

	}

	/**
	 * Classe che implementa Action Listener, utilizzata dal bottone aggiungi tavolo
	 */
	private class Action_Btn_Aggiungi_Tavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Tavolo tavolo = jlist_tavoli.getSelectedValue();
			Frame_Aggiungi_Modifica_Tavolo frame = new Frame_Aggiungi_Modifica_Tavolo(tavolo,
					Frame_Aggiungi_Modifica_Tavolo.AGGIUNGI);
		}

	}

	/**
	 * Classe che implementa Action Listener, utilizzata dal bottone modifica tavolo
	 */
	private class Action_Btn_Modifica_Tavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// get tavolo da lista

		}
	}

	/**
	 * classe che implementa Action listener utilizzata dal bottone elimina tavolo
	 */
	private class Action_Btn_Elimina_Tavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Tavolo tavolo = jlist_tavoli.getSelectedValue();

			// creaimo gli action listener
			ActionListener act_si = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					// rimoviamo il tavolo
					aggiorna_list_tavoli();
					frame.dispose();
				}
			};

			ActionListener act_no = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					frame.dispose();
				}
			};

			// apriamo il frame che chiede la conferma
			Frame_conferma frame = new Frame_conferma("Sicuro di chiudere il tavolo n° " + tavolo.getNome(), act_si,
					act_no);
		}
	}

	private class Action_JList implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				// Abilita il pulsante quando un elemento è selezionato
				boolean bol = jlist_tavoli.getSelectedIndex() != -1;
				btn_modifica.setEnabled(bol);
				btn_elimina.setEnabled(bol);
			}
		}

	}

	/**
	 * Frame per le modifiche della lista dei tavoli
	 */
	private class Frame_Aggiungi_Modifica_Tavolo extends JFrame {

		private static final long serialVersionUID = 1L;

		public static final int AGGIUNGI = 0;
		public static final int MODIFICA = 0;

		private final Tavolo tavolo;

		public Frame_Aggiungi_Modifica_Tavolo(Tavolo tavolo, int tipo) {

			this.tavolo = tavolo;

			String titolo = (tipo == AGGIUNGI ? ("Modifica Tavolo n°" + tavolo.getNome()) : "Aggiungi Tavolo");

			// inizzializzazione Frame
			setTitle(titolo);
			setSize(new Dimension(300, 200));
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());

			setType(Type.POPUP);

			setUndecorated(true);
			setResizable(false);
			setAlwaysOnTop(true);

			// creo i panel
			JPanel panel_principale = new JPanel(new GridLayout(0, 2, 10, 10));
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
				}
			};
			// TODO
			ActionListener act_lis_conferma = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO aggiungere al DB
					dispose();

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
			getContentPane().add(panel_principale, BorderLayout.SOUTH);

		}
	}

	private class CustomListRenderer extends DefaultListCellRenderer {
		private int fontSize;

		public CustomListRenderer(int fontSize) {
			this.fontSize = fontSize;
			setHorizontalAlignment(SwingConstants.CENTER); // Imposta l'allineamento al centro
	        setBorder(new EmptyBorder(5, 5, 5, 5)); // Aggiunge un margine per una migliore presentazione
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (c instanceof JLabel) {
				JLabel label = (JLabel) c;
				label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), fontSize));
			}

			return c;
		}

	}
}
