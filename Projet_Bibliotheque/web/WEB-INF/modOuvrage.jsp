<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container Site-Content">

  <h1 class="mt-4 mb-3">Modifier un ouvrage</h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="go?action=afficherAccueil">Accueil</a>
    </li>
    <li class="breadcrumb-item">
        <a href="go?action=afficherGestionCatalogue">Gestion du catalogue</a>
    </li>
    <li class="breadcrumb-item">Modification d'un ouvrage</li>
    <li class="breadcrumb-item active">${requestScope.ouvrage.getTitre()}</li>
  </ol>

    <div class="card mb-4">
      <div class="card-body">
        <div class="row">
          <div class="col-lg-12">
              <h2 class="card-title"></h2>
              <p class="text-danger">${requestScope.erreurMod}</p>
              <p class="text-danger">${requestScope.erreurAuteur}</p>
              <p class="text-danger">${requestScope.erreurException}</p>
              <p class="text-success">${requestScope.message}</p>
              <form class="form-horizontal" action="go">
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="titre">Titre:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="titre" name="titre" value="${ouvrage.getTitre()}">
                      </div>
                  </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="type">Cat&eacute;gorie:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="type" name="type" value="${ouvrage.getType()}">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-sm-2" for="prenom">Pr&eacute;nom Auteur:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="prenom" name="prenom" value="${ouvrage.getAuteur().getPrenom()}">
                      </div>
                  </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="nom">Nom Auteur:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="nom" name="nom" value="${ouvrage.getAuteur().getNom()}">
                      </div>
                  </div>
                    <div class="form-group">
                      <label class="control-label col-sm-2" for="idAuteur">ID Auteur:</label>
                      <div class="col-sm-10">          
                          <input type="text" class="form-control" id="idAuteur" name="idAuteur" value="${ouvrage.getAuteur().getId()}">
                      </div>
                  </div>
                  <input type="hidden" name="id" value="${ouvrage.getId()}" />
                  <input type="hidden" name="action" value="modifierOuvrage" />
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
