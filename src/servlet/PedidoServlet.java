package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClienteBD;
import model.PedidoBD;
import objetos.Cliente;
import objetos.Pedido;

/**
 * Servlet implementation class PedidoServlet
 */
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PedidoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean voltar = request.getParameter("voltar") != null;
		boolean enviar = request.getParameter("enviar") != null;
		boolean visualizar = request.getParameter("visualizar") != null;
		boolean sair = request.getParameter("sair") != null;

		if (voltar) {
			RequestDispatcher dis = request.getRequestDispatcher("paginas/menu.jsp");
			dis.forward(request, response);
		}

		if (enviar) {
			String codcliente = (String) request.getParameter("codcliente");

			Cliente cliente = new Cliente();

			cliente = ClienteBD.validaCliente(codcliente);

			if (cliente == null) {
				request.setAttribute("aviso", "Cliente não cadastrado!");
				RequestDispatcher dis = request.getRequestDispatcher("paginas/pedido.jsp");
				dis.forward(request, response);
			} else {
				String produto = (String) request.getParameter("produto");
				String preco = (String) request.getParameter("preco");

				// data e hora do sistema no momento do cadastro
				TimeZone timeZone = TimeZone.getTimeZone("GMT-3:00");
				TimeZone.setDefault(timeZone);
				Calendar calendar = GregorianCalendar.getInstance(timeZone);
				Date dataAtual = calendar.getTime();

				DateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String datahora = data.format(dataAtual);

				Pedido pedido = new Pedido();

				pedido.setProduto(produto);
				pedido.setPreco(preco);
				pedido.setData(datahora);
				pedido.setCodcliente(cliente.getCodcliente());
				pedido.setNome(cliente.getNome());
				pedido.setTelefone(cliente.getTelefone());
				pedido.setEndereco(cliente.getEndereco());
				pedido.setComplemento(cliente.getComplemento());
				pedido.setBairro(cliente.getBairro());
				pedido.setCep(cliente.getCep());

				if (PedidoBD.addPedido(pedido)) {
					request.setAttribute("aviso", "Pedido enviado com sucesso!");
					RequestDispatcher dis = request.getRequestDispatcher("paginas/pedido.jsp");
					dis.forward(request, response);
				} else {
					request.setAttribute("aviso", "Falhar ao enviar pedido!");
					RequestDispatcher dis = request.getRequestDispatcher("paginas/pedido.jsp");
					dis.forward(request, response);
				}
			}
		}

		if (visualizar) {
			// lista com os nomes para o cabeçalho
			String[] nomeCab = { "IDPedido", "Produto", "Preço (R$)", "Data pedido", "Código cliente", "Nome",
					"Telefone", "Endereco", "Complemento", "Bairro", "Cep" };

			// preenche a lista cabecalho para enviar para o arquivo jsp
			ArrayList<String> cabecalho = new ArrayList<String>();
			for (String nomes : nomeCab) {
				cabecalho.add(nomes);
			}

			HttpSession session = request.getSession();
			session.setAttribute("cabecalho", cabecalho);

			// lista receberá todos os pedidos do banco
			ArrayList<Pedido> pedidos = (ArrayList<Pedido>) PedidoBD.retornaPedidos();

			session.setAttribute("pedido", pedidos);
			RequestDispatcher dis = request.getRequestDispatcher("paginas/visualizapedidos.jsp");
			dis.forward(request, response);
		}

		if (sair) {
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}
	}

}
