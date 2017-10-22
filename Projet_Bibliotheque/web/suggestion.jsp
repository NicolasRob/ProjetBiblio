<div class="container Site-Content">

  <!-- Page Heading/Breadcrumbs -->
  <h1 class="mt-4 mb-3">Envoyer une suggestion</h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="go?action=afficherAccueil">Accueil</a>
    </li>
    <li class="breadcrumb-item active">Suggestion</li>
  </ol>

  <!-- Blog Post -->
    <div class="card mb-4">
      <div class="card-body">
        <div class="row">
          <div class="col-lg-12">
              <h2 class="card-title"></h2>
              <form class="form-horizontal" action="go">
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="titre">Titre:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="titre" name="titre" >
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="auteur">Auteur:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="auteur" name="auteur" >
                      </div>
                  </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="commentaires">Commentaires:</label>
                      <div class="col-sm-10">          
                          <textarea  rows="5" class="form-control" id="description" name="commentaires"></textarea>
                      </div>
                    </div>
                  <input type="hidden" name="action" value="envoyerSuggestion" />
                  <div class="form-group">        
                      <div class="col-sm-offset-2 col-sm-10">
                          <button type="submit" class="btn btn-default">Envoyer</button>
                      </div>
                  </div>
              </form>
          </div>
      </div>
    </div>
  </div>
</div>

