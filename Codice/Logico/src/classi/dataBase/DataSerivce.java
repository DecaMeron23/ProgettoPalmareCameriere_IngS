package classi.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import classi.tavolo.Tavolo;
import mainPackage.CreateDB;
import model.generated.tables.TavoloTables;
import model.generated.tables.records.TavoloRecord;

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
		List<Tavolo> listaTavoli = new ArrayList<Tavolo>();
		for (TavoloRecord tavoloRecord : listaRecord) {
			listaTavoli.add(Record2Class.tavolo(tavoloRecord));
		}
		return listaTavoli;
	}

	public void inserisciTavolo(Tavolo tavolo) {
		TavoloRecord tavoloRecord = Class2Record.tavolo(tavolo);
		int result = create.insertInto(TavoloTables.TAVOLO).set(tavoloRecord).execute();
		System.out.println("Inserimento tavolo: " + result);
	}

	public void cancellaTavolo(Tavolo tavolo) {
		int result = create.deleteFrom(TavoloTables.TAVOLO).where(TavoloTables.TAVOLO.NOME.eq(tavolo.getNome()))
				.execute();
		System.out.println("Eliminazione Tavolo: " + result);
	}

}
