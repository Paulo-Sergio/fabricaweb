package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {
		String dataBase = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String userPassword = "123456";
		try {
			return DriverManager.getConnection(dataBase, userName, userPassword);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
