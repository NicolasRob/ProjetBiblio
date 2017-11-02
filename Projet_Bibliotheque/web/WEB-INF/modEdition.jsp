<div class="container Site-Content">

  <h1 class="mt-4 mb-3">Modifier une Édition</h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="go?action=afficherAccueil">Accueil</a>
    </li>
    <li class="breadcrumb-item">
        <a href="go?action=afficherGestionCatalogue">Gestion du catalogue</a>
    </li>
    <li class="breadcrumb-item active">Modification d'une édition</li>
  </ol>
    <div class="card mb-4">
      <div class="card-body">
        <div class="row">
          <div class="col-lg-12">
              <h2 class="card-title"></h2>
              <form class="form-horizontal" action="go">
                  <p class="text-danger" >${requestScope.erreurException}</p>
                  <p class="text-success" >${param.message}</p>
                  <p class="text-danger" >${param.messageErreur}</p>
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="titre">Titre:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="titre" name="titre" 
                                 readonly value="${requestScope.edition.getOuvrage().getTitre()}">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="auteur">Auteur:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="auteur" name="auteur" 
                                 readonly value="${requestScope.edition.getOuvrage().getAuteur().getPrenom()} ${requestScope.edition.getOuvrage().getAuteur().getNom()}">
                      </div>
                  </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="isbn">ISBN:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="isbn" name="isbn" 
                                 value="${requestScope.edition.getIsbn()}">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="editeur">Éditeur:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="editeur" name="editeur"
                                 value="${requestScope.edition.getEditeur()}">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="pages">Nombre de pages:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="pages" name="pages"
                                 value="${requestScope.edition.getNombrePage()}">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-sm-4" for="date">Date de publication (YYYY-MM-DD):</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="date" name="date"
                                 value="${requestScope.edition.getDatePublication()}">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="image">Image:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="image" name="image"
                                 value="${requestScope.edition.getImage()}">
                      </div>
                    </div>
                  <input type="hidden" name="id" value="${requestScope.edition.getId()}" />
                  <input type="hidden" name="action" value="modifierEdition" />
                  <div class="form-group">        
                      <div class="col-sm-offset-2 col-sm-10">
                          <button type="submit" class="btn btn-default">Enregistrer</button>
                      </div>
                  </div>
              </form>
          </div>
      </div>
    </div>
  </div>
</div>
