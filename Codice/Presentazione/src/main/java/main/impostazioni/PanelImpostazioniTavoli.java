package main.impostazioni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import classi.dataBase.DataService;
import classi.enumerativi.StatoTavolo;
import classi.tavolo.Tavolo;
import logico.Logico;
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
	// private InterfaceModel model;

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
		panelSopra = new JPanel(new GridLayout(0, 3, 100, 40));
		JPanel panelSotto = new JPanel(new GridLayout(0, 3, 75, 75));

		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(panelSopra);
		jScrollPane.setPreferredSize(new Dimension(900, 700));
		jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// creiamo la classe che si occupa del DB, la Jlist con tutti i tavoli
		// db = new ModificheDB();
		btnAggiungi = new JButton("Aggiungi Tavolo");
		btnAggiungi.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		btnModifica = new JButton("Modifica Tavolo");
		btnModifica.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		btnElimina = new JButton("Elimina Tavolo");
		btnElimina.setFont(new Font(getFont().getName(), Font.PLAIN, 20));

		lblTavoloSelezionato = new JLabel();
		lblTavoloSelezionato.setFont(new Font(getFont().getName(), Font.PLAIN, 25));

		// aggiorniamo la JList
		aggiornaListTavoli();

		// aggiungo action listener
		btnAggiungi.addActionListener(new ActionBtnAggiungiTavolo());
		btnModifica.addActionListener(new ActionBtnModificaTavolo());
		btnElimina.addActionListener(new ActionBtnEliminaTavolo());

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
	private void aggiornaListTavoli() {

		panelSopra.removeAll();

		for (Tavolo tavolo : DataService.getTavoli()) {
			BottoneTavolo btn = new BottoneTavolo(tavolo);
			btn.aggiornaStato();

			if (tavoloSelezionato != null && tavoloSelezionato.equals(tavolo)) {
				btn.setBackground(Color.CYAN);
			}

			btn.addActionListener(new ActionListener() {

				/**
				 * Action listener per il pulsante tavolo
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					// possiamo selezionare il tavolo solo se è libero
					if (tavolo.getStato().equals(StatoTavolo.LIBERO)) {
						Boolean bool;
						int nome;

						if (tavoloSelezionato != null && tavoloSelezionato.equals(tavolo)) {
							tavoloSelezionato = null;
							bool = false;
							nome = 0;
						} else {
							tavoloSelezionato = ((BottoneTavolo) e.getSource()).getTavolo();
							bool = true;
							nome = tavoloSelezionato.getNome();
						}
						aggiornaListTavoli();

						aggiornaPulsanti(bool);

						lblTavoloSelezionato.setText("Tavolo Selezionato n° " + (nome == 0 ? "" : "" + nome));
					}
				}

			});

			panelSopra.add(btn);
		}

		// aggiungiamo pulsanti invisibili per mantenere la forma dei pulsanti

		int pulsantiMancanti = 21 - DataService.getTavoli().size();

		if (pulsantiMancanti > 0) {
			for (int i = 0; i < pulsantiMancanti; i++) {
				JButton btn = new JButton("");
				btn.setVisible(false);
				btn.setEnabled(false);
				panelSopra.add(btn);
			}
		}

		lblTavoloSelezionato.setText("Tavolo Selezionato n° ");

		aggiornaPulsanti(false);

		repaint();
		revalidate();

	}

	/**
	 * Metodo utilizzato per aggiornare i pulsanti Modifica e Elimina
	 * 
	 * @param bool se true attiva i pulsanti, sennò li disattiva
	 */
	private void aggiornaPulsanti(Boolean bool) {
		btnModifica.setEnabled(bool);
		btnElimina.setEnabled(bool);
	}

	/**
	 * Questo metodo attiva o disattiva il mainFrame
	 * 
	 * @param bool il valore booleano, se false disattiva il mainFrame
	 */
	private void enableFrame(boolean bool) {
		JFrame frame = ((JFrame) SwingUtilities.getWindowAncestor(this));
		frame.setEnabled(bool);
		if (bool) {
			frame.toFront();
		}

	}

	/**
	 * classe che implementa Action listener utilizzata dal bottone aggiungi tavolo
	 */
	private class ActionBtnAggiungiTavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new FrameAggiungiModificaTavolo(FrameAggiungiModificaTavolo.AGGIUNGI);
			enableFrame(false);
		}

	}

	/**
	 * Classe che implementa Action Listener, utilizzata dal bottone modifica tavolo
	 */
	private class ActionBtnModificaTavolo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// creiamo il frame di modifica
			new FrameAggiungiModificaTavolo(FrameAggiungiModificaTavolo.MODIFICA);
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
					JDialog frame = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					frame.dispose();

					try {
						Logico.eliminaTavolo(tavoloSelezionato);
					} catch (Exception ex) {
						System.out.println("Il tavolo non esite");
					}
					aggiornaListTavoli();
				}
			};

			ActionListener actNo = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JDialog frame = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					frame.dispose();
				}
			};

			WindowAdapter closeWindAdapter = new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					enableFrame(true);
				}
			};

			// apriamo il frame che chiede la conferma
			new FrameConfermaScelta("Eliminazione Tavolo",
					"Sicuro di eliminare il tavolo n° " + tavoloSelezionato.getNome(), actSi, actNo, closeWindAdapter);
			enableFrame(false);
		}
	}

	/**
	 * Frame per le modifiche della lista dei tavoli
	 */
	private class FrameAggiungiModificaTavolo extends JDialog {

		private static final long serialVersionUID = 1L;

		public static final int AGGIUNGI = 0;
		public static final int MODIFICA = 1;

		private JTextArea txtAreaNomeTavolo;

		private JTextArea txtAreaPostiTavolo;

		private JLabel lblErrore;

		private int tipo;

		public FrameAggiungiModificaTavolo(int tipologia) {

			this.tipo = tipologia;

			String titolo = (tipo == MODIFICA ? ("Modifica Tavolo n°" + tavoloSelezionato.getNome())
					: "Aggiungi Tavolo");

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
			JPanel panelPrincipale = new JPanel(new GridLayout(0, 2, 10, 50));
			JPanel panelPulsanti = new JPanel(new GridLayout(0, 2, 75, 75));
			JPanel panelPulsantiMain = new JPanel(new GridLayout(0, 1, 10, 10));

			// creo i label e text area per il nome del tavolo
			JLabel lblNomeTavolo = new JLabel("Tavolo n° ");
			String strNomeTavolo = (tipo == AGGIUNGI ? "" : "" + tavoloSelezionato.getNome());
			txtAreaNomeTavolo = new JTextArea(strNomeTavolo);

			// creo i label e text area per posti del tavolo
			JLabel lblPostiTavolo = new JLabel("N° posti ");
			String strPostiTavolo = (tipo == AGGIUNGI ? "" : "" + tavoloSelezionato.getNumPostiMassimi());
			txtAreaPostiTavolo = new JTextArea(strPostiTavolo);

			// Creazione label Errore
			lblErrore = new JLabel();
			lblErrore.setForeground(Color.red);

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

			ActionListener actConferma = new ActionListenerConferma();

			// aggiungiamo gli action listener ai bottoni
			btnConferma.addActionListener(actConferma);
			btnAnnulla.addActionListener(actAnnulla);

			// aggiungiamo i componenti al panel principale
			panelPrincipale.add(lblNomeTavolo);
			panelPrincipale.add(txtAreaNomeTavolo);
			panelPrincipale.add(lblPostiTavolo);
			panelPrincipale.add(txtAreaPostiTavolo);

			// aggiungiamo i componenti al panel pulsanti
			panelPulsanti.add(btnAnnulla);
			panelPulsanti.add(btnConferma);

			panelPulsantiMain.add(lblErrore);
			panelPulsantiMain.add(panelPulsanti);

			// aggiungiamo i panel al content pane
			getContentPane().add(panelPrincipale, BorderLayout.CENTER);
			getContentPane().add(panelPulsantiMain, BorderLayout.SOUTH);

			setAlwaysOnTop(true);
			setVisible(true);
		}

		/**
		 * Classe che implementa l'action listener per il pulsante conferma
		 */
		class ActionListenerConferma implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				int nomeTavolo = 0;
				int numeroPosti = 0;
				boolean noEccezioni = true;

				String nomeTavoloStr = txtAreaNomeTavolo.getText().trim();
				String numeroPostiStr = txtAreaPostiTavolo.getText().trim();

				if (nomeTavoloStr.isBlank() || numeroPostiStr.isBlank()) {
					sendErrore("Inserire un valore intero!!");
					noEccezioni = false;
				}

				// blocco try catch per verificare se non si verificano problemi con il parse
				// del nome e dei posti del tavolo
				if (noEccezioni) {
					try {
						nomeTavolo = Integer.parseInt(nomeTavoloStr);
						numeroPosti = Integer.parseInt(numeroPostiStr);
					} catch (Exception ex) {
						noEccezioni = false;
						sendErrore("Utilizzare numeri interi!!");
					}
				}
				if ((numeroPosti <= 0 || nomeTavolo <= 0) && noEccezioni) {
					sendErrore("Numeri minori o uguali di 0!!");
					noEccezioni = false;
				}

				// se non ci sono state eccezzioni aggiungiamo/modifichiamo il tavolo
				if (noEccezioni) {
					// creiamo il tavolo
					Tavolo tavolo = new Tavolo(nomeTavolo, numeroPosti, StatoTavolo.LIBERO);

					// se il tipo di modifica è AGGIUNGI tavolo
					if (tipo == AGGIUNGI) {
						// eseguiamo il try catch per verificare se ci sono problemi durante l'aggiunta
						// del tavolo
						try {
							// aggiungiamo il tavolo
							Logico.aggiungiTavolo(tavolo);
						} catch (Exception ex) {
							noEccezioni = false;
							sendErrore("Il tavolo n° " + nomeTavolo + " esiste già");

						}
						// Se il tipo di modifica è MODIFICA tavolo
					} else if (tipo == MODIFICA) {
						// aggiorniamo il tavolo
						try {
							Logico.modificaTavolo(tavoloSelezionato, tavolo);
						} catch (Exception e1) {
							noEccezioni = false;
							sendErrore("Il tavolo n° " + nomeTavolo + " esiste già");
						}
					}
				}
				// Se durante l'esecuzione di questo metodo NON si sono verificate eccezzioni
				// allora chiudiamo il frame
				if (noEccezioni) {
					// togliamo la selezione al tavolo
					tavoloSelezionato = null;
					// aggiorniamo la lista dei tavoli
					aggiornaListTavoli();
					dispose();
					enableFrame(true);
				}
			}

			private void sendErrore(String string) {
				lblErrore.setText(string);
				int delay = 10000; // 10 secondi in millisecondi
				Timer timer = new Timer(delay, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						lblErrore.setText("");
					}
				});
				timer.setRepeats(false); // Imposta il timer per eseguire l'azione solo una volta
				timer.start();
			}

		}
	}

}
