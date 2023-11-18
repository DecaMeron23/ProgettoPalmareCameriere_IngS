package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Classi.Componente;
import Classi.PiattoOrdinato;
import Classi.Tavolo;

/**
 * Classe che estende JPanel, riguarda il panel principale per fare le
 * ordinazioni su un tavolo specifico
 */
public class Panel_Tavolo_Occupato extends JPanel {

	private static final long serialVersionUID = 3149564893779333739L;

	/**
	 * posizione in cui si ha la barra di separazione tra i due pannelli
	 */
	private static final int POS_SEPARAZIONE_DX_SX = 550;

	/**
	 * piatto che abbiamo selezionato nel preciso istante
	 */
	PiattoOrdinato piatto_selezionato;
	/**
	 * lista di piatti che abbiamo selezionato
	 */
	List<PiattoOrdinato> lista_piatti_ordinati;
	/**
	 * Il tavolo a cui fa riferimento questo panel
	 */
	Tavolo tavolo;

	/**
	 * Panel sinistro , contiene lo storico dei ordini e l'ordine attuale
	 */
	private Panel_SX_Tavolo_Occupato panel_sx;

	/**
	 * Panel destro, serve per aggiungere piatti all'ordine attuale ed eventuali commenti
	 */
	private Panel_DX_Tavolo_Occupato panel_dx;

	/**
	 * @param tavolo: il tavolo a cui fa riferimento questo panel
	 * @param lista_componenti: lista che contiene i diversi componenti
	 */
	public Panel_Tavolo_Occupato(Tavolo tavolo, List<Componente> lista_componenti) {

		this.tavolo = tavolo;
		lista_piatti_ordinati = new ArrayList<>();

		// creo i due panel
		panel_sx = new Panel_SX_Tavolo_Occupato(this);
		panel_dx = new Panel_DX_Tavolo_Occupato(lista_componenti, this);

		// impostiamo il Layout del panel
		setLayout(new BorderLayout());

		// creaiamo lo split pane per dividere il pane principale in due
		JSplitPane split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_sx, panel_dx);
		
		// impostiamo la posizione del separatore, blocchiamo il separatore e lo aggiungiamo al panel
		split_pane.setDividerLocation(POS_SEPARAZIONE_DX_SX);
		split_pane.setEnabled(false);
		add(split_pane);

		// aggiorniamo il panel
		repaint_panel();
	}

	/**
	 *  serve per aggiornare il panel
	 */
	private void repaint_panel() {
		panel_dx.repaint_panel();
		panel_sx.repaint_panel();
	}

	/**
	 * @param commento_piatto: commento del piatto selezionato
	 */
	public void aggiungi_piatto(Text_Commento_Piatto commento_piatto) {
		// aggiungiamo il commento(se inseirito) nel piatto_ordinato_attuale
		piatto_selezionato.setCommento(commento_piatto.getText());
		// resettiamo i commenti
		commento_piatto.reset();
		// salviamo il piatto nella lista dei piatti ordinati (in testa alla lista)
		lista_piatti_ordinati.add(0 , piatto_selezionato);
		// resettiamo il piatto_ordinato_attuale
		piatto_selezionato = null;
		// aggiorniamo il panel
		repaint_panel();
	}
}