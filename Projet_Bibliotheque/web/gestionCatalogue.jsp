<%-- 
    Document   : login
    Created on : 5-Oct-2017, 1:37:34 PM
    Author     : Vengor
--%>

<!-- Page Content -->
<div class="container Site-Content">

  <!-- Page Heading/Breadcrumbs -->
  <h1 class="mt-4 mb-3">Gestion du catalogue</h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="index.html">Accueil</a>
    </li>
    <li class="breadcrumb-item">
      Administration
    </li>
    <li class="breadcrumb-item active">Catalogue</li>
  </ol>

  <!-- Blog Post -->
    <div class="card mb-4">
      <div class="card-body">
        <div class="row">
          <div class="col-lg-12">
              <h2 class="card-title"></h2>
              <form class="form-horizontal" action="go">
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="recherche">Recherche:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="recherche" name="recherche">
                      </div>
                  </div>
                  <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">Crit&egrave;re:</label>
                        <div class="col-sm-10">          
                            <select class="form-control" id="critere" name="critere">
                                <option>Titre</option>
                                <option>Auteur</option>
                            </select>
                        </div>
                  </div>
                  <input type="hidden" name="action" value="rechercheCatalogue" />
                  <div class="form-group">        
                      <div class="col-sm-offset-2 col-sm-10">
                          <button type="submit" class="btn btn-default">Rechercher</button>
                      </div>
                  </div>
                    <div class="form-group">
                        <a class='col-sm-2' href="go?action=afficherAjoutOuvrage">Ajouter un nouvel ouvrage</a>
                    </div>
              </form>
          </div>
        </div>
        </div>
    </div>
        <div class="mb-4" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="card">
          <div class="card-header" role="tab" id="headingOne">
            <h5 class="mb-0">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Ouvrage - Auteur</a>
            </h5>
          </div>

          <div id="collapseOne" class="collapse" role="tabpanel" aria-labelledby="headingOne">
            <div class="card-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <td colspan="2"><a href="go?action=afficherAjoutEdition">Ajouter une édition</a></td>
                        <td colspan="2"><a href="2">Modifier l'ouvrage</a></td>
                        <td colspan="2"><a href="2">Supprimer l'ouvrage</a></td>
                    </tr>
                    <tr>
                        <td>ISBN</td>
                        <td>Editeur</td>
                        <td>Date de Publication</td>
                        <td>Emplacement</td>
                        <td><a href="">Modifier</a></td>
                        <td><a href="">Supprimer</a></td>
                    </tr>
                    <tr>
                        <td>ISBN</td>
                        <td>Editeur</td>
                        <td>Date de Publication</td>
                        <td>Emplacement</td>
                        <td><a href="">Modifier</a></td>
                        <td><a href="">Supprimer</a></td>
                </table>
            </div>
          </div>
            
            <div class="card-header" role="tab" id="headingTwo">
            <h5 class="mb-0">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseOne">Ouvrage - Auteur</a>
            </h5>
          </div>

          <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingOne">
            <div class="card-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <td>ISBN</td>
                        <td>Editeur</td>
                        <td>Date de Publication</td>
                    </tr>
                    <tr>
                        <td>ISBN</td>
                        <td>Editeur</td>
                        <td>Date de Publication</td>
                    </tr>
                </table>
            </div>
          </div>
        </div>
</div>
<!-- /.container -->

