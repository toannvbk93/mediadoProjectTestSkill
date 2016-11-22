package projectTest.example.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB{
	public Connection connection = null;
	public ResultSet rs = null;
	public Statement stat = null;

	public ConnectDB(){}
	public void ConnectDB(Connection connection, ResultSet rs, Statement stat) {
		this.connection = connection;
		this.rs = rs;
		this.stat = stat;
	}

	public Connection ConnectDB() {
		System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return null;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/project_post", "postgres", "zxcvbn");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
		return connection;
	}

	public void closeDB() throws SQLException {
		if (stat != null) {
			stat.close();
			connection.close();
		}
	}

	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
		}
	}
}
