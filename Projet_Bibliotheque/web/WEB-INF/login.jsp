<div class="container Site-Content">

  <h1 class="mt-4 mb-3">Connection</h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="go?action=afficherAccueil">Accueil</a>
    </li>
    <li class="breadcrumb-item active">Connection</li>
  </ol>

    <div class="card mb-4">
      <div class="card-body">
        <div class="row">
          <div class="col-lg-12">
              <h2 class="card-title"></h2>
              <form class="form-horizontal" action="go">
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="numeroMembre">Num&eacute;ro de membre:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="numeroMembre" placeholder="Entrez votre num&eacute;ro de membre" name="numeroMembre">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="pwd">Mot de passe:</label>
                      <div class="col-sm-10">          
                          <input type="password" class="form-control" id="pwd" placeholder="Entrez votre mot de passe" name="pwd">
                      </div>
                  </div>
                  <input type="hidden" name="action" value="login" />
                  <div class="form-group">        
                      <div class="col-sm-offset-2 col-sm-10">
                            <p class="text-danger"><%= (request.getAttribute("erreurLogin") != null) 
                                    ? request.getAttribute("erreurLogin") : "" %></p>
                            <button type="submit" class="btn btn-default">Connection</button>
                      </div>
                  </div>
              </form>
          </div>
      </div>
    </div>
  </div>
</div>

