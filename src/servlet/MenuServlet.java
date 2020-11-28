package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuServlet() {
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

		boolean entrar = request.getParameter("entrar") != null;
		boolean sair = request.getParameter("sair") != null;

		if (entrar) {
			Integer opcoes = Integer.parseInt(request.getParameter("opcoes"));

			switch (opcoes) {

			case 1:
				RequestDispatcher func = request.getRequestDispatcher("paginas/funcionario.jsp");
				func.forward(request, response);
				break;

			case 2:
				RequestDispatcher usu = request.getRequestDispatcher("paginas/cliente.jsp");
				usu.forward(request, response);
				break;

			case 3:
				RequestDispatcher ped = request.getRequestDispatcher("paginas/pedido.jsp");
				ped.forward(request, response);
				break;

			case 4:
				RequestDispatcher vis_ped = request.getRequestDispatcher("paginas/visualizapedidos.jsp");
				vis_ped.forward(request, response);
				break;
			}
		}

		if (sair) {
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}
	}

}
