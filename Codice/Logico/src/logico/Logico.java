package logico;

import java.util.List;

import classi.dataBase.DataService;
import classi.enumerativi.StatoTavolo;
import classi.menu.Componente;
import classi.menu.Piatto;
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
	 * @throws Exception se il numero di coperti è maggiore del numero dei positi
	 *                   disponibili o è miore o uguale a zero
	 */
	public static void occupaTavolo(Tavolo tavolo, int coperti) throws Exception {

		if (coperti <= 0) {
			throw new Exception();
		}

		if (coperti > tavolo.getNumPostiMassimi()) {
			throw new Exception();
		}
		ResocontoTavolo resocontoTavolo = new ResocontoTavolo(coperti);
		DataService.inserisciResocontoTavolo(resocontoTavolo, tavolo);
		DataService.aggiornaStatoTavolo(tavolo, StatoTavolo.OCCUPATO);
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
	 * @throws Exception in caso il tavolo non sia occupato
	 */
	public static void pagaTavolo(Tavolo tavolo) throws Exception {

		List<Tavolo> listaTavoli = DataService.getTavoli();

		for (Tavolo t : listaTavoli) {
			if (t.equals(tavolo) && !(t.getStato().equals(StatoTavolo.OCCUPATO))) {
				throw new Exception("Tavolo non occupato");
			}
		}

		DataService.eliminaRescontoTavolo(tavolo);
		DataService.aggiornaStatoTavolo(tavolo, StatoTavolo.DA_PULIRE);
	}

	/**
	 * Questo metodo serve per aggiornare lo stato del tavolo a Libero
	 * 
	 * @param tavolo il tavolo a cui facciamo riferimento
	 * @throws Exception se il tavolo non esiste a DB oppure il tavolo è in uno
	 *                   stato diverso da DA_PULIRE
	 */
	public static void tavoloPulito(Tavolo tavolo) throws Exception {
		Tavolo t = DataService.getTavolo(tavolo);
		if (t == null) {
			throw new Exception("Tavolo non esiste");
		}
		if(!t.getStato().equals(StatoTavolo.DA_PULIRE)) {
			throw new Exception("Il Tavolo non è da Pulire");
		}
		DataService.aggiornaStatoTavolo(tavolo, StatoTavolo.LIBERO);
	}

	/**
	 * Questo metodo aggiunge al DB un nuovo tavolo
	 * 
	 * @param tavolo il tavolo da aggiungere al data base
	 * @throws Exception in caso il tavolo che si vuole aggiungere è già presente
	 *                   nel DB
	 */
	public static void aggiungiTavolo(Tavolo tavolo) throws Exception {
		Tavolo t = DataService.getTavolo(tavolo);
		
		if(t != null) {
			throw new Exception("Il tavolo esiste già");
		}
		DataService.inserisciTavolo(tavolo);
	}

	/**
	 * Questo metodo prevede la modifica del tavolo
	 * 
	 * @param tavolo il tavolo da modificare
	 */
	public static void modificaTavolo(Tavolo tavoloVecchio, Tavolo tavoloNuovo) throws Exception {
		DataService.aggiornaTavolo(tavoloVecchio , tavoloNuovo);

	}

	/**
	 * Questo metodo serve per eliminare dal DB un tavolo
	 * 
	 * @param tavolo il tavolo da eliminare
	 * @throws Exception in caso il tavolo da eliminare non esiste
	 */
	public static void eliminaTavolo(Tavolo tavolo) throws Exception {
		Tavolo t = DataService.getTavolo(tavolo);
		
		if(t == null) {
			throw new Exception("Il tavolo non esiste");
		}
		
		DataService.eliminaTavolo(tavolo);
	}

	/**
	 * @param compVecchia componente vecchia
	 * @param compNuova   componente nuova
	 * @throws Exception
	 */
	public static void modificaComponente(Componente compVecchia, Componente compNuova) throws Exception {
		DataService.aggiornaNomeComponente(compVecchia, compNuova);
	}

	/**
	 * Questo metodo prevede l'aggiunta della componente
	 * 
	 * @param componente la componente da eliminare
	 * @throws Exception in caso la componente non esistesse
	 */
	public static void eliminaCompondente(Componente componente) throws Exception {
		DataService.eliminaComponente(componente);
		riorganizzaComponenti();
	}

	private static void riorganizzaComponenti() {
		List<Componente> listaCompoenti = DataService.getComponenti();
		int precendente = 0;
		for (Componente componente : listaCompoenti) {
			if (++precendente != componente.getPrecendenza()) {
				componente.setPrecendenza(precendente);
			}
		}
		for (Componente componente : listaCompoenti) {
			DataService.aggiornaPrecedenzaComponente(componente);
		}
	}

	/**
	 * Questo metodo serve ad aggingere una nuovoa componente
	 * 
	 * @param componente la componente da aggiungere
	 * @throws Exception in caso esistesse già la componente
	 */
	public static void aggiungiComponente(Componente componente) throws Exception {
		if (componente.getNome().isBlank()) {
			throw new Exception();
		}
		int n = DataService.getComponenti().size();
		componente.setPrecendenza(n + 1);
		DataService.inserisciComponente(componente);
	}

	/**
	 * Questo metodo serve per eliminare il piatto da database
	 * 
	 * @param piattoSelezionato il piatto selezionato
	 * @param componente        la componente del piatto
	 * @throws Exception in caso non esistesse il piatto
	 */
	public static void eliminaPiatto(Piatto piattoSelezionato, Componente componente) throws Exception {
		Piatto p = DataService.getPiatto(piattoSelezionato, componente);
		
		if(p == null) {
			throw new Exception("Il Piatto non esiste");
		}
		
		DataService.eliminaPiatto(piattoSelezionato, componente);
	}

	/**
	 * Questo metodo prevede di aggiungere un piatto al data base
	 * 
	 * @param piatto     il piatto da aggiungere
	 * @param componente la componente da aggiungere
	 * @throws Exception in caso il piatto esistesse già
	 */
	public static void aggiungiPiatto(Piatto piatto, Componente componente) throws Exception {
		DataService.inserisciPiatto(piatto, componente);
	}

	/**
	 * Questo metodo prevede la modifica di un piatto
	 * 
	 * @param piattoVecchio il piatto da cambiare
	 * @param piattoNuovo   il piatto nuovo
	 * @param componente    la componente del piatto
	 */
	public static void modificaPiatto(Piatto piattoVecchio, Piatto piattoNuovo, Componente componente)
			throws Exception {
		DataService.modificaPiatto(piattoVecchio, piattoNuovo, componente);
	}

	/**
	 * @param componente la componente che deve incrementare di precedenza
	 */
	public static void incrementaPrecendeza(Componente componente) {
		List<Componente> listaComponenti = DataService.getComponenti();

		// se la componente è la prima non si fa nulla
		if (componente.getPrecendenza() > 1) {
			Componente componente1 = listaComponenti.get(componente.getPrecendenza() - 1);
			Componente componente2 = listaComponenti.get(componente.getPrecendenza() - 2);

			int precedenza1 = componente1.getPrecendenza();
			componente1.setPrecendenza(componente2.getPrecendenza());
			componente2.setPrecendenza(precedenza1);

			DataService.aggiornaPrecedenzaComponente(componente1);
			DataService.aggiornaPrecedenzaComponente(componente2);
		}
	}

	/**
	 * @param componente la componente che deve incrementare di precedenza
	 */
	public static void decrementaPrecendeza(Componente componente) {
		List<Componente> listaComponenti = DataService.getComponenti();
		
		componente = DataService.getComponente(componente);
		
		int sizeList = listaComponenti.size();
		
		// se la componente è l'ultima non si fa nulla
		if (componente.getPrecendenza() < sizeList) {
			Componente componente1 = listaComponenti.get(componente.getPrecendenza() - 1);
			Componente componente2 = listaComponenti.get(componente.getPrecendenza());

			int precedenza1 = componente1.getPrecendenza();
			componente1.setPrecendenza(componente2.getPrecendenza());
			componente2.setPrecendenza(precedenza1);

			DataService.aggiornaPrecedenzaComponente(componente1);
			DataService.aggiornaPrecedenzaComponente(componente2);
		}
	}

	/**
	 * @param tavolo il tavolo a cui dobbiamo calcolare il coperto
	 * @return il prezzo totale del coperto
	 */
	public static double calcolaPrezzoCoperto(Tavolo tavolo) {
		int numeroCoperti = DataService.getResocontoTavolo(tavolo).getNumCoperti();
		double prezzoCoperto = DataService.getPrezzoCoperto();
		return numeroCoperti * prezzoCoperto;
	}
	

	/**
	 * Questo metodo aggiorna il prezzo del coperto
	 * 
	 * @param prezzo il prezzo nuovo
	 */
	public static void setPrezzoCoperto(Double prezzo) {
		DataService.aggiornaPrezzoCoperto(prezzo);
	}
}
