package main.tavoloOccupato.panel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import classi.menu.Componente;
import classi.ordine.PiattoOrdinato;
import classi.tavolo.Tavolo;
import main.tavoloOccupato.TextAreaCommentoPiatto;

/**
 * Classe che estende JPanel, riguarda il panel principale per fare le
 * ordinazioni su un tavolo specifico
 */
public class PanelTavoloOccupato extends JPanel {

	private static final long serialVersionUID = 3149564893779333739L;

	/**
	 * posizione in cui si ha la barra di separazione tra i due pannelli
	 */
	private static final int POS_SEPARAZIONE_DX_SX = 550;

	/**
	 * piatto che abbiamo selezionato nel preciso istante
	 */
	PiattoOrdinato piattoSelezionato;
	/**
	 * lista di piatti che abbiamo selezionato
	 */
	List<PiattoOrdinato> listaPiattiOrdinati;
	/**
	 * Il tavolo a cui fa riferimento questo panel
	 */
	Tavolo tavolo;

	/**
	 * Panel sinistro , contiene lo storico dei ordini e l'ordine attuale
	 */
	private PanelTavoloOccupatoSinistro panelSx;

	/**
	 * Panel destro, serve per aggiungere piatti all'ordine attuale ed eventuali commenti
	 */
	private PanelTavoloOccupatoDestro panelDx;

	/**
	 * @param tavolo: il tavolo a cui fa riferimento questo panel
	 * @param lista_componenti: lista che contiene i diversi componenti
	 */
	public PanelTavoloOccupato(Tavolo tavolo, List<Componente> listaComponenti) {

		this.tavolo = tavolo;
		listaPiattiOrdinati = new ArrayList<>();

		// creo i due panel
		panelSx = new PanelTavoloOccupatoSinistro(this);
		panelDx = new PanelTavoloOccupatoDestro(listaComponenti, this);

		// impostiamo il Layout del panel
		setLayout(new BorderLayout());

		// creaiamo lo split pane per dividere il pane principale in due
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelSx, panelDx);
		
		// impostiamo la posizione del separatore, blocchiamo il separatore e lo aggiungiamo al panel
		splitPane.setDividerLocation(POS_SEPARAZIONE_DX_SX);
		splitPane.setEnabled(false);
		add(splitPane);

		// aggiorniamo il panel
		repaintPanel();
	}

	/**
	 *  serve per aggiornare il panel
	 */
	private void repaintPanel() {
		panelDx.repaintPanel();
		panelSx.repaintPanel();
	}

	/**
	 * @param commento_piatto: commento del piatto selezionato
	 */
	public void aggiungiPiatto(TextAreaCommentoPiatto commentoPiatto) {
		// aggiungiamo il commento(se inseirito) nel piatto_ordinato_attuale
		piattoSelezionato.setCommento(commentoPiatto.getText());
		// resettiamo i commenti
		commentoPiatto.reset();
		// salviamo il piatto nella lista dei piatti ordinati (in testa alla lista)
		listaPiattiOrdinati.add(0 , piattoSelezionato);
		// resettiamo il piatto_ordinato_attuale
		piattoSelezionato = null;
		// aggiorniamo il panel
		repaintPanel();
	}
}