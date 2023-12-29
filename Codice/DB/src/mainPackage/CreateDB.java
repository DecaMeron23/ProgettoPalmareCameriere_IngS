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
					String str = "CREATE TABLE PIATTO (" + "NOME	TEXT," + "PREZZO	DOUBLE , "
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
							+ "TAVOLO	INT , COUNTER_PIATTO_ORDINATO INT, PRIMARY KEY (NUM_ORDINE, TAVOLO)" + ")";

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
					String str = "CREATE TABLE COMPONENTE ( NOME	TEXT PRIMARY KEY )";

					stm.executeUpdate(str);
					System.out.println("la tabella COMPONENTE è stata creata");
				} else {
					System.out.println("La tabella COMPONENTE già esiste nel database.");
				}

				// Creazione Tabella Resoconto Tavolo
				tables = meta.getTables(null, null, "RESOCONTO_TAVOLO", null);
				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE RESOCONTO_TAVOLO (" + "TAVOLO	INT PRIMARY KEY, " + "NUM_COPERTI INT, COUNTER_ORDINI INT" +  ")";

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
		
		popolaDB();
	}

	private static void popolaDB() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Popola la tabella TAVOLO
            popolaTavolo(conn);

            // Popola la tabella COMPONENTE
            popolaComponente(conn);
            
            // Popola la tabella PIATTO
            popolaPiatto(conn);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void popolaTavolo(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Inserisci dati nella tabella TAVOLO
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('1', 4, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('2', 6, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('3', 6, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('4', 4, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('5', 4, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('6', 4, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('7', 4, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('8', 4, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('9', 4, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('10', 2, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('11', 2, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('12', 2, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('13', 2, 0)");
            stmt.executeUpdate("INSERT INTO TAVOLO (NOME, POSTI_MASSIMI, STATO) VALUES ('14', 2, 0)");
            // Aggiungi altri inserimenti secondo necessità
        }
    }

    private static void popolaPiatto(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Inserisci dati nella tabella PIATTO
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pasta al Sugo', 10.99, 'Primi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pasta alla Carbonara', 15.99, 'Primi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pasta al Pesto', 11.99, 'Primi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pasta e vongole', 14.99, 'Primi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Aglio e Olio', 11.99, 'Primi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Riso alla Milanese', 12.99, 'Primi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pasta in Bianco', 9.99, 'Primi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Coniglio', 17.99, 'Secondi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Bistecca', 18.99, 'Secondi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Salmone', 19.99, 'Secondi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Fritto Misto', 16.99, 'Secondi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Grigliata Mista', 17.99, 'Secondi')");
            stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Verdure Gliliate', 13.99, 'Secondi')");
            // Aggiungi altri inserimenti secondo necessità
        }
    }

    private static void popolaComponente(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Inserisci dati nella tabella COMPONENTE
            stmt.executeUpdate("INSERT INTO COMPONENTE (NOME) VALUES ('Primi')");
            stmt.executeUpdate("INSERT INTO COMPONENTE (NOME) VALUES ('Secondi')");
            // Aggiungi altri inserimenti secondo necessità
        }
    }
	
	
}

