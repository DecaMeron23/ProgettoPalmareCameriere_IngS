package main_package;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

	public static String DB_REL_FILE = "./DataBase/indirizzi.db3";
	public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

	public static void main(String[] args) {
		try {			
		 Connection conn = DriverManager.getConnection(DB_URL);
		 if (conn != null) {
		   DatabaseMetaData meta = conn.getMetaData();
		   System.out.println("The driver name is " + meta.getDriverName());
		   System.out.println("A new database has been created.");
		   
		   Statement stm = conn.createStatement();
		   String str = "CREATE TABLE TAVOLI (" + 
				   								"NOME			INT PRIMARY KEY," + 
				   								"POSTI_MASSIMI	INT )";
		   
		   stm.executeUpdate(str);
		   stm.close();
		   conn.close();
		   
		 }
		 // controllo che il file esista a questo punto
		 System.out.println("il file esiste? " + new File(DB_REL_FILE).exists());
		} catch (SQLException e) {
		  System.out.println(e.getMessage());
		}
	}
	
}
