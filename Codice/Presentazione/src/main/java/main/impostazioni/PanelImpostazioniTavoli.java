package main.impostazioni;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import classi.dataBase.DataService;
import classi.tavolo.Tavolo;
import main.FrameConfermaScelta;
import main.tavoli.BottoneTavolo;

/**
 * Classe che estende JPanel, utilizzata nelle impostazioni, in particolare
 * nelle modifiche dei tavoli
 */
class PanelImpostazioniTavoli extends JPanel {

	private static final long serialVersionUID = 2122068881007738339L;

	/**
	 * Modello dei dati
	 */
	//private InterfaceModel model;

	/**
	 * Data Base
	 */
//	private ModificheDB db;
	/**
	 * bottone per aggiungere un nuovo tavolo
	 */
	private JButton btnAggiungi;
	/**
	 * bottone per modificare un tavolo
	 */
	private JButton btnModifica;
	/**
	 * bottone per eliminare un tavolo
	 */
	private JButton btnElimina;

	private JPanel panelSopra;

	private Tavolo tavoloSelezionato;

	private JLabel lblTavoloSelezionato;

	public PanelImpostazioniTavoli() {

		setLayout(new BorderLayout());
		panelSopra = new JPanel(new GridLayout(0, 4, 100, 50));
		JPanel panelSotto = new JPanel(new GridLayout(0, 3, 75, 75));

		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(panelSopra);
		jScrollPane.setPreferredSize(new Dimension(900, 700));
		jScrollPane.getVerticalScrollBar().setUnitIncrement(10);

		// creiamo la classe che si occupa del DB, la Jlist con tutti i tavoli
		// db = new ModificheDB();
		btnAggiungi = new JButton("Aggiungi Tavolo");
		btnModifica = new JButton("Modifica Tavolo");
		btnElimina = new JButton("Elimina Tavolo");

		lblTavoloSelezionato = new JLabel();

		// aggiorniamo la JList
		aggiornaListTavoli(panelSopra);

		// aggiungo action listener
		btnAggiungi.addActionListener(new ActionBtnAggiungiTavolo());
		btnElimina.addActionListener(new ActionBtnEliminaTavolo());
		btnModifica.addActionListener(new ActionBtnModificaTavolo());

		// aggiungiamo gli elementi
		panelSotto.add(btnAggiungi);
		panelSotto.add(btnModifica);
		panelSotto.add(btnElimina);

		// aggiungiamo i panel
		add(lblTavoloSelezionato, BorderLayout.NORTH);
		add(jScrollPane, BorderLayout.CENTER);
		add(panelSotto, BorderLayout.SOUTH);
	}

	/**
	 * metodo che aggiorna i tavoli della JList
	 */
	private void aggiornaListTavoli(JPanel panel) {

		panelSopra.removeAll();

//		List<Tavolo> listaTavolo = model.getListaTavoli();

		for (Tavolo tavolo : DataService.getTavoli()) {
			BottoneTavolo btn = new BottoneTavolo(tavolo);
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					tavoloSelezionato = ((BottoneTavolo) e.getSource()).getTavolo();
					btnModifica.setEnabled(true);
					btnElimina.setEnabled(true);
					lblTavoloSelezionato.setText("Tavolo Selezionato n° " + tavoloSelezionato.getNome());
				}
			});

			panel.add(btn);
		}

		int pulsantiMancanti = 32 - DataService.getTavoli().size();

		if (pulsantiMancanti > 0) {
			for (int i = 0; i < pulsantiMancanti; i++) {
				JButton btn = new JButton("");
				btn.setVisible(false);
				btn.setEnabled(false);
				panel.add(btn);
			}
		}

		lblTavoloSelezionato.setText("Tavolo Selezionato n° ");

		btnModifica.setEnabled(false);
		btnElimina.setEnabled(false);

		repaint();
		revalidate();

	}

	private void enableFrame(boolean bool) {
		JFrame frame = ((JFrame) SwingUtilities.getWindowAncestor(this));
		frame.setEnabled(bool);
		if (bool) {
			frame.toFront();
		}

	}

	/**
	 * Classe che implementa Action Listener, utilizzata dal bottone modifica tavolo
	 */
	private class ActionBtnModificaTavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// creiamo il frame di modifica
			new FrameAggiungiModificaTavolo(tavoloSelezionato, FrameAggiungiModificaTavolo.MODIFICA);
			enableFrame(false);
		}
	}

	/**
	 * classe che implementa Action listener utilizzata dal bottone elimina tavolo
	 */
	private class ActionBtnEliminaTavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// creaimo gli action listener
			ActionListener actSi = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					// rimoviamo il tavolo TODO
					aggiornaListTavoli(panelSopra);
					frame.dispose();
					enableFrame(true);
				}
			};

			ActionListener actNo = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					frame.dispose();
					enableFrame(true);
				}
			};

			WindowAdapter closeWindAdapter = new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					enableFrame(true);
				}
			};

			// apriamo il frame che chiede la conferma
			new FrameConfermaScelta("bah", "Sicuro di chiudere il tavolo n° " + tavoloSelezionato.getNome(), actSi,
					actNo, closeWindAdapter);
			enableFrame(false);
		}
	}

	/**
	 * classe che implementa Action listener utilizzata dal bottone aggiungi tavolo
	 */
	private class ActionBtnAggiungiTavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new FrameAggiungiModificaTavolo(null, FrameAggiungiModificaTavolo.AGGIUNGI);
			enableFrame(false);
		}

	}

	/**
	 * Frame per le modifiche della lista dei tavoli
	 */
	private class FrameAggiungiModificaTavolo extends JFrame {

		private static final long serialVersionUID = 1L;

		public static final int AGGIUNGI = 0;
		public static final int MODIFICA = 1;

		public FrameAggiungiModificaTavolo(Tavolo tavolo, int tipo) {

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
					enableFrame(true);
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
			JPanel panelPrincipale = new JPanel(new GridLayout(0, 2, 10, 60));
			JPanel panelPulsanti = new JPanel(new GridLayout(0, 2, 75, 75));

			// creo i label e text area
			JLabel lblNomeTavolo = new JLabel("Tavolo n° ");
			String strNomeTavolo = (tipo == AGGIUNGI ? "" : "" + tavolo.getNome());
			JTextArea txtAreaNomeTavolo = new JTextArea(strNomeTavolo);

			JLabel lblPostiTavolo = new JLabel("Tavolo n° ");
			String strPostiTavolo = (tipo == AGGIUNGI ? "" : "" + tavolo.getNumPostiMassimi());
			JTextArea txtAreaPostiTavolo = new JTextArea(strPostiTavolo);

			// creazione dei pulsanti
			JButton btnConferma = new JButton("Conferma");
			JButton btnAnnulla = new JButton("Annulla");

			// creazione listener
			ActionListener actAnnulla = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					enableFrame(true);
				}
			};
			// TODO
			ActionListener actConferma = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO aggiungere al DB
					dispose();
					enableFrame(true);
				}
			};

			// aggiungiamo gli action listener ai bottoni
			btnConferma.addActionListener(actConferma);
			btnAnnulla.addActionListener(actAnnulla);

			// aggiungiamo i componenti al panel principale
			panelPrincipale.add(lblNomeTavolo);
			panelPrincipale.add(txtAreaNomeTavolo);
			panelPrincipale.add(lblPostiTavolo);
			panelPrincipale.add(txtAreaPostiTavolo);

			// aggiungiamo i componenti al panel pulsanti
			panelPulsanti.add(btnConferma);
			panelPulsanti.add(btnAnnulla);

			// aggiungiamo i panel al content pane
			getContentPane().add(panelPrincipale, BorderLayout.CENTER);
			getContentPane().add(panelPulsanti, BorderLayout.SOUTH);

			setAlwaysOnTop(true);
			setVisible(true);
		}
	}
}
