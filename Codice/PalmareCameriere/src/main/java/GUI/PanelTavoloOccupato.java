package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import Classi.Componente;
import Classi.PiattoOrdinato;
import Classi.ResocontoTavolo;
import Classi.Tavolo;

public class PanelTavoloOccupato extends JPanel {

	private static final long serialVersionUID = 3149564893779333739L;

	private static final int POS_SEPARAZIONE_DX_SX = 550;

	PiattoOrdinato piatto_ordinato_attuale;
	List<PiattoOrdinato> lista_piatti_ordinati;
	Tavolo tavolo;

	private Panel_Ordini_Tavolo_Occupato panel_sx;
	private Panel_Componenti_Tavolo_Occupato panel_dx;

	public PanelTavoloOccupato(Tavolo tavolo, List<Componente> lista_componenti) {
		
		this.tavolo= tavolo; 
		lista_piatti_ordinati = new ArrayList<>();

		panel_sx = new Panel_Ordini_Tavolo_Occupato(this);
		panel_dx = new Panel_Componenti_Tavolo_Occupato(lista_componenti, this);

		// setting Layout
		setLayout(new BorderLayout());

		JSplitPane split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_sx, panel_dx);
		split_pane.setDividerLocation(POS_SEPARAZIONE_DX_SX);
		split_pane.setEnabled(false);
		add(split_pane);
		
		repaint_panel();
	}

	private void repaint_panel() {
		panel_dx.repaint_panel();
		panel_sx.repaint_panel();
	}

	public void aggiungi_piatto(Text_Commento_Piatto commento_piatto) {
		// aggiungiamo il commento(se inseirito) nel piatto_ordinato_attuale
		piatto_ordinato_attuale.setCommento(commento_piatto.getText());
		// resettiamo i commenti
		commento_piatto.reset();
		// salviamo il piatto nella lista dei piatti ordinati
		lista_piatti_ordinati.add(piatto_ordinato_attuale);
		// resettiamo il piatto_ordinato_attuale
		piatto_ordinato_attuale = null;
		// aggiorniamo il panel_DX
		panel_dx.repaint_panel();
		// il panel_SX
		panel_sx.repaint_panel();
	}

}
