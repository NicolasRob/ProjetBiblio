<div class="container Site-Content">

  <h1 class="mt-4 mb-3">Ajouter une Édition</h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="go?action=afficherAccueil">Accueil</a>
    </li>
    <li class="breadcrumb-item">
        <a href="go?action=afficherGestionCatalogue">Gestion du catalogue</a>
    </li>
    <li class="breadcrumb-item active">Ajout d'une édition</li>
  </ol>

    <div class="card mb-4">
      <div class="card-body">
        <div class="row">
          <div class="col-lg-12">
              <h2 class="card-title"></h2>
              <form class="form-horizontal" action="go">
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="titre">Titre:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="titre" name="titre" readonly value="Titre">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="auteur">Auteur:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="auteur" name="auteur" readonly value="Auteur">
                      </div>
                  </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="isbn">ISBN:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="isbn" name="isbn" >
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="editeur">Éditeur:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="editeur" name="editeur">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="description">Description</label>
                      <div class="col-sm-10">          
                          <textarea  rows="5" class="form-control" id="description" name="description"></textarea>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="pages">Nombre de pages:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="pages" name="pages">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="date">Date de publication:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="date" name="date">
                      </div>
                    </div>
                  <input type="hidden" name="action" value="ajouterEdition" />
                  <div class="form-group">        
                      <div class="col-sm-offset-2 col-sm-10">
                          <button type="submit" class="btn btn-default">Ajouter</button>
                      </div>
                  </div>
              </form>
          </div>
      </div>
    </div>
  </div>
</div>
