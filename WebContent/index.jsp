<!DOCTYPE html>
<% String aviso = (String) request.getAttribute("aviso"); %>
<html>
   <head>
      <meta charset="ISO-8859-1" />
      <title>Acessar o sistema</title>
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
               <form id="loginsenha" action="LoginServlet" method="post">
                  <fieldset>
                     <legend>Bem-vindo!</legend>
                     <hr>
                     <div class="grupo">
                        <div class="form-group">
                           <label for="login">Login:</label>
                           <input type="text" name="login" id="login" class="form-control" />
                        </div>
                        <div class="form-group">
                           <label for="senha">Senha:</label>
                           <input type="password" name="senha" id="senha" class="form-control" />
                        </div>
                     </div>
                     <div class="grupo btnsGroup">
                        <input type="submit" name="entrar" class="botao" title="Entrar no sistema" value="Entrar" />
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