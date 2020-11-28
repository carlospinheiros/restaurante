<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objetos.Pedido" %>
<!DOCTYPE html>
<% 
ArrayList<String> cabecalho = (ArrayList<String>) session.getAttribute("cabecalho");
if (cabecalho == null) {
    cabecalho = new ArrayList<String>();
    session.setAttribute("cabecalho", cabecalho);
}
ArrayList<Pedido> pedidos = (ArrayList) session.getAttribute("pedido");
if (pedidos == null) {
    pedidos = new ArrayList();
    session.setAttribute("pedidos", pedidos);
} else {
    session.removeAttribute("pedidos");
}
%>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Visualizar pedidos</title>
      <!-- Bootstrap Core CSS -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <!-- Custom CSS -->
      <link href="css/style.css" rel="stylesheet">
      <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700" rel="stylesheet">
   </head>
   <body>
      <div class="row">
         <div class="col-sm-12">
            <div class="fundo">
            </div>
            <img src="img/fundo.jpg">
         </div>
         <div class="col-sm-8 col-sm-offset-2">
            <div class="formulario" id="formPedido">
               <form id="visualizarpedido" action="PedidoServlet" method="post">
                  <fieldset>
                     <legend>Visualizar pedidos</legend>
                     <hr>
                     <div class="grupo btnsGroup">
                        <div class="form-group">
                           <input class="botao" type="submit" name="voltar" title="Voltar a opções" value="Voltar a opções" /> 
                        </div>
                        <div class="form-group">
                           <input class="botao" type="submit" name="visualizar" title="Visualizar pedidos" value="Visualizar pedidos" /> 
                        </div>
                        <div class="form-group">
                           <input class="botao" type="submit" name="sair" title="Sair do sistema" value="Sair" />   
                        </div>
                     </div>
                     <br />
                     <table class="table">
                     <tr>
                        <%for (String cab : cabecalho) {%>       
                           <th style="font-size: 14px"><%=cab%></th>
                        <% }%>
                        </tr>
                        <tr>
                        <%for(Pedido ped : pedidos) {%>
                        <tr>
                           <td style="font-size: 13px"><%=ped.getIdpedido()%></td>
                           <td style="font-size: 13px"><%=ped.getProduto()%> </td>
                           <td style="font-size: 13px"><%=ped.getPreco()%></td>
                           <td style="font-size: 13px"><%=ped.getData()%> </td>
                           <td style="font-size: 13px"><%=ped.getCodcliente()%> </td>
                           <td style="font-size: 13px"><%=ped.getNome()%> </td>
                           <td style="font-size: 13px"><%=ped.getTelefone()%> </td>
                           <td style="font-size: 13px"><%=ped.getEndereco()%>  </td>
                           <td style="font-size: 13px"><%=ped.getComplemento()%> </td>
                           <td style="font-size: 13px"><%=ped.getBairro()%> </td>
                           <td style="font-size: 13px"><%=ped.getCep()%> </td>
                        </tr>
                        <% }%>
                     </table>
                  </fieldset>
               </form>
            </div>
         </div>
      </div>
   </body>
</html>