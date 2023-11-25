package mainPackage;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

	public static String DB_REL_FILE = "../DataBaseFile/DataBase.db3";
	public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

	public static void main(String[] args) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");

				Statement stm = conn.createStatement();

				// creaiamo la tabella tavolo

				ResultSet tables = meta.getTables(null, null, "TAVOLO", null);

				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE TAVOLO (" + "NOME	INT PRIMARY KEY," + "POSTI_MASSIMI	INT, STATO INT DEFAULT 0)";
					stm.executeUpdate(str);
					System.out.println("la tabella TAVOLO è stata creata");
				} else {
					System.out.println("La tabella TAVOLO già esiste nel database.");
				}

				// creiamo la tabella piatto
				tables = meta.getTables(null, null, "PIATTO", null);
				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE PIATTO (" + "NOME	TEXT," + "PREZZO	INT , "
							+ "COMPONENTE	TEXT, " + "PRIMARY KEY (NOME , COMPONENTE)" + ")";

					stm.executeUpdate(str);
					System.out.println("la tabella PIATTO è stata creata");

				} else {
					System.out.println("La tabella PIATTO già esiste nel database.");
				}
				
				// creazione tavolo ordine
				tables = meta.getTables(null, null, "ORDINE", null);
				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE ORDINE (" + "NUM_ORDINE	INT,"
							+ "TAVOLO	INT, PRIMARY KEY (NUM_ORDINE, TAVOLO)" + ")";

					stm.executeUpdate(str);
					System.out.println("la tabella ORDINE è stata creata");
				} else {
					System.out.println("La tabella ORDINE già esiste nel database.");
				}

				// Creazione Tabella Piatto_Ordinato
				tables = meta.getTables(null, null, "PIATTO_ORDINATO", null);
				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE PIATTO_ORDINATO (" + "NUM_PIATTO	INT," + "NUM_ORDINE	INT , "
							+ "TAVOLO INT, " + "COMMENTO TEXT, OCCORRENZE INT, " + "PIATTO TEXT, "
							+ " PRIMARY KEY (NUM_PIATTO, NUM_ORDINE , TAVOLO)" + ")";

					stm.executeUpdate(str);
					System.out.println("la tabella PIATTO_ORDINATO è stata creata");
				} else {
					System.out.println("La tabella PIATTO_ORDINATO già esiste nel database.");
				}
				
				// Creazione Tabella Componente
				tables = meta.getTables(null, null, "COMPONENTE", null);
				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE COMPONENTE (" + "NOME	INT" + ")";

					stm.executeUpdate(str);
					System.out.println("la tabella COMPONENTE è stata creata");
				} else {
					System.out.println("La tabella COMPONENTE già esiste nel database.");
				}

				// Creazione Tabella Resoconto Tavolo
				tables = meta.getTables(null, null, "RESOCONTO_TAVOLO", null);
				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE RESOCONTO_TAVOLO (" + "TAVOLO	INT PRIMARY KEY, " + "NUM_COPERTI INT" + ")";

					stm.executeUpdate(str);
					System.out.println("la tabella RESOCONTO_TAVOLO è stata creata");
				} else {
					System.out.println("La tabella RESOCONTO_TAVOLO già esiste nel database.");
				}
				
				conn.close();

			}
			// controllo che il file esista a questo punto
			System.out.println("il file esiste? " + new File(DB_REL_FILE).exists());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
