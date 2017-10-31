<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container Site-Content">

  <h1 class="mt-4 mb-3">Modifier un auteur</h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="go?action=afficherAccueil">Accueil</a>
    </li>
    <li class="breadcrumb-item">
        <a href="go?action=afficherGestionCatalogue">Gestion du catalogue</a>
    </li>
    <li class="breadcrumb-item">Modification d'un auteur</li>
    <li class="breadcrumb-item active">${requestScope.auteur.getPrenom()} ${requestScope.auteur.getNom()}</li>
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
                      <label class="control-label col-sm-2" for="prenom">Prenom:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="prenom" name="prenom" value="${auteur.getPrenom()}">
                      </div>
                  </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="nom">Nom:</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="nom" name="nom" value="${auteur.getNom()}">
                      </div>
                  </div>
                  <input type="hidden" name="id" value="${auteur.getId()}" />
                  <input type="hidden" name="action" value="modifierAuteur" />
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
