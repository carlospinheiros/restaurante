<!DOCTYPE html>
<% String aviso = (String) request.getAttribute("aviso"); %>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Cadastro de funcionários</title>
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
               <form id="cadastrarfuncionario" action="FuncionarioServlet" method="post">
                  <fieldset>
                     <legend>Cadastrar funcionário</legend>
                     <hr>
                     <div class="grupo">
                        <div class="form-group">
                           <label for="codfuncionario">CPF:</label>
                           <input type="text" class="form-control" name="codfuncionario" id="codfuncionario" />
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
                           <label>Cargo:</label>
                           <select name="cargo" class="form-control form-control-lg">
                              <option value="Gerente" > Gerente </option>
                              <option value="Cozinheiro" > Cozinheiro </option>
                              <option value="Atendente" > Atendente </option>
                              <option value="Entregador" > Entregador </option>
                           </select>
                        </div>
                     </div>
                     <div class="grupo">
                        <div class="form-group">
                           <label for="login">Informe um login:</label>
                           <input type="text" class="form-control" name="login" id="login" />
                        </div>
                        <div class="form-group">
                           <label for="senha">Informe uma senha:</label>
                           <input type="password" class="form-control" name="senha" id="senha" />                                   
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
                           <input class="botao" type="submit" name="sair" id="sair" title="Sair do sistema" value="Sair" />  
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