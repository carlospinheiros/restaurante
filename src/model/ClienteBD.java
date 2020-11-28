package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objetos.Cliente;

public class ClienteBD {

	static public synchronized Cliente validaCliente(String codcliente) {

		Cliente cliente = new Cliente();

		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * from cliente where codcliente=?");
			ps.setString(1, codcliente);
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();
			String codclienteBd;
			codclienteBd = res.getString("codcliente");

			if (codclienteBd.contentEquals(codcliente)) {
				String nome;
				String telefone;
				String endereco;
				String complemento;
				String bairro;
				String cep;

				codcliente = res.getString("codcliente");
				nome = res.getString("nome");
				telefone = res.getString("telefone");
				endereco = res.getString("endereco");
				complemento = res.getString("complemento");
				bairro = res.getString("bairro");
				cep = res.getString("cep");

				cliente.setCodcliente(codcliente);
				cliente.setNome(nome);
				cliente.setTelefone(telefone);
				cliente.setEndereco(endereco);
				cliente.setComplemento(complemento);
				cliente.setBairro(bairro);
				cliente.setCep(cep);
			} else {
				cliente = null;
			}
			ps.close();
			c.close();
			a.fechaBd();
		} catch (Exception e) {
			cliente = null;
		}
		return cliente;
	}

	static public synchronized boolean addCliente(Cliente cli) {

		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"INSERT INTO cliente (codcliente, nome, telefone, endereco, complemento, bairro, cep) VALUES "
							+ "(?,?,?,?,?,?,?)");

			ps.setString(1, cli.getCodcliente());
			ps.setString(2, cli.getNome());
			ps.setString(3, cli.getTelefone());
			ps.setString(4, cli.getEndereco());
			ps.setString(5, cli.getComplemento());
			ps.setString(6, cli.getBairro());
			ps.setString(7, cli.getCep());
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

	static public synchronized ArrayList<Cliente> retornaClientes() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM cliente");
			ResultSet res = (ResultSet) ps.executeQuery();

			while (res.next()) {
				Cliente cli = new Cliente();
				cli.setCodcliente(res.getString("codcliente"));
				cli.setNome(res.getString("nome"));
				cli.setTelefone(res.getString("telefone"));
				cli.setEndereco(res.getString("endereco"));
				cli.setComplemento(res.getString("complemento"));
				cli.setBairro(res.getString("bairro"));
				cli.setCep(res.getString("cep"));
				clientes.add(cli);
			}
			ps.close();
			c.close();
			a.fechaBd();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

}
