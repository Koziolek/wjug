package pl.koziolekweb.warsjava2011;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws Exception {
		Connection connection = createConnection();
		IDatabaseConnection dbUnitConnection = new DatabaseConnection(
				connection);
		IDataSet usersDataSet = new CsvURLDataSet(
				new URL(
						"file:///home/koziolek/workspace/warsjava2011/dbunit/src/main/resources/users.csv"));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, usersDataSet);
	}

	private static Connection createConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/koziolek", "koziolek",
				"koziolek");
		return connection;
	}
}
