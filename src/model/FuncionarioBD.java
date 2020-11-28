package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objetos.Funcionario;

public class FuncionarioBD {

	static public synchronized Funcionario validaLogin(String login, String senha) {

		Funcionario func = new Funcionario();

		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c
					.prepareStatement("SELECT * from funcionario where login=? and senha=?");
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();
			String loginBd;
			String senhaBd;
			loginBd = res.getString("login");
			senhaBd = res.getString("senha");

			if ((loginBd.contentEquals(login)) && (senhaBd.contentEquals(senha))) {
				String codfuncionario;
				String nome;
				String telefone;
				String cargo;
				codfuncionario = res.getString("codfuncionario");
				nome = res.getString("nome");
				telefone = res.getString("telefone");
				cargo = res.getString("cargo");
				func.setCodfuncionario(codfuncionario);
				func.setNome(nome);
				func.setTelefone(telefone);
				func.setCargo(cargo);
				func.setLogin(loginBd);
				func.setSenha(senhaBd);
			} else {
				func = null;
			}
			ps.close();
			c.close();
			a.fechaBd();
		} catch (Exception e) {
			func = null;
		}
		return func;
	}

	static public synchronized boolean addFuncionario(Funcionario f) {

		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"INSERT INTO funcionario (codfuncionario, nome, telefone, cargo, login, senha) VALUES "
							+ "(?,?,?,?,?,?)");

			ps.setString(1, f.getCodfuncionario());
			ps.setString(2, f.getNome());
			ps.setString(3, f.getTelefone());
			ps.setString(4, f.getCargo());
			ps.setString(5, f.getLogin());
			ps.setString(6, f.getSenha());
			ps.executeUpdate();
			ps.close();
			c.close();
			a.fechaBd();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	static public synchronized ArrayList<Funcionario> retornaFuncionarios() {

		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM funcionario");
			ResultSet res = (ResultSet) ps.executeQuery();

			while (res.next()) {
				Funcionario func = new Funcionario();
				func.setCodfuncionario(res.getString("codfuncionario"));
				func.setNome(res.getString("nome"));
				func.setTelefone(res.getString("telefone"));
				func.setCargo(res.getString("cargo"));
				func.setLogin(res.getString("login"));
				func.setSenha(res.getString("senha"));
				funcionarios.add(func);
			}
			ps.close();
			c.close();
			a.fechaBd();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

}
