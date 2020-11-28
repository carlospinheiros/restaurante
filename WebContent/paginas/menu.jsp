<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Opções do sistema</title>
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
               <form id="opcoessistema" action="MenuServlet" method="post">
                  <fieldset>
                     <legend>Opções</legend>
                     <hr>
                     <div class="grupo">
                        <div class="form-group">
                           <label>Escolha uma opção:</label>
                           <select name="opcoes" class="form-control form-control-lg">
                              <option value=1 > 1 - Funcionário </option>
							  <option value=2 > 2 - Cliente </option>
							  <option value=3 > 3 - Pedido </option>
					          <option value=4 > 4 - Visualizar pedidos </option>
                           </select>
                        </div>
                     </div>
                     <div class="grupo btnsGroup">
                        <input type="submit" name="entrar" class="botao" id="entrar" title="Entrar no sistema" value="Entrar" />
                		<input type="submit" name="sair" class="botao" id="sair" title="Sair do sistema" value="Sair" />
                     </div>
                  </fieldset>
               </form>
            </div>
         </div>
      </div>
   </body>
</html>