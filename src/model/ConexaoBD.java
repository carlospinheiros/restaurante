package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {

	private Connection con;

	public void iniciaBd() {

		try {
			String database = "jdbc:mysql://localhost/restaurante?useTimezone=true&serverTimezone=UTC";
			String usuario = "root";
			String senha = "1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(database, usuario, senha);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fechaBd() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConexao() {
		return con;
	}

}
