package classi.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import classi.menu.Componente;
import classi.menu.Piatto;
import classi.ordine.Ordine;
import classi.ordine.PiattoOrdinato;
import classi.tavolo.ResocontoTavolo;
import classi.tavolo.Tavolo;
import mainPackage.CreateDB;
import model.generated.tables.ComponenteTables;
import model.generated.tables.OrdineTables;
import model.generated.tables.PiattoOrdinatoTables;
import model.generated.tables.PiattoTables;
import model.generated.tables.ResocontoTavoloTables;
import model.generated.tables.TavoloTables;
import model.generated.tables.records.ComponenteRecord;
import model.generated.tables.records.OrdineRecord;
import model.generated.tables.records.PiattoOrdinatoRecord;
import model.generated.tables.records.PiattoRecord;
import model.generated.tables.records.ResocontoTavoloRecord;
import model.generated.tables.records.TavoloRecord;


/**
 * 
 */
public class DataSerivce {

	private Connection conn;

	private DSLContext create;

	public DataSerivce() {
		try {
			conn = DriverManager.getConnection(CreateDB.DB_URL);
			create = DSL.using(conn, SQLDialect.SQLITE);
		} catch (SQLException e) {
			System.out.println("ERROR: failed to connect!");
			System.out.println("ERROR: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

	/**
	 * Questo metodo interroga il db ed estrai tutti i tavoli
	 * 
	 * @return lista dei tavoli
	 */
	public List<Tavolo> getTavoli() {
		List<TavoloRecord> listaRecord = create.selectFrom(TavoloTables.TAVOLO).fetchInto(TavoloRecord.class);
		return toListaTavoli(listaRecord);
	}

	/**
	 * @param tavolo il tavolo da inserire
	 */
	public void inserisciTavolo(Tavolo tavolo) {
		TavoloRecord tavoloRecord = Class2Record.tavolo(tavolo);
		int result = create.insertInto(TavoloTables.TAVOLO).set(tavoloRecord).execute();
		System.out.println("Inserimento tavolo: " + result);
	}

	/**
	 * @param tavolo il tavolo da eliminare
	 */
	public void cancellaTavolo(Tavolo tavolo) {
		int result = create.deleteFrom(TavoloTables.TAVOLO).where(TavoloTables.TAVOLO.NOME.eq(tavolo.getNome()))
				.execute();
		System.out.println("Eliminazione Tavolo: " + result);
	}

	/**
	 * @param c la componente dei piatti
	 * @return la lista dei piatti
	 */
	public List<Piatto> getPiatti(Componente c) {
		List<PiattoRecord> listaRecord = create.selectFrom(PiattoTables.PIATTO)
				.where(PiattoTables.PIATTO.COMPONENTE.eq(c.getNome())).fetchInto(PiattoRecord.class);

		return toListaPiatti(listaRecord);
	}
	
	/**
	 * @param p il nome del piatto
	 * @return il piatto
	 */
	private Piatto getPiatto(String p) {
		PiattoRecord piattoRecord = create.selectFrom(PiattoTables.PIATTO).where(PiattoTables.PIATTO.NOME.eq(p)).fetchInto(PiattoRecord.class).get(0);
		return Record2Class.piatto(piattoRecord);
	}

	/**
	 * Inserisci un piatto nel data base
	 *
	 * @param p il piatto da inserire
	 * @parem c la componente in cui si deve aggiungere il piatto
	 * 
	 */
	public void inserisciPiatto(Piatto p, Componente c) {
		PiattoRecord piattoRecord = Class2Record.piatto(p, c);
		int result = create.insertInto(PiattoTables.PIATTO).set(piattoRecord).execute();
		System.out.println("Inserimento Piatto: " + result);
	}

	/**
	 * Questo metodo elimina un piatto da una componente
	 * 
	 * @param p il piatto da eliminare
	 * @param c la componente cui il piatto fa parte
	 */
	public void eliminaPiatto(Piatto p, Componente c) {
		int result = create.deleteFrom(PiattoTables.PIATTO)
				.where(PiattoTables.PIATTO.NOME.eq(p.getNome()).and(PiattoTables.PIATTO.COMPONENTE.eq(c.getNome())))
				.execute();
		System.out.println("Eliminazione piatto: " + result);
	}

	/**
	 * @return la lista delle componenti
	 */
	public List<Componente> getComponenti() {
		List<ComponenteRecord> listaRecords = create.selectFrom(ComponenteTables.COMPONENTE)
				.fetchInto(ComponenteRecord.class);
		return toListComponente(listaRecords);
	}

	/**
	 * Questo metodo agginge una componente al data base
	 * 
	 * @param c la componente da aggingere
	 */
	public void inserisciComponente(Componente c) {
		ComponenteRecord componenteRecord = Class2Record.componente(c);
		int result = create.insertInto(ComponenteTables.COMPONENTE).set(componenteRecord).execute();
		System.out.println("Inserimento componente: " + result);
	}

	/**
	 * Questo metodo elimina dal data base la componente e i relativi piatti della
	 * componente
	 * 
	 * @param c la componente da eliminare
	 */
	public void eliminaComponente(Componente c) {
		List<Piatto> listaPiatti = getPiatti(c);
		for (Piatto piatto : listaPiatti) {
			eliminaPiatto(piatto, c);
		}

		int result = create.deleteFrom(ComponenteTables.COMPONENTE)
				.where(ComponenteTables.COMPONENTE.NOME.eq(c.getNome())).execute();
		System.out.println("Eliminazione Componente: " + result);
	}

	/**
	 * @param t il tavolo cui vogliamo prendere il resoconto
	 * @return il resoconto del tavolo
	 */
	public ResocontoTavolo getResocontoTavolo(Tavolo t) {
		ResocontoTavoloRecord resocontoTavoloRecord = create.selectFrom(ResocontoTavoloTables.RESOCONTO_TAVOLO)
				.where(ResocontoTavoloTables.RESOCONTO_TAVOLO.TAVOLO.eq(t.getNome()))
				.fetchInto(ResocontoTavoloRecord.class).get(0);

		List<Ordine> listaOrdini = getOrdini(t);
		ResocontoTavolo resocontoTavolo = Record2Class.resocontoTavolo(resocontoTavoloRecord);
		resocontoTavolo.setListaOrdini(listaOrdini);
		return resocontoTavolo;

	}

	public void inserisciResocontoTavolo(ResocontoTavolo r, Tavolo t) {
		ResocontoTavoloRecord resocontoRecord = Class2Record.resocontoTavolo(r, t);
		int result = create.insertInto(ResocontoTavoloTables.RESOCONTO_TAVOLO).set(resocontoRecord).execute();
		System.out.println("Inserimento resoconto tavolo: " + result);
	}

	/**
	 * @param t il tavolo a cui vogliamo eliminare il resoconto
	 */
	public void eliminaRescontoTavolo(Tavolo t) {
		eliminaOrdineTavolo(t);
		int result = create.deleteFrom(ResocontoTavoloTables.RESOCONTO_TAVOLO)
				.where(ResocontoTavoloTables.RESOCONTO_TAVOLO.TAVOLO.eq(t.getNome())).execute();
		System.out.println("Eliminazione rescoconto tavolo: " + result);
	}

	/**
	 * @param t il tavolo a cui l'ordine fa riferimento
	 * @return la lista degli ordini
	 */
	public List<Ordine> getOrdini(Tavolo t) {
		List<OrdineRecord> listaRecord = create.selectFrom(OrdineTables.ORDINE)
				.where(OrdineTables.ORDINE.TAVOLO.eq(t.getNome())).fetchInto(OrdineRecord.class);
		List<Ordine> listaOrdini = new ArrayList<Ordine>();
		for (OrdineRecord ordineRecord : listaRecord) {
			Ordine ordine = Record2Class.ordine(ordineRecord, getPiattiOridinati(t, ordineRecord.getNumOrdine()));
			listaOrdini.add(ordine);
		}
		return listaOrdini;
	}

	/**
	 * @param o l'ordine da inserire
	 * @param t il tavolo a cui fa riferimento l'ordine
	 */
	public void inserisciOrdine(Ordine o, Tavolo t) {
		OrdineRecord ordineRecord = Class2Record.ordine(o, t);
		int result = create.insertInto(OrdineTables.ORDINE).set(ordineRecord).execute();
		for (PiattoOrdinato piatto : o.getListaPiattiOrdinati()) {
			inserisciPiattoOrdinato(piatto, o, t);
		}

		System.out.println("Inserimento Ordine: " + result);

	}

	/**
	 * @param t il tavolo da cui vogliamo eliminare gli ordini
	 */
	private void eliminaOrdineTavolo(Tavolo t) {
		eliminaPiattiOrdinati(t);
		int result = create.deleteFrom(OrdineTables.ORDINE).where(OrdineTables.ORDINE.TAVOLO.eq(t.getNome())).execute();
		System.out.println("Eliminazione ordine del tavolo: " + result);
	}

	/**
	 * @param p il piatto ordinato da inserire nel DB
	 * @param t il tavolo a cui fa riferimento l'ordine
	 * @param o l'ordine a cui fa riferimento il piatto ordinato
	 */
	private void inserisciPiattoOrdinato(PiattoOrdinato p, Ordine o, Tavolo t) {
		PiattoOrdinatoRecord piattoRecord = Class2Record.piattoOrdinato(p, o, t);
		int result = create.insertInto(PiattoOrdinatoTables.PIATTO_ORDINATO).set(piattoRecord).execute();
		System.out.println("Inserimento piatto ordinato: " + result);
	}

	/**
	 * @param t il tavolo a cui vogliamo eliminare i piatti
	 */
	private void eliminaPiattiOrdinati(Tavolo t) {
		int result = create.deleteFrom(PiattoOrdinatoTables.PIATTO_ORDINATO)
				.where(PiattoOrdinatoTables.PIATTO_ORDINATO.TAVOLO.eq(t.getNome())).execute();
		System.out.println("Eliminazione piatti ordinati: " + result);
	}

	/**
	 * @param t il tavolo a cui facciamo riferimeno
	 * @param numeroOrdine il numero dell'ordine dei piatti
	 * @return la lista dei piatti ordinati per un certo ordine
	 */
	public List<PiattoOrdinato> getPiattiOridinati(Tavolo t, int numeroOrdine) {
		List<PiattoOrdinatoRecord> listaRecord = create.selectFrom(PiattoOrdinatoTables.PIATTO_ORDINATO)
				.where(PiattoOrdinatoTables.PIATTO_ORDINATO.TAVOLO.eq(t.getNome()))
				.and(PiattoOrdinatoTables.PIATTO_ORDINATO.NUM_ORDINE.eq(numeroOrdine))
				.fetchInto(PiattoOrdinatoRecord.class);
		List<PiattoOrdinato> lista = new ArrayList<PiattoOrdinato>();
		for (PiattoOrdinatoRecord piattoOrdinatoRecord : listaRecord) {
			lista.add(Record2Class.piattoOrdinato(piattoOrdinatoRecord , getPiatto(piattoOrdinatoRecord.getPiatto())));
		}
		return lista;
	}

	/**
	 * Questo metodo trasforma una lista di record tavoli in una lista di Tavoli
	 * 
	 * @param listaRecord da trasformare
	 * @return la lista dei tavoli
	 */
	private List<Tavolo> toListaTavoli(List<TavoloRecord> listaRecord) {
		List<Tavolo> listaTavoli = new ArrayList<Tavolo>();
		for (TavoloRecord tavoloRecord : listaRecord) {
			listaTavoli.add(Record2Class.tavolo(tavoloRecord));
		}
		return listaTavoli;
	}

	/**
	 * @param listaRecord
	 * @return
	 */
	private List<Piatto> toListaPiatti(List<PiattoRecord> listaRecord) {
		List<Piatto> listaPiatti = new ArrayList<>();
		for (PiattoRecord piattoRecord : listaRecord) {
			listaPiatti.add(Record2Class.piatto(piattoRecord));
		}
		return listaPiatti;
	}

	/**
	 * Questo metodo trasforma una lista di componenti record in lista di componenti
	 * 
	 * @param listaRecords la lista dei componenti record
	 * @return lista di componenti
	 */
	private List<Componente> toListComponente(List<ComponenteRecord> listaRecords) {
		List<Componente> listaComponenti = new ArrayList<>();
		for (ComponenteRecord componenteRecord : listaRecords) {
			Componente componente = Record2Class.componente(componenteRecord);

			componente.setListaPiatti(getPiatti(componente));

			listaComponenti.add(componente);
		}
		return listaComponenti;
	}

}
