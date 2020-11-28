package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objetos.Pedido;

public class PedidoBD {

	static public synchronized boolean addPedido(Pedido p) {

		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"INSERT INTO pedido (produto, preco, datahora, codcliente, nome, telefone, endereco, complemento, bairro, cep) VALUES "
							+ "(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, p.getProduto());
			ps.setString(2, p.getPreco());
			ps.setString(3, p.getData());
			ps.setString(4, p.getCodcliente());
			ps.setString(5, p.getNome());
			ps.setString(6, p.getTelefone());
			ps.setString(7, p.getEndereco());
			ps.setString(8, p.getComplemento());
			ps.setString(9, p.getBairro());
			ps.setString(10, p.getCep());
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

	static public synchronized ArrayList<Pedido> retornaPedidos() {

		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM pedido");
			ResultSet res = (ResultSet) ps.executeQuery();

			while (res.next()) {
				Pedido pedido = new Pedido();
				pedido.setIdpedido(res.getInt("idpedido"));
				pedido.setProduto(res.getString("produto"));
				pedido.setPreco(res.getString("preco"));
				pedido.setData(res.getString("datahora"));
				pedido.setCodcliente(res.getString("codcliente"));
				pedido.setNome(res.getString("nome"));
				pedido.setTelefone(res.getString("telefone"));
				pedido.setEndereco(res.getString("endereco"));
				pedido.setComplemento(res.getString("complemento"));
				pedido.setBairro(res.getString("bairro"));
				pedido.setCep(res.getString("cep"));
				pedidos.add(pedido);
			}
			ps.close();
			c.close();
			a.fechaBd();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}

}
