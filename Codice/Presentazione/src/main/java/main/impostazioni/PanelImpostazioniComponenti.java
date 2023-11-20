package main.impostazioni;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

class PanelImpostazioniComponenti extends JPanel {

	private static final long serialVersionUID = -2936621503776909843L;

	public PanelImpostazioniComponenti() {

		// creazione panel
		JPanel panelSopra = new JPanel(new BorderLayout());
		JPanel panelSotto = new JPanel(new BorderLayout());

		JPanel panelSopraBtn = new JPanel(new BorderLayout());
		JPanel panelSottoBtn = new JPanel(new BorderLayout());
		
		JPanel panelSopraCenter = new JPanel(new BorderLayout());
		
		// creazione label
		JLabel lblModifica = new JLabel("Modifica Componente:");
		JLabel lblAggiungi = new JLabel("Aggiungi Componente:");
		
		// creazione pulsanti
		JButton btnModifica = new JButton("Salva");
		JButton btnAggiungi = new JButton("Salva");
		
		// creazione txt field
		JTextArea txtModifica = new JTextArea(); 
		JTextArea txtAggiungi = new JTextArea(); 
		
		//aggiungiamo gli elementi
		panelSottoBtn.add(btnAggiungi , BorderLayout.EAST);
		panelSopraBtn.add(btnModifica , BorderLayout.EAST);
		

		
		panelSopra.add(panelSopraBtn , BorderLayout.SOUTH);
		panelSotto.add(panelSottoBtn , BorderLayout.SOUTH);

		panelSopra.add(lblModifica , BorderLayout.NORTH);
		panelSotto.add(lblAggiungi , BorderLayout.CENTER);
		
		// creazione split pane
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT , panelSopra , panelSotto);
		
		
		
	}

}
