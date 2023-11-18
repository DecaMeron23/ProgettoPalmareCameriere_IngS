package GUI;

import java.awt.BorderLayout;
import java.lang.invoke.SerializedLambda;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class Panel_Impostazioni_Componenti extends JPanel {

	private static final long serialVersionUID = -2936621503776909843L;

	public Panel_Impostazioni_Componenti() {

		// creazione panel
		JPanel panel_sopra = new JPanel(new BorderLayout());
		JPanel panel_sotto = new JPanel(new BorderLayout());

		JPanel panel_sopra_btn = new JPanel(new BorderLayout());
		JPanel panel_sotto_btn = new JPanel(new BorderLayout());
		
		JPanel panel_sopra_center = new JPanel(new BorderLayout());
		
		// creazione label
		JLabel lbl_modifica = new JLabel("Modifica Componente:");
		JLabel lbl_aggiungi = new JLabel("Aggiungi Componente:");
		
		// creazione pulsanti
		JButton btn_modifica = new JButton("Salva");
		JButton btn_aggiungi = new JButton("Salva");
		
		// creazione txt field
		JTextArea txt_modifica = new JTextArea(); 
		JTextArea txt_aggiungi = new JTextArea(); 
		
		//aggiungiamo gli elementi
		panel_sotto_btn.add(btn_aggiungi , BorderLayout.EAST);
		panel_sopra_btn.add(btn_modifica , BorderLayout.EAST);
		

		
		panel_sopra.add(panel_sopra_btn , BorderLayout.SOUTH);
		panel_sotto.add(panel_sotto_btn , BorderLayout.SOUTH);

		panel_sopra.add(lbl_modifica , BorderLayout.NORTH);
		panel_sotto.add(lbl_aggiungi , BorderLayout.CENTER);
		
		// creazione split pane
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT , panel_sopra , panel_sotto);
		
		
		
	}

}
