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
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import classi.dataBase.DataService;
import logico.Logico;

/**
 * 
 */
class PanelImpostazioniSinistro extends JPanel {

	private static final long serialVersionUID = -641162938886101084L;

	// array dei bottoni
	private final JButton[] btnImpostazioni;

	/**
	 * @param action: action listener dei bottoni
	 */
	PanelImpostazioniSinistro(ActionListener action) {

		JPanel panelBtnImpostazioni = new JPanel(new GridLayout(0, 1));
		JPanel panelBtnCoperto = new JPanel();

		// set layout
		setLayout(new BorderLayout());

		// creazione dei pulsanti
		JButton btnTavoli = new JButton("Tavoli");
		btnTavoli.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		JButton btnComponenti = new JButton("Componenti");
		btnComponenti.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		JButton btnPiatti = new JButton("Piatti");
		btnPiatti.setFont(new Font(getFont().getName(), Font.PLAIN, 20));

		JButton btnCoperto = new JButton("Modifica Coperto");
		btnCoperto.setPreferredSize(new Dimension(400, 100));
		btnCoperto.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		;
		btnCoperto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FinestraModificaCoperto();
				enableFrame(false);

			}
		});

		// aggiungiamo i bottoni
		btnImpostazioni = new JButton[] { btnTavoli, btnComponenti, btnPiatti };

		// settaggio identificativi bottoni
		btnTavoli.setName("tavoli");
		btnComponenti.setName("componenti");
		btnPiatti.setName("piatto");

		// aggiunta action listener e aggiunta al panel
		for (JButton btn : btnImpostazioni) {
			btn.addActionListener(action);
			panelBtnImpostazioni.add(btn);
		}

		panelBtnCoperto.add(btnCoperto);

		add(panelBtnImpostazioni, BorderLayout.CENTER);
		add(panelBtnCoperto, BorderLayout.SOUTH);

	}

	/**
	 * Metodo che serve per attivare il frame
	 * 
	 * @param bool se true attiva il frame
	 */
	private void enableFrame(boolean bool) {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.setEnabled(bool);
		if(bool) {
			frame.toFront();
		}
	}

	/**
	 * funzione che setta il colore standard dei pulsanti eccetto quello passato
	 * come parametro
	 * 
	 * @param button: il bottone che verrà colorato
	 */
	void repaintPanel(JButton button) {
		for (JButton btn : btnImpostazioni) {
			if (btn.equals(button)) {
				btn.setBackground(Color.cyan);
			} else {
				btn.setBackground(null);
			}
		}
	}

	/**
	 * Funzione che idenditifica il bottone
	 * 
	 * @param button: Bottone da identificare
	 * @return i intero che identifica il bottone
	 */
	int getTipoBtn(JButton button) {
		for (int i = 0; i < btnImpostazioni.length; i++) {
			if (btnImpostazioni[i].equals(button)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Finestra per la modifica del coperto
	 */
	class FinestraModificaCoperto extends JDialog {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4366265631121266798L;
		/**
		 * Text area per la modifica del prezzo
		 */
		private JTextArea txtAreaPrezzo;
		private JLabel lblErrore;

		public FinestraModificaCoperto() {

			setSize(new Dimension(300, 200));

			setTitle("Modifica Coperto");
			setSize(300, 200);
			setLocationRelativeTo(null); // Posiziona al centro dello schermo
			setVisible(true);
			setAlwaysOnTop(true);

			JPanel panelPrincipale = new JPanel(new BorderLayout());

			JPanel panelModifica = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
			JPanel panelBtn = new JPanel(new GridLayout(1, 2));
			JPanel panelSud = new JPanel(new GridLayout(2, 1));
			String prezzoFormattato = formattaPrezzo();

			lblErrore = new JLabel();
			lblErrore.setForeground(Color.red);

			JLabel lblCoperto = new JLabel("Nuovo Prezzo: ");
			JLabel lblEuro = new JLabel("€");
			lblEuro.setFont(new Font(getFont().getName(), Font.PLAIN, 25));
			lblCoperto.setFont(new Font(getFont().getName(), Font.PLAIN, 25));

			txtAreaPrezzo = new JTextArea(prezzoFormattato);
			txtAreaPrezzo.setPreferredSize(new Dimension(120, 30));
			txtAreaPrezzo.setFont(new Font(getFont().getName(), Font.PLAIN, 25));

			panelModifica.add(lblCoperto);
			panelModifica.add(txtAreaPrezzo);
			panelModifica.add(lblEuro);

			JButton btnAnnulla = new JButton("Annulla");
			JButton btnConferma = new JButton("Conferma");

			btnAnnulla.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

			btnConferma.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					Double prezzo;
					try {
						prezzo = getPrezzo();
					} catch (NessunPrezzo e1) {
						sendErrore("Inserire un valore");
						return;
					} catch (PrezzoNegativo e2) {
						sendErrore("Valori negativi non validi");
						return;
					} catch (Exception e1) {
						sendErrore("Inserire un prezzo");
						return;
					}

					Logico.setPrezzoCoperto(prezzo);

					dispose();
				}

				private void sendErrore(String string) {
					lblErrore.setText(string);
					Timer timer = new Timer(10000, new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							lblErrore.setText("");
						}
					});

					timer.setRepeats(false);
					timer.start();
				}
			});

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					enableFrame(true);
				}
			});

			panelBtn.add(btnAnnulla);
			panelBtn.add(btnConferma);

			panelSud.add(lblErrore);
			panelSud.add(panelBtn);

			panelPrincipale.add(panelModifica, BorderLayout.CENTER);
			panelPrincipale.add(panelSud, BorderLayout.SOUTH);

			add(panelPrincipale);
		}

		/**
		 * @return il prezzo formattato come 5,60
		 */
		private String formattaPrezzo() {
			double prezzoAttuale = DataService.getPrezzoCoperto();
			DecimalFormat format = new DecimalFormat("#,##0.00");
			String prezzoFormattato = format.format(prezzoAttuale);
			return prezzoFormattato;
		}

		/**
		 * @return il prezzo scritto nella text area
		 * @throws NessunPrezzo       se il text area è vuoto
		 * @throws PrezzoNonLeggibile se il prezzo non è identificabile come double
		 * @throws PrezzoNegativo     se il prezzo è negativo o pari a zero
		 * @throws Exception          sempre se il prezzo non è identificabile come
		 *                            numero
		 * 
		 */
		private double getPrezzo() throws NessunPrezzo, PrezzoNonLeggibile, PrezzoNegativo, Exception {
			String strPrezzo = txtAreaPrezzo.getText().trim();
			if (strPrezzo.isBlank()) {
				throw new NessunPrezzo();
			}

			Double prezzo = 0.0;

			try {
				prezzo = Double.parseDouble(strPrezzo);
			} catch (NumberFormatException e) {

				if (strPrezzo.contains(",")) {

					String[] stringhe = strPrezzo.split(",");

					if (stringhe.length != 2) {
						throw new PrezzoNonLeggibile();
					}

					int intero = Integer.parseInt(stringhe[0]);
					int decimale = Integer.parseInt(stringhe[1]);

					prezzo = Double.parseDouble(intero + "." + decimale);

				} else {
					throw new PrezzoNonLeggibile();
				}

			} catch (Exception e) {
				System.out.println("Questa non so il perché");
			}

			if (prezzo <= 0) {
				throw new PrezzoNegativo();
			}

			return prezzo;
		}

		class NessunPrezzo extends RuntimeException {

			private static final long serialVersionUID = -8372597240413431804L;

		}

		class PrezzoNonLeggibile extends RuntimeException {

			private static final long serialVersionUID = -3945114919717609997L;
		}

		class PrezzoNegativo extends RuntimeException {

			private static final long serialVersionUID = -2813490593683871936L;

		}

	}

}
