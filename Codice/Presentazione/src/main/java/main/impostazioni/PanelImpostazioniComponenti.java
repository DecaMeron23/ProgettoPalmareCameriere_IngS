package main.impostazioni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import classi.dataBase.DataService;
import classi.menu.Componente;
import logico.Logico;
import main.FrameConfermaScelta;
import main.MainFrame;
import main.impostazioni.PanelImpostazioniComponenti.PanelModificaComponente.PanelRiordinaComponenti;

class PanelImpostazioniComponenti extends JPanel {

	private static final long serialVersionUID = -2936621503776909843L;

	/**
	 * Panel contenente i pulsanti delle diversi componenti
	 */
	JPanel panelSopraCenter;

	/**
	 * La componente attuale selezionata
	 */
	Componente componenteSelezionata;

	/**
	 * panel contentente la parte principale delle modifiche
	 */
	PanelModificaComponente panelSopraSud;

	public PanelRiordinaComponenti panelRiordinaComponenti;

	public PanelImpostazioniComponenti() {

		// creazione panel
		JPanel panelSopra = new JPanel(new BorderLayout());
		// panel che è contenuto in panelSottoMain
		JPanel panelSotto = new JPanel(new BorderLayout());
		// Panel principale sotto
		JPanel panelSottoMain = new JPanel();
		panelSottoMain.setPreferredSize(new Dimension(900, 200));

		JPanel panelSottoScritte = new JPanel(new FlowLayout());

		JScrollPane scrollPanelSopra = new JScrollPane();
		panelSopraCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panelSopraSud = new PanelModificaComponente();

		// creazione label
		JLabel lblModifica = new JLabel("Scegli la componente");
		JLabel lblAggiungi = new JLabel("Aggiungi Componente:");

		lblModifica.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAggiungi.setFont(new Font("Arial", Font.PLAIN, 18));

		JLabel lblErroreSotto = new JLabel();
		lblErroreSotto.setForeground(Color.RED);

		JTextArea txtAggiungi = new JTextArea();
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setEnabled(false);
		btnAggiungi.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		btnAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = txtAggiungi.getText().trim();
				try {
					Logico.aggiungiComponente(new Componente(nome));
				} catch (Exception e1) {
					System.out.println("Questa componente esiste già!!");
					lblErroreSotto.setText("Questa componente esiste già!!");
					startTimer();

				}
				txtAggiungi.setText("");
				aggiornaFrame();
			}

			private void startTimer() {
				int delay = 10000; // 10 secondi in millisecondi
				Timer timer = new Timer(delay, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						lblErroreSotto.setText("");
					}

				});

				timer.setRepeats(false);
				timer.start();
			}
		});

		// aggiungiamo il listener per la text area
		txtAggiungi.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateButtonState();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateButtonState();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateButtonState();
			}

			private void updateButtonState() {
				btnAggiungi.setEnabled(!txtAggiungi.getText().isBlank());
			}
		});

		// creazione txt field
		txtAggiungi.setPreferredSize(new Dimension(700, 25));
		txtAggiungi.setFont(new Font("Arial", Font.PLAIN, 18));

		// settiamo lo scroll pane
		scrollPanelSopra.setPreferredSize(new Dimension(900, 75));
		scrollPanelSopra.setViewportView(panelSopraCenter);
		scrollPanelSopra.getHorizontalScrollBar().setUnitIncrement(10);

		// aggiungiamo i componenti del panel sopra
		panelSopra.add(lblModifica, BorderLayout.NORTH);
		panelSopra.add(scrollPanelSopra, BorderLayout.CENTER);
		panelSopra.add(panelSopraSud, BorderLayout.SOUTH);

		// panel Sotto
		panelSottoMain.add(panelSotto);

		panelSottoScritte.add(lblAggiungi);
		panelSottoScritte.add(txtAggiungi);

		panelSotto.add(panelSottoScritte, BorderLayout.NORTH);
		panelSotto.add(lblErroreSotto, BorderLayout.CENTER);
		panelSotto.add(btnAggiungi, BorderLayout.SOUTH);

		// creazione split pane
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelSopra, panelSottoMain);
		split.setEnabled(false);
		split.setDividerSize(1);
		add(split);

		// creiamo i pulsanti
		aggiornaPulsantiComponenti();
	}

	/**
	 * 
	 */
	private void aggiornaFrame() {
		panelRiordinaComponenti.aggiornaModel();
		panelSopraSud.aggiornaElementi();
		aggiornaPulsantiComponenti();
	}

	/**
	 * Metodo che aggiorna i pulsanti delle componenti
	 */
	private void aggiornaPulsantiComponenti() {
		panelSopraCenter.removeAll();
		panelSopraCenter.repaint();
		panelSopraCenter.revalidate();
		for (Componente componente : DataService.getComponenti()) {
			String nomeComponente = componente.getNome();
			JButton btn = new JButton(nomeComponente);
			btn.setPreferredSize(new Dimension(150, 30));
			btn.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (componenteSelezionata != null && componenteSelezionata.equals(componente)) {
						componenteSelezionata = null;
						panelSopraSud.textComponente.setEnabled(false);
					} else {
						componenteSelezionata = componente;
						panelSopraSud.textComponente.setEnabled(true);
					}

					aggiornaFrame();
					panelSopraSud.btnSalva.setEnabled(false);

				}
			});

			if (componenteSelezionata != null && componenteSelezionata.equals(componente)) {
				btn.setBackground(Color.CYAN);
			} else {
				btn.setBackground(null);
			}

			panelSopraCenter.add(btn);
		}
	}

	protected void enableFrame(boolean bool) {
		MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
		frame.setEnabled(bool);
		if (bool) {
			frame.toFront();
		}
	}

	/**
	 * Panel per la parte di modifica di una componente
	 */
	class PanelModificaComponente extends JPanel {

		private static final long serialVersionUID = -5737802857021715407L;

		/**
		 * Il text Area per il componente
		 */
		JTextArea textComponente;
		/**
		 * Label per il nome del componente selezionato
		 */
		JLabel lblComponenteAttuale;

		/**
		 * Bottone salva
		 */
		JButton btnSalva;
		/**
		 * Bottone elimina
		 */
		JButton btnElimina;

		public PanelModificaComponente() {
			super(new BorderLayout());
			setPreferredSize(new Dimension(900, 400));

			// creazione delle componenti del panel
			// textArea per il nome della componente
			textComponente = new JTextArea();
			textComponente.setEnabled(false);
			textComponente.setFont(new Font("Arial", Font.PLAIN, 18));
			textComponente.setPreferredSize(new Dimension(350, 25));

			textComponente.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					updateButtonState();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					updateButtonState();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					updateButtonState();
				}

				private void updateButtonState() {
					if (componenteSelezionata == null) {
						btnSalva.setEnabled(false);
						textComponente.setEditable(false);
					} else {
						btnSalva.setEnabled(!textComponente.getText().equals(componenteSelezionata.getNome()));
						textComponente.setEditable(true);
					}
				}
			});

			// label per il nome della componente selezionata
			lblComponenteAttuale = new JLabel();
			lblComponenteAttuale.setFont(new Font("Arial", Font.PLAIN, 18));

			// label per la modifica
			JLabel lblModifica = new JLabel("Nuovo nome della componente: ");
			lblModifica.setFont(new Font("Arial", Font.PLAIN, 18));
			// label in caso di Errore
			JLabel lblErrore = new JLabel();
			lblErrore.setFont(new Font("Arial", Font.PLAIN, 18));
			lblErrore.setForeground(Color.RED);
			// bottone salva
			btnSalva = new JButton("Salva");
			btnSalva.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
			btnSalva.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String nome = textComponente.getText().trim(); // trim per rimuovere spazi a inizio e fine
					if (!nome.isBlank()) {
						Componente compNuova = new Componente(nome);
						Componente compVecchia = new Componente(componenteSelezionata.getNome());
						try {
							Logico.modificaComponente(compVecchia, compNuova);
						} catch (Exception ex) {
							System.out.println("il nome della componente esiste già");
							lblErrore.setText("Questa componente esiste già!");
							startTimer();
						}
						componenteSelezionata = null;
					} else {
						lblErrore.setText("Assegnare un nome alla componente per salvare!");
						startTimer();
					}
					aggiornaFrame();
				}

				private void startTimer() {
					int delay = 10000; // 10 secondi in millisecondi
					Timer timer = new Timer(delay, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							lblErrore.setText("");
						}

					});

					timer.setRepeats(false);
					timer.start();
				}
			});
			// bottone elimina
			btnElimina = new JButton("Elimina");
			btnElimina.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
			btnElimina.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					ActionListener actSi = new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								Logico.eliminaCompondente(componenteSelezionata);
							} catch (Exception e1) {
							}
							componenteSelezionata = null;
							aggiornaFrame();
							JDialog frame = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
							frame.dispose();
						}
					};

					ActionListener actNo = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JDialog frame = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
							frame.dispose();
						}
					};

					WindowAdapter winAp = new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							enableFrame(true);
						}
					};

					new FrameConfermaScelta("Eliminazione Componente", "Sicuro di eliminare la componente?", actSi,
							actNo, winAp);
					enableFrame(false);
				}
			});

			aggiornaElementi();

			JPanel alto = new JPanel();
			JPanel centroSopra = new JPanel(new FlowLayout(10));
			JPanel centro = new JPanel(new GridLayout(0, 1));
			panelRiordinaComponenti = new PanelRiordinaComponenti();
			JPanel centrobottoni = new JPanel(new FlowLayout(10));
			JPanel basso = new JPanel(new GridLayout(0, 1));
			JPanel centroBasso = new JPanel(new GridLayout(0, 1));

			alto.add(lblComponenteAttuale);

			centroSopra.add(lblModifica);
			centroSopra.add(textComponente);

			centrobottoni.add(btnElimina);
			centrobottoni.add(btnSalva);

			centroBasso.add(lblErrore);
			centroBasso.add(centrobottoni);

			centro.add(centroSopra);
			centro.add(centroBasso);

			basso.add(panelRiordinaComponenti);

			add(alto, BorderLayout.NORTH);
			add(centro, BorderLayout.CENTER);
			add(basso, BorderLayout.SOUTH);

		}

		/**
		 * Metodo che aggiorna gli elementi del panel
		 */
		void aggiornaElementi() {
			String nomeComponente = "";
			String componenteAttuale = "Modifica la componente: ";
			boolean boolBtn = false;
			if (componenteSelezionata != null) {
				nomeComponente = componenteSelezionata.getNome();
				componenteAttuale += componenteSelezionata.getNome();
				boolBtn = true;
			}

			textComponente.setText(nomeComponente);
			lblComponenteAttuale.setText(componenteAttuale);

			btnElimina.setEnabled(boolBtn);
			btnSalva.setEnabled(boolBtn);

		}

		class PanelRiordinaComponenti extends JPanel {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private DefaultListModel<String> listModel;
			private JList<String> componentiList;

			public PanelRiordinaComponenti() {
				// Creazione del modello della lista
				listModel = new DefaultListModel<>();
				aggiornaModel();

				// Creazione della lista con il modello
				componentiList = new JList<>(listModel);
				componentiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				componentiList.setFont(new Font("Arial", Font.PLAIN, 18));

				// Creazione dei bottoni per spostare gli elementi su e giù
				JButton suButton = new JButton("Su");
				JButton giuButton = new JButton("Giù");

				// Aggiunta degli ActionListener ai bottoni
				suButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Componente componente = getComponenteSelezionata();
						int indexAttuale = componentiList.getSelectedIndex();
						if (componente != null) {
							Logico.incrementaPrecendeza(componente);
							aggiornaFrame();
							componentiList.setSelectedIndex(indexAttuale - 1);
						}
					}
				});

				giuButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Componente componente = getComponenteSelezionata();
						int indexAttuale = componentiList.getSelectedIndex();
						if (componente != null) {
							Logico.decrementaPrecendeza(componente);
							aggiornaFrame();
							componentiList.setSelectedIndex(indexAttuale + 1);
						}
					}
				});

				// Creazione del pannello per i bottoni
				JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 20));
				buttonPanel.add(suButton);
				buttonPanel.add(giuButton);

				// Creazione del layout principale
				setLayout(new BorderLayout());
				add(new JScrollPane(componentiList), BorderLayout.CENTER);
				add(buttonPanel, BorderLayout.EAST);
			}

			/**
			 * @return la componente selezionate dalla JList, null se nessu elemento è
			 *         selezionato
			 */
			protected Componente getComponenteSelezionata() {
				int index = componentiList.getSelectedIndex();

				if (index < 0) {
					return null;
				}
				return DataService.getComponenti().get(index);
			}

			void aggiornaModel() {
				listModel.clear();
				List<String> nomiComponenti = new ArrayList<String>();
				for (Componente componente : DataService.getComponenti()) {
					nomiComponenti.add(componente.getNome());
				}
				listModel.addAll(nomiComponenti);
			}

		}
	}
}