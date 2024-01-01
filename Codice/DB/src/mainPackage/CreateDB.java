package mainPackage;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
					String str = "CREATE TABLE TAVOLO (" + "NOME	INT PRIMARY KEY,"
							+ "POSTI_MASSIMI	INT, STATO INT DEFAULT 0)";
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
					String str = "CREATE TABLE COMPONENTE ( NOME	TEXT PRIMARY KEY  , PRECEDENZA INT)";

					stm.executeUpdate(str);
					System.out.println("la tabella COMPONENTE è stata creata");
				} else {
					System.out.println("La tabella COMPONENTE già esiste nel database.");
				}

				// Creazione Tabella Resoconto Tavolo
				tables = meta.getTables(null, null, "RESOCONTO_TAVOLO", null);
				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE RESOCONTO_TAVOLO (" + "TAVOLO	INT PRIMARY KEY, "
							+ "NUM_COPERTI INT, COUNTER_ORDINI INT" + ")";

					stm.executeUpdate(str);
					System.out.println("la tabella RESOCONTO_TAVOLO è stata creata");
				} else {
					System.out.println("La tabella RESOCONTO_TAVOLO già esiste nel database.");
				}

				// Creazione Tabella per il prezzo del coperto
				tables = meta.getTables(null, null, "COPERTO", null);
				if (!tables.next()) {
					// La tabella non esiste, quindi possiamo crearla
					String str = "CREATE TABLE COPERTO (PREZZO	DOUBLE )";

					stm.executeUpdate(str);
					System.out.println("la tabella COPERTO è stata creata");
				} else {
					System.out.println("La tabella COPERTO già esiste nel database.");
				}

				conn.close();

			}
			// controllo che il file esista a questo punto
			System.out.println("il file esiste? " + new File(DB_REL_FILE).exists());
			popolaDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void popolaDB() throws Exception {
		System.out.println("Inizio a popolare");
		Connection conn = DriverManager.getConnection(DB_URL);
		// Popola la tabella TAVOLO
		popolaTavolo(conn);

		// Popola la tabella COMPONENTE
		popolaComponente(conn);

		// Popola la tabella PIATTO
		popolaPiatto(conn);
		System.out.println("Fine popolamento");
	}

	private static void popolaTavolo(Connection conn) throws Exception {
		Statement stmt = conn.createStatement();
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

	private static void popolaPiatto(Connection conn) throws Exception {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Linguine alle Vongole', 12.50, 'Primi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Risotto ai Funghi Porcini', 14.00, 'Primi')");

//		stmt.executeUpdate(
//				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Penne all Arrabbiata, 10.00, 'Primi')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Gnocchi al Pesto', 11.50, 'Primi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Spaghetti Carbonara', 10.50, 'Primi')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Lasagna al Forno', 13.00, 'Primi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Tagliatelle ai Frutti di Mare', 15.00, 'Primi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Ravioli con Burro e Salvia', 12.00, 'Primi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Fettuccine Alfredo', 11.00, 'Primi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Minestrone della Casa', 8.00, 'Primi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Cannelloni Ricotta e Spinaci', 13.50, 'Primi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pappardelle al Cinghiale', 16.00, 'Primi')");

		// i secondi
		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Filetto di Manzo Grigliato', 18.00, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Salmone alla Griglia', 16.50, 'Secondi')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pollo al Limone', 14.00, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Cotoletta alla Milanese', 13.50, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Brasato di Vitello', 17.50, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pesce Spada alla Siciliana', 19.00, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Ossobuco con Polenta', 20.00, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Trancio di Tonno Scottato', 15.50, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Galletto Arrosto', 12.00, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Costine di Maiale al Barbecue', 14.50, 'Secondi')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Baccalà alla Vicentina', 16.50, 'Secondi')");

//		stmt.executeUpdate(
//				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Anatra all'Arancia', 19.50, 'Secondi')");

		System.out.println("Fine Primi");
		// i contorni
		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Insalata di Rucola e Pomodori', 6.00, 'Contorni')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Patate Arrosto', 4.50, 'Contorni')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Verdure Grigliate', 5.50, 'Contorni')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Purè di Patate', 5.00, 'Contorni')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Insalata Mista', 5.50, 'Contorni')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Funghi Trifolati', 6.50, 'Contorni')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Zucchine Gratin', 7.00, 'Contorni')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Caponata Siciliana', 7.50, 'Contorni')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Cipolline Caramellate', 6.00, 'Contorni')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Carciofi alla Romana', 8.00, 'Contorni')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Melanzane alla Griglia', 6.50, 'Contorni')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Asparagi al Burro', 7.00, 'Contorni')");

		// Dolci
		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Tiramisù', 6.50, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Panna Cotta con Frutti di Bosco', 7.00, 'Dolci')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Cannoli Siciliani', 8.00, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Cioccolato Fondente al Peperoncino', 7.50, 'Dolci')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Torta al Limone', 6.00, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Profiteroles alla Crema', 7.50, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Mousse al Cioccolato', 8.50, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Cheesecake ai Frutti di Bosco', 9.00, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Gelato alla Vaniglia con Cioccolato Caldo', 6.50, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Torta di Mele con Gelato alla Cannella', 8.50, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pere al Vino Rosso con Gelato', 7.00, 'Dolci')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Sformato di Cioccolato Bianco', 9.50, 'Dolci')");

		// Pizze
		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Margherita', 9.00, 'Pizze')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Prosciutto e Funghi', 10.50, 'Pizze')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Quattro Formaggi', 11.00, 'Pizze')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Vegetariana', 10.00, 'Pizze')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Capricciosa', 12.00, 'Pizze')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Diavola', 11.50, 'Pizze')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Calzone', 12.50, 'Pizze')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pizza ai Frutti di Mare', 13.00, 'Pizze')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pizza BBQ', 12.50, 'Pizze')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pizza Bianca con Patate e Rosmarino', 11.50, 'Pizze')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pizza al Salame Piccante', 11.00, 'Pizze')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pizza con Gorgonzola e Noci', 12.50, 'Pizze')");

		// vini
		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Chianti Classico', 20.00, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pinot Grigio', 18.50, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Barolo', 30.00, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Prosecco', 15.00, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Chardonnay', 22.00, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Merlot', 18.00, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Sauvignon Blanc', 19.50, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Cabernet Sauvignon', 25.00, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Malbec', 21.50, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Riesling', 17.00, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Zinfandel', 23.00, 'Vini')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Rosé', 16.50, 'Vini')");

		// Birre
		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Birra Artigianale IPA', 6.00, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Lager Premium', 5.50, 'Birre')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Stout al Cioccolato', 7.00, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Weissbier', 6.50, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pilsner', 5.80, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Amber Ale', 6.20, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Saison', 7.50, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Porter', 8.00, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Witbier', 6.00, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Pale Ale', 6.50, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Golden Ale', 5.50, 'Birre')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Belgian Tripel', 9.00, 'Birre')");

		// Analcolici
		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Limonata Fresca', 4.00, 'Analcolici')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Aranciata Bio', 4.00, 'Analcolici')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Coca-Cola Zero', 3.50, 'Analcolici')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Acqua Minerale Naturale', 2.50, 'Analcolici')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Succhi di Frutta Misti', 5.00, 'Analcolici')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Tè Freddo al Limone', 4.50, 'Analcolici')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Frullato di Fragole', 5.50, 'Analcolici')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Espresso', 2.00, 'Analcolici')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Latte di Mandorla', 4.50, 'Analcolici')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Smoothie Verde Detox', 6.00, 'Analcolici')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Birra Analcolica', 3.80, 'Analcolici')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Ginger Beer', 4.20, 'Analcolici')");

		// Antipasti
		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Bruschette Miste', 8.00, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Carpaccio di Manzo', 9.50, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Insalata Caprese', 7.50, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Crostini con Crema di Formaggio', 6.50, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Polpette al Sugo', 8.50, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Frittura di Calamari', 10.00, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Tartare di Salmone', 11.00, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Melone e Prosciutto', 9.00, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Mozzarella in Carrozza', 7.50, 'Antipasti')");

		stmt.executeUpdate("INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Funghi Ripieni', 8.50, 'Antipasti')");

//		stmt.executeUpdate(
//				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Gamberi all' Aglio', 12.00, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO PIATTO (NOME, PREZZO, COMPONENTE) VALUES ('Vellutata di Zucca', 6.50, 'Antipasti')");

		stmt.executeUpdate(
				"INSERT INTO COPERTO (PREZZO) VALUES (2)");
		
	}

	private static void popolaComponente(Connection conn) throws Exception {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Primi', 1)");
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Secondi', 2)");
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Contorni', 3)");
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Pizze', 4)");
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Dolci', 5)");
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Vini', 6)");
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Birre', 7)");
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Analcolici', 8)");
		stmt.executeUpdate("INSERT INTO COMPONENTE (NOME, PRECEDENZA) VALUES ('Antipasti', 9)");
	}

	public static boolean existDB() {
		return new File(DB_REL_FILE).exists();
	}

}
