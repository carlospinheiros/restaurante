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

import model.ClienteBD;
import objetos.Cliente;

/**
 * Servlet implementation class ClienteServlet
 */
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteServlet() {
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
			String codcliente = (String) request.getParameter("codcliente");
			String nome = (String) request.getParameter("nome");
			String telefone = (String) request.getParameter("telefone");
			String endereco = (String) request.getParameter("endereco");
			String complemento = (String) request.getParameter("complemento");
			String bairro = (String) request.getParameter("bairro");
			String cep = (String) request.getParameter("cep");

			Cliente cli = new Cliente();

			cli.setCodcliente(codcliente);
			cli.setNome(nome);
			cli.setTelefone(telefone);
			cli.setEndereco(endereco);
			cli.setComplemento(complemento);
			cli.setBairro(bairro);
			cli.setCep(cep);

			if (ClienteBD.addCliente(cli)) {
				request.setAttribute("aviso", "Cliente cadastrado com sucesso!");
				RequestDispatcher dis = request.getRequestDispatcher("paginas/cliente.jsp");
				dis.forward(request, response);
			} else {
				request.setAttribute("aviso", "Falhar ao cadastrar cliente!");
				RequestDispatcher dis = request.getRequestDispatcher("paginas/cliente.jsp");
				dis.forward(request, response);
			}
		}

		if (gerar) {

			Document documentoPDF = new Document();

			try {
				// cria uma instancia do documento e da o nome do pdf
				String local1 = System.getProperty("user.home");
				PdfWriter.getInstance(documentoPDF, new FileOutputStream(local1 + "\\Documents\\ListaClientes.pdf"));

				// realiza a abertura do arquivo para escrita
				documentoPDF.open();

				// setar o tamanho da página
				documentoPDF.setPageSize(PageSize.A4);

				// adiciona título
				Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLUE);
				Paragraph titulo = new Paragraph("LISTA DE CLIENTES", fontTitulo);
				titulo.setAlignment(Element.ALIGN_CENTER);
				documentoPDF.add(titulo);

				// pula linha
				documentoPDF.add(new Paragraph(" "));

				// quantidade de colunas
				PdfPTable table = new PdfPTable(7);

				// configura a fonte e alinhamento das palavras do cabeçalho
				Font fontCabecalho = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);

				PdfPCell cabCodcliente = new PdfPCell(new Paragraph("Codcliente", fontCabecalho));
				cabCodcliente.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabCodcliente.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabNome = new PdfPCell(new Paragraph("Nome", fontCabecalho));
				cabNome.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabNome.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabTelefone = new PdfPCell(new Paragraph("Telefone", fontCabecalho));
				cabTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabTelefone.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabEndereco = new PdfPCell(new Paragraph("Endereço", fontCabecalho));
				cabEndereco.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabEndereco.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabComplemento = new PdfPCell(new Paragraph("Complemento", fontCabecalho));
				cabComplemento.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabComplemento.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabBairro = new PdfPCell(new Paragraph("Bairro", fontCabecalho));
				cabBairro.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabBairro.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				PdfPCell cabCep = new PdfPCell(new Paragraph("Cep", fontCabecalho));
				cabCep.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabCep.setBackgroundColor(new BaseColor(0xc0, 0xc0, 0xc0));

				// adiciona o cabeçalho da tabela
				table.addCell(cabCodcliente);
				table.addCell(cabNome);
				table.addCell(cabTelefone);
				table.addCell(cabEndereco);
				table.addCell(cabComplemento);
				table.addCell(cabBairro);
				table.addCell(cabCep);

				// lista receberá todos os clientes do banco
				ArrayList<Cliente> clientes = (ArrayList<Cliente>) ClienteBD.retornaClientes();

				// verifica se o documento está aberto
				if (documentoPDF.isOpen()) {
					for (Cliente cliente : clientes) {
						table.addCell(
								new Phrase(cliente.getCodcliente(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(new Phrase(cliente.getNome(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(new Phrase(cliente.getTelefone(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(new Phrase(cliente.getEndereco(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(
								new Phrase(cliente.getComplemento(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(new Phrase(cliente.getBairro(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
						table.addCell(new Phrase(cliente.getCep(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
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
			File file = new File(local + "\\Documents\\ListaClientes.pdf");
			response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"ListaClientes.pdf\"");
			Files.copy(file.toPath(), response.getOutputStream());
		}

		if (sair) {
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}
	}

}
