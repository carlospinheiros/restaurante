package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.FuncionarioBD;
import objetos.Funcionario;

/**
 * Servlet implementation class FuncionarioServlet
 */
public class FuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FuncionarioServlet() {
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
		boolean gerar = request.getParameter("gerar") != null;
		boolean sair = request.getParameter("sair") != null;

		if (voltar) {
			RequestDispatcher dis = request.getRequestDispatcher("paginas/menu.jsp");
			dis.forward(request, response);
		}

		if (enviar) {
			String codfuncionario = (String) request.getParameter("codfuncionario");
			String nome = (String) request.getParameter("nome");
			String telefone = (String) request.getParameter("telefone");
			String cargo = (String) request.getParameter("cargo");
			String login = (String) request.getParameter("login");
			String senha = (String) request.getParameter("senha");

			Funcionario func = new Funcionario();

			func.setCodfuncionario(codfuncionario);
			func.setNome(nome);
			func.setTelefone(telefone);
			func.setCargo(cargo);
			func.setLogin(login);
			func.setSenha(senha);

			if (FuncionarioBD.addFuncionario(func)) {
				request.setAttribute("aviso", "Funcionário cadastrado com sucesso!");
				RequestDispatcher dis = request.getRequestDispatcher("paginas/funcionario.jsp");
				dis.forward(request, response);
			} else {
				request.setAttribute("aviso", "Falhar ao cadastrar funcionário!");
				RequestDispatcher dis = request.getRequestDispatcher("paginas/cliente.jsp");
				dis.forward(request, response);
			}
		}

		if (gerar) {

			Document documentoPDF = new Document();

			try {
				// cria uma instancia do documento e da o nome do pdf
				String local1 = System.getProperty("user.home");
				PdfWriter.getInstance(documentoPDF,
						new FileOutputStream(local1 + "\\Documents\\ListaFuncionarios.pdf"));

				// realiza a abertura do arquivo para escrita
				documentoPDF.open();

				// setar o tamanho da página
				documentoPDF.setPageSize(PageSize.A4);

				// adiciona título
				Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLUE);
				Paragraph titulo = new Paragraph("LISTA DE FUNCIONÁRIOS", fontTitulo);
				titulo.setAlignment(Element.ALIGN_CENTER);
				documentoPDF.add(titulo);

				// pula linha
				documentoPDF.add(new Paragraph(" "));

				// quantidade de colunas
				PdfPTable table = new PdfPTable(6);

				// configura a fonte e alinhamento das palavras do cabeçalho
				Font fontCabecalho = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);

				PdfPCell cabCodfuncionario = new PdfPCell(new Paragraph("Codfuncionario", fontCabecalho));
				cabCodfuncionario.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabCodfuncionario.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabNome = new PdfPCell(new Paragraph("Nome", fontCabecalho));
				cabNome.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabNome.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabTelefone = new PdfPCell(new Paragraph("Telefone", fontCabecalho));
				cabTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabTelefone.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabFuncao = new PdfPCell(new Paragraph("Cargo", fontCabecalho));
				cabFuncao.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabFuncao.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabLogin = new PdfPCell(new Paragraph("Login", fontCabecalho));
				cabLogin.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabLogin.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabSenha = new PdfPCell(new Paragraph("Senha", fontCabecalho));
				cabSenha.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabSenha.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				// adiciona o cabeçalho da tabela
				table.addCell(cabCodfuncionario);
				table.addCell(cabNome);
				table.addCell(cabTelefone);
				table.addCell(cabFuncao);
				table.addCell(cabLogin);
				table.addCell(cabSenha);

				// lista receberá todos os funcionários do banco
				ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>) FuncionarioBD.retornaFuncionarios();

				// verifica se o documento está aberto
				if (documentoPDF.isOpen()) {
					for (Funcionario funcionario : funcionarios) {
						table.addCell(new Phrase(funcionario.getCodfuncionario(),
								FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(new Phrase(funcionario.getNome(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(
								new Phrase(funcionario.getTelefone(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(
								new Phrase(funcionario.getCargo(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(
								new Phrase(funcionario.getLogin(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(
								new Phrase(funcionario.getSenha(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
					}
					documentoPDF.add(table);
				}
			} catch (DocumentException de) {
				de.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				// Fecha o arquivo após a escrita da mensagem
				documentoPDF.close();
			}
			// abrir o arquivo no navegador
			String local = System.getProperty("user.home");
			File file = new File(local + "\\Documents\\ListaFuncionarios.pdf");
			response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"ListaFuncionarios.pdf\"");
			Files.copy(file.toPath(), response.getOutputStream());
		}

		if (sair) {
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}
	}

}
