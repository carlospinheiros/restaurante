<!DOCTYPE html>
<% String aviso = (String) request.getAttribute("aviso"); %>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Cadastro de clientes</title>
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
         <div class="col-sm-6 col-sm-offset-3">
            <div class="formulario">
               <form id="cadastrarcliente" action="ClienteServlet" method="post">
                  <fieldset>
                     <legend>Cadastrar cliente</legend>
                     <hr>
                     <div class="grupo">
                        <div class="form-group">
                           <label for="codcliente">CPF:</label>
                           <input class="form-control" type="text" name="codcliente" id="codcliente" /> 
                        </div>
                        <div class="form-group">
                           <label for="nome">Nome:</label>
                           <input type="text" class="form-control" name="nome" id="nome" />                                     
                        </div>
                        <div class="form-group">
                           <label for="telefone">Telefone:</label>
                           <input type="text" class="form-control" name="telefone" id="telefone" />                                
                        </div>
                     </div>
                     <div class="grupo">
                        <div class="form-group">
                           <label for="endereco">Endereço:</label>
                           <input type="text" class="form-control" name="endereco" id="endereco" />
                        </div>
                        <div class="form-group">
                           <label for="complemento">Complemento:</label>
                           <input type="text" class="form-control" name="complemento" id="complemento" />                                 
                        </div>
                        <div class="form-group">
                           <label for="bairro">Bairro:</label>
                           <input type="text" class="form-control" name="bairro" id="bairro" />
                        </div>
                        <div class="form-group">
                           <label for="cep">CEP:</label>
                           <input type="text" class="form-control" name="cep" id="cep" />
                        </div>
                     </div>
                     <div class="grupo btnsGroup">
                        <div class="form-group">
                           <input class="botao" type="submit" name="voltar" title="Voltar a opções" value="Voltar a opções" /> 
                        </div>
                        <div class="form-group">
                           <input class="botao" type="submit" name="enviar" title="Enviar cadastro" value="Enviar cadastro" />
                        </div>
                        <div class="form-group">
                           <input class="botao"  type="submit" name="gerar" title="Gerar lista PDF" value="Gerar lista PDF" />
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