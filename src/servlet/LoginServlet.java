package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FuncionarioBD;
import objetos.Funcionario;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		if (entrar) {
			String login = (String) request.getParameter("login");
			String senha = (String) request.getParameter("senha");

			Funcionario funcionario = new Funcionario();

			funcionario = FuncionarioBD.validaLogin(login, senha);

			if (funcionario == null) {
				request.setAttribute("aviso", "Erro na senha e/ou login!");
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);

			} else {
				request.setAttribute("funcionario", funcionario);
				RequestDispatcher dis = request.getRequestDispatcher("paginas/menu.jsp");
				dis.forward(request, response);
			}
		}
	}

}