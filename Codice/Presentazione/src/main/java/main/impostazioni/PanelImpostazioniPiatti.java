package main.impostazioni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import classi.dataBase.DataService;
import classi.menu.Componente;
import classi.menu.Piatto;
import logico.Logico;
import main.FrameConfermaScelta;

/**
 * Panel delle impostazioni relativo per i piatti
 */
class PanelImpostazioniPiatti extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * il menù a tendia per le componenti
	 */
	private JComboBox<String> comboBoxComponenti;

	/**
	 * la JTable contenente i piatti della relativa componente
	 */
	private JTable tablePiatti;

	/**
	 * il Piatto attualmente selezionato
	 */
	protected Piatto piattoSelezionato;

	/**
	 * la componente attualmente selezionata
	 */
	protected Componente componenteSelezionata;

	/**
	 * Il bottone per la modifica dei piatti
	 */
	protected JButton btnModifica;

	/**
	 * Il bottone per l'eliminazione dei piatti
	 */
	protected JButton btnElimina;

	public PanelImpostazioniPiatti() {
		setPreferredSize(new Dimension(900, 750));
		setLayout(new BorderLayout());

		JPanel panelTitoloEComboBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel lblSelezioneComponente = new JLabel("Il Nuovo Piatto è un: ");
		lblSelezioneComponente.setFont(new Font(getFont().getName(),  Font.PLAIN, 30));
		comboBoxComponenti = new JComboBox<>();
		comboBoxComponenti.setFont(new Font(getFont().getName(), Font.PLAIN, 30));
		comboBoxComponenti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomeComponente = (String) comboBoxComponenti.getSelectedItem();
				for (Componente componente : DataService.getComponenti()) {
					if (componente.getNome().equals(nomeComponente)) {
						aggiornaTabellaPiatti(componente);
						break;
					}
				}
			}
		});
		aggiornaComboBox();

		panelTitoloEComboBox.add(lblSelezioneComponente);
		panelTitoloEComboBox.add(comboBoxComponenti);

		tablePiatti = new JTable();
		tablePiatti.setDefaultRenderer(Object.class, new PiattoRendererTable());
		tablePiatti.setDefaultEditor(Object.class, null);
		tablePiatti.setFillsViewportHeight(true);
		tablePiatti.setRowHeight(30);
		tablePiatti.getTableHeader().setFont(new Font(getFont().getName(), Font.PLAIN, 22));
		tablePiatti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePiatti.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// se il click del mouse è completato
				if (!e.getValueIsAdjusting()) {
					int selectedRow = tablePiatti.getSelectedRow();

					if (selectedRow != -1) { // se la riga selezionata esiste
						String nomePiatto = (String) tablePiatti.getValueAt(selectedRow, 0);
						String strPrezzoPiatto = ((String) tablePiatti.getValueAt(selectedRow, 1)).split(" ")[0];
						strPrezzoPiatto = strPrezzoPiatto.split(",")[0] + "." + strPrezzoPiatto.split(",")[1];
						double prezzoPiatto = (double) Double.parseDouble(strPrezzoPiatto);

						piattoSelezionato = new Piatto(nomePiatto, prezzoPiatto);
						attvaPulsanti(true);

					} else { // se non è stata selezionata nessuna riga
						piattoSelezionato = null;
						attvaPulsanti(false);
					}
				}
			}
		});
		aggiornaTabellaPiatti(DataService.getComponenti().get(0));

		JScrollPane scrollPaneTable = new JScrollPane(tablePiatti);
		scrollPaneTable.setPreferredSize(new Dimension(800, 500));

		JPanel panelBottoni = new JPanel(new GridLayout(1, 3, 10, 5));

		// creiamo i bottoni
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameAggiungiModificaPiatto(FrameAggiungiModificaPiatto.AGGIUNGI);
				enableFrame(false);
			}
		});
		btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(e -> {
			new FrameAggiungiModificaPiatto(FrameAggiungiModificaPiatto.MODIFICA);
			enableFrame(false);
		});
		btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListenerEliminaPiatto());

		attvaPulsanti(false);

		panelBottoni.add(btnAggiungi);
		panelBottoni.add(btnModifica);
		panelBottoni.add(btnElimina);

		add(panelTitoloEComboBox, BorderLayout.NORTH);
		add(scrollPaneTable, BorderLayout.CENTER);
		add(panelBottoni, BorderLayout.SOUTH);
	}

	/**
	 * @param attivare, se @ture i pulsanti Elimina e Modifica si ativano, sennò no
	 */
	private void attvaPulsanti(boolean attivare) {
		btnElimina.setEnabled(attivare);
		btnModifica.setEnabled(attivare);
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

	private void aggiornaComboBox() {
		List<Componente> listaComponenti = DataService.getComponenti();
		Vector<String> listaNomiComponenti = new Vector<String>();

		for (Componente componente : listaComponenti) {
			listaNomiComponenti.add(componente.getNome());
		}

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(listaNomiComponenti);
		comboBoxComponenti.setModel(model);
	}

	private void aggiornaTabellaPiatti(Componente componente) {
		componenteSelezionata = componente;
		List<Piatto> listaPiatti = DataService.getPiatti(componente);

		String[] columnNames = { "Piatto", "Prezzo" };
		Object[][] data = new Object[listaPiatti.size()][2];

		for (int i = 0; i < listaPiatti.size(); i++) {
			Piatto piatto = listaPiatti.get(i);
			data[i][0] = piatto.getNome();
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String prezzoFormattato = decimalFormat.format(piatto.getPrezzo());
			data[i][1] = prezzoFormattato + " €";
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		tablePiatti.setModel(model);

		// Imposta la larghezza delle colonne
		TableColumn columnPiatto = tablePiatti.getColumnModel().getColumn(0);
		columnPiatto.setPreferredWidth(150);

		TableColumn columnPrezzo = tablePiatti.getColumnModel().getColumn(1);
		columnPrezzo.setPreferredWidth(50);

	}

	/**
	 * classe che implementa Action listener utilizzata dal bottone elimina piatto
	 */
	private class ActionListenerEliminaPiatto implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// creaimo gli action listener
			ActionListener actSi = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JDialog frame = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());

					try {
						Logico.eliminaPiatto(piattoSelezionato, componenteSelezionata);
					} catch (Exception ex) {
						System.out.println("Il piatto non esiste(?)");
					}
					aggiornaTabellaPiatti(componenteSelezionata);
					frame.dispose();
					enableFrame(true);
				}

			};

			ActionListener actNo = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JDialog frame = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
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
			new FrameConfermaScelta("Eliminazione Piatto",
					"Sicuro di eliminare il piatto: " + piattoSelezionato.getNome(), actSi, actNo, closeWindAdapter);
			enableFrame(false);
		}

	}

	static class PiattoRendererTable extends DefaultTableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1252794729222342964L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			// Crea un nuovo Font con dimensioni personalizzate
			Font font = new Font(getFont().getName(), Font.PLAIN, 22); // Sostituisci 14 con la dimensione desiderata
			setFont(font);

			if (column == 1) {
				// Se si tratta della colonna "Prezzo", allinea a destra
				setHorizontalAlignment(SwingConstants.RIGHT);
			} else {
				// Altrimenti, allinea a sinistra
				setHorizontalAlignment(SwingConstants.LEFT);
			}

			return this;
		}
	}

	/**
	 * Frame per le modifiche della lista dei tavoli
	 */
	private class FrameAggiungiModificaPiatto extends JDialog {

		/**
		 * 
		 */
		private static final long serialVersionUID = 471060309226612092L;

		/**
		 * Operazione Aggiungi
		 */
		public static final int AGGIUNGI = 0;
		/**
		 * Operazione Modifica
		 */
		public static final int MODIFICA = 1;

		/**
		 * il text Area per il nome del piatto
		 */
		private JTextArea txtAreaNomePiatto;

		/**
		 * il textArea per il prezzo del piatto
		 */
		private JTextArea txtAreaPrezzoPiatto;

		/**
		 * il label per l'errore
		 */
		private JLabel lblErrore;

		/**
		 * il tipo di operazione che si esegue
		 */
		private int tipo;

		public FrameAggiungiModificaPiatto(int tipologia) {

			this.tipo = tipologia;

			String titolo = (tipo == MODIFICA ? "Modifica Piatto" : "Aggiungi Piatto");

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
			JLabel lblNomePiatto = new JLabel("Nome del Piatto: ");
			String strNomePiatto = (tipo == AGGIUNGI ? "" : "" + piattoSelezionato.getNome());
			txtAreaNomePiatto = new JTextArea(strNomePiatto);

			// creo i label e text area per posti del tavolo
			JLabel lblPrezzoPiatto = new JLabel("Prezzo del Piatto: ");
			String strPostiPiatto = (tipo == AGGIUNGI ? "" : "" + piattoSelezionato.getPrezzo());
			txtAreaPrezzoPiatto = new JTextArea(strPostiPiatto);

			// Creazione label Errore
			lblErrore = new JLabel();
			lblErrore.setForeground(Color.red);

			// creazione dei pulsanti
			JButton btnConferma = new JButton("Conferma");
			JButton btnAnnulla = new JButton("Annulla");

			// creazione listener
			ActionListener actConferma = new ActionListenerConferma();
			ActionListener actAnnulla = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					enableFrame(true);
				}
			};

			// aggiungiamo gli action listener ai bottoni
			btnConferma.addActionListener(actConferma);
			btnAnnulla.addActionListener(actAnnulla);

			// aggiungiamo i componenti al panel principale
			panelPrincipale.add(lblNomePiatto);
			panelPrincipale.add(txtAreaNomePiatto);
			panelPrincipale.add(lblPrezzoPiatto);
			panelPrincipale.add(txtAreaPrezzoPiatto);

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

				double prezzoPiatto = 0.0;
				boolean noEccezioni = true;

				String nomePiatto = txtAreaNomePiatto.getText().trim();
				String prezzoPiattoStr = txtAreaPrezzoPiatto.getText().trim();

				// verifichiamo se le stringhe sono vuote
				if (nomePiatto.isBlank() || prezzoPiattoStr.isBlank()) {
					sendErrore("Inserire un valore!!");
					noEccezioni = false;
				}

				// blocco try catch per verificare se non si verificano problemi con il parse
				// del prezzo del piatto
				if (noEccezioni) {
					try {
						prezzoPiatto = Double.parseDouble(prezzoPiattoStr);
					} catch (Exception ex) {
						noEccezioni = false;
						sendErrore("Prezzo non identificabile!!");
					}

				}

				if (prezzoPiatto <= 0 && noEccezioni) {
					sendErrore("Prezzo minore o uguale di 0!!");
					noEccezioni = false;
				}

				// se non ci sono state eccezzioni aggiungiamo/modifichiamo il piatto
				if (noEccezioni) {
					// creiamo il piatto
					Piatto piatto = new Piatto(nomePiatto, prezzoPiatto);

					// se il tipo di modifica è AGGIUNGI
					if (tipo == AGGIUNGI) {
						// eseguiamo il try catch per verificare se ci sono problemi durante l'aggiunta
						// del piatto
						try {
							// aggiungiamo il piatto
							Logico.aggiungiPiatto(piatto, componenteSelezionata);
						} catch (Exception ex) {
							noEccezioni = false;
							sendErrore("Il piatto: " + nomePiatto + " esiste già");

						}
						// Se il tipo di modifica è MODIFICA
					} else if (tipo == MODIFICA) {
						// aggiorniamo il piatto
						try {
							Logico.modificaPiatto(piattoSelezionato, piatto, componenteSelezionata);
						} catch (Exception ex) {
							noEccezioni = false;
							sendErrore("Il piatto: " + nomePiatto + " esiste già");
						}
					}
				}
				// Se durante l'esecuzione di questo metodo NON si sono verificate eccezzioni
				// allora chiudiamo il frame
				if (noEccezioni) {
					// togliamo la selezione al tavolo
					piattoSelezionato = null;
					// aggiorniamo la lista dei tavoli
					aggiornaTabellaPiatti(componenteSelezionata);
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
