<!DOCTYPE html>
<% String aviso = (String) request.getAttribute("aviso"); %>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Cadastro de pedidos</title>
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
         <div class="col-sm-4 col-sm-offset-4">
            <div class="formulario">
               <form id="cadastrarpedido" action="PedidoServlet" method="post">
                  <fieldset>
                     <legend>Pedido</legend>
                     <hr>
                     <div class="grupo">
                        <div class="form-group">
                           <label for="codcliente">Código do cliente (CPF):</label>
                           <input type="text" class="form-control" name="codcliente" id="codcliente" />
                        </div>
                     </div>
                     <div class="grupo">
                        <div class="form-group">
                           <label>Produto:</label>
                           <select name="produto">
                              <option value="Café da manhã " > Café da manhã </option>
                              <option value="Lanche" > Lanche </option>
                              <option value="Almoço" > Almoço </option>
                              <option value="Sobremesa" > Sobremesa </option>
                              <option value="Promoção" > Promoção </option>
                              <option value="Oferta especial" > Oferta especial </option>
                           </select>
                        </div>
                        </div>
                        <div class="grupo">
                        <div class="form-group">
                           <label>Preço (R$):</label>
                           <select name="preco">
                              <option value="5,00" > 5,00 </option>
                              <option value="10,00" > 10,00 </option>
                              <option value="15,00" > 15,00 </option>
                              <option value="20,00" > 20,00 </option>
                           </select>
                        </div>
                     </div>
                     <div class="grupo btnsGroup">
                        <div class="form-group" style="margin-left:-15px">
                           <input class="botao" type="submit" name="voltar" title="Voltar a opções" value="Voltar a opções" /> 
                        </div>
                        <div class="form-group">
                           <input class="botao" type="submit" name="enviar" title="Enviar pedido" value="Enviar pedido" />
                        </div>
                        <div class="form-group">
                           <input class="botao" type="submit" name="sair" title="Sair do sistema" value="Sair" />  
                        </div>
                     </div>
                  </fieldset>
               </form>
               <br />
               <% if (aviso != null) { %>
               <p style="text-align:center"><%= aviso %></p>
               <% } %>
            </div>
         </div>
      </div>
   </body>
</html>