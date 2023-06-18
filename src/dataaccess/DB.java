package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {

	// Singleton Design Pattern
	private static DB db = new DB();
	private Connection connection;

	// Connecting to the MS SQL driver
	public DB() {

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				createConnection();
			} catch (Exception e) {
				System.err.println("Something went wrong! Please try again...");
			}

		}

	// returns a single object
	public static DB getDB() {
		return db;
	}

	// connecting to databse
	private void createConnection() {
		try {

			String url = "jdbc:sqlserver://localhost:1433;databaseName=dmat;user=sa;password=root;trustServerCertificate=true";

			connection = DriverManager.getConnection(url);

		} catch (Exception e) {
			System.err.println("Something went wrong! Please try again...");
		}
	}

	// WRITE OPERATION (Insert/Update/Delete)
	public int executeSQL(String sql) {

		int result = 0;

		try {
			Statement stmt = connection.createStatement();
			result = stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Something went wrong! Please try again...");
		}

		return result;
	}

	// READ OPERATION (SELECT)
	public ResultSet executeRetrieveQuery(String sql) {

		// ResultSet holds Table Data
		ResultSet set = null;

		try {

			Statement stmt = connection.createStatement();

			set = stmt.executeQuery(sql);

		} catch (Exception e) {
			System.err.println("Something went wrong! Please try again...");
		}

		return set;

	}

	public void closeConnection() {
		try {
			connection.close();
			System.out.println("[DB] Connection Closed..");
		} catch (Exception e) {
			System.err.println("[DB] [Connection] Something Went Wrong: " + e);
		}
	}

}
