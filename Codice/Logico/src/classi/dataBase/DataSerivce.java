package classi.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import mainPackage.CreateDB;
import model.generated.tables.Tavolo;
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
	public List<TavoloRecord> getTavoli() {
		return create.selectFrom(Tavolo.TAVOLO).fetchInto(TavoloRecord.class);
	}

	public void inserisciTavolo(int nomeTavolo, int postiMassimi) {
		TavoloRecord tavolo = new TavoloRecord(nomeTavolo, postiMassimi, 0);
		int result = create.insertInto(Tavolo.TAVOLO).set(tavolo).execute();
		System.out.println("Inserimento tavolo: " + result);
	}

	public void cancellaTavolo(int nomeTavolo) {
		int result = create.deleteFrom(Tavolo.TAVOLO).where(Tavolo.TAVOLO.NOME.eq(nomeTavolo)).execute();
		System.out.println("Eliminazione Tavolo: " + result);
	}

}
