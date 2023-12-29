package logico;

import java.util.List;

import classi.dataBase.DataService;
import classi.enumerativi.StatoTavolo;
import classi.menu.Componente;
import classi.ordine.Ordine;
import classi.ordine.PiattoOrdinato;
import classi.tavolo.ResocontoTavolo;
import classi.tavolo.Tavolo;

/**
 * Parte logica della applicazione
 */
/**
 * 
 */
public class Logico {

	/**
	 * Questo metodo occupa il tavolo
	 * 
	 * @param tavolo  il tavolo da occupare
	 * @param coperti il numero di coperti del tavolo
	 */
	public static void occupaTavolo(Tavolo tavolo, int coperti) {
		ResocontoTavolo resocontoTavolo = new ResocontoTavolo(coperti);
		DataService.inserisciResocontoTavolo(resocontoTavolo, tavolo);
		DataService.aggiornaStatoTavolo(tavolo, StatoTavolo.OCCUPATO);
	}

	/**
	 * Resettiamo il DB alla chiusura dell'App (eliminiamo gli ordini e impostiamo
	 * tutti i tavolo liberi)
	 */
	public static void eseguiAzioniDiChiusura() {
		List<Tavolo> tavoli = DataService.getTavoli();
		for (Tavolo tavolo : tavoli) {
			DataService.eliminaRescontoTavolo(tavolo);
			DataService.aggiornaStatoTavolo(tavolo, StatoTavolo.LIBERO);
		}
	}

	/**
	 * Questo metodo riceve una lista di piatti e un tavolo, poi crea un nuovo
	 * ordine e lo mette le DB
	 * 
	 * @param listaPiattiOrdinati la lista dei piatti da aggiungere all'ordine
	 * @param tavolo              il tavolo a cui si aggiunge l'ordine
	 */
	public static void inviaOrdine(List<PiattoOrdinato> listaPiattiOrdinati, Tavolo tavolo) {
		ResocontoTavolo resoconto = DataService.getResocontoTavolo(tavolo);
		int i = 1;
		// impostiamo l'id dei piatti
		for (PiattoOrdinato piattoOrdinato : listaPiattiOrdinati) {
			piattoOrdinato.setIDPiatto(i++);
		}
		Ordine ordine = new Ordine(listaPiattiOrdinati, resoconto.getNumeroOrdine());
		DataService.aggiornaResoconto(resoconto, tavolo);
		DataService.inserisciOrdine(ordine, tavolo);
	}

	/**
	 * Questo metodo serve per eseguire le operazioni che si effettuano al pagamento
	 * 
	 * @param tavolo il tavolo a cui facciamo riferimento
	 */
	public static void pagaTavolo(Tavolo tavolo) {
		DataService.eliminaRescontoTavolo(tavolo);
		DataService.aggiornaStatoTavolo(tavolo, StatoTavolo.DA_PULIRE);
	}

	/**
	 * Questo metodo serve per aggiornare lo stato del tavolo a Libero
	 * 
	 * @param tavolo il tavolo a cui facciamo riferimento
	 */
	public static void tavoloPulito(Tavolo tavolo) {
		DataService.aggiornaStatoTavolo(tavolo, StatoTavolo.LIBERO);
	}

	/**
	 * Questo metodo aggiunge al DB un nuovo tavolo
	 * 
	 * @param tavolo il tavolo da aggiungere al data base
	 * @throws Exception in caso il tavolo che si vuole aggiungere è già
	 *                          presente nel DB
	 */
	public static void aggiungiTavolo(Tavolo tavolo) throws Exception {
		DataService.inserisciTavolo(tavolo);
	}

	/**
	 * Questo metodo prevede la modifica del tavolo
	 * 
	 * @param tavolo il tavolo da modificare
	 */
	public static void modificaTavolo(Tavolo tavoloVecchio, Tavolo tavoloNuovo) {
		DataService.eliminaTavolo(tavoloVecchio);
		DataService.inserisciTavolo(tavoloNuovo);

	}

	/**
	 * Questo metodo serve per eliminare dal DB un tavolo
	 * 
	 * @param tavolo il tavolo da eliminare
	 * @throws RuntimeException in caso il tavolo da eliminare non esiste
	 */
	public static void eliminaTavolo(Tavolo tavolo) throws Exception {
		DataService.eliminaTavolo(tavolo);
	}

	/**
	 * @param compVecchia componente vecchia
	 * @param compNuova componente nuova
	 * @throws Exception
	 */
	public static void modificaComponente(Componente compVecchia, Componente compNuova) throws Exception{
		DataService.aggiornaNomeComponente(compVecchia , compNuova);
	}

	/**
	 * Questo metodo prevede l'aggiunta della componente
	 * 
	 * @param componente la componente da eliminare
	 * @throws Exception in caso la componente non esistesse
	 */
	public static void eliminaCompondente(Componente componente) throws Exception{
		DataService.eliminaComponente(componente);
	}

	/**
	 * Questo metodo serve ad aggingere una nuovoa componente
	 * 
	 * @param componente la componente da aggiungere
	 * @throws Exception in caso esistesse già la componente
	 */
	public static void aggiungiComponente(Componente componente) throws Exception{
		DataService.inserisciComponente(componente);
	}
	
}
