<%-- Affichage d'un formulaire de recherche des ouvrages et des résultats appropriés--%>
<%-- Utilisés lors de la gestion du catalogue --%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container Site-Content">

    <h1 class="mt-4 mb-3">Gestion du catalogue</h1>

    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="go?action=afficherAccueil">Accueil</a>
        </li>
        <li class="breadcrumb-item">
            Administration
        </li>
        <li class="breadcrumb-item active">Catalogue</li>
    </ol>

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
                            <label class="control-label col-sm-2" for="critere">Crit&egrave;re:</label>
                            <div class="col-sm-10">          
                                <select class="form-control" id="critere" name="critere">
                                    <option value="titre">Titre</option>
                                    <option value="auteur">Auteur</option>
                                    <option value="categorie">Catégorie</option>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="action" value="afficherGestionCatalogue" />
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
    <c:if test="${param.recherche != null}">
        <p class="text-danger">${requestScope.erreurInput}</p>
        <p class="text-danger">${requestScope.erreurException}</p>
        <p class="text-success">${param.message}</p>
        <div class="mb-4" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="card">
                <c:forEach var="ouvrage" items="${requestScope.ouvragesEditions}" varStatus="loop">
                    <div class="card-header" role="tab" id="heading${loop.index}">
                        <h5 class="mb-0">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse${loop.index}" 
                               aria-expanded="true" aria-controls="collapse${loop.index}">
                                ${ouvrage.getKey().getTitre()} - ${ouvrage.getKey().getAuteur().getPrenom()} ${ouvrage.getKey().getAuteur().getNom()}
                            </a>
                        </h5>
                    </div>

                    <div id="collapse${loop.index}" class="collapse" role="tabpanel" aria-labelledby="heading${loop.index}">
                        <div class="card-body">
                            <table class="table table-striped table-bordered">
                                <tr>
                                    <td colspan="2"><a href="go?action=afficherAjoutEdition&id=${ouvrage.getKey().getId()}&recherche=${param.recherche}&critere=${param.critere}">
                                            Ajouter une Édition</a></td>
                                    <td colspan="2"><a href="go?action=supprimerOuvrage&id=${ouvrage.getKey().getId()}&recherche=${param.recherche}&critere=${param.critere}">
                                            Supprimer l'ouvrage</a></td>
                                    <td><a href="go?action=afficherModificationOuvrage&id=${ouvrage.getKey().getId()}&recherche=${param.recherche}&critere=${param.critere}">
                                            Modifier l'ouvrage</a></td>
                                    <td><a href="go?action=afficherModificationAuteur&id=${ouvrage.getKey().getId()}&recherche=${param.recherche}&critere=${param.critere}">
                                            Modifier l'auteur</a></td>
                                </tr>
                                <c:forEach var="edition" items="${ouvrage.getValue()}">
                                    <tr>
                                        <td>${edition.getIsbn()}</td>
                                        <td colspan="2">${edition.getEditeur()}</td>
                                        <td>${edition.getDatePublication()}</td>
                                        <td><a href="go?action=afficherModificationEdition&id=${edition.getId()}&recherche=${param.recherche}&critere=${param.critere}">
                                                Modifier</a></td>
                                        <td><a href="go?action=supprimerEdition&id=${edition.getId()}&recherche=${param.recherche}&critere=${param.critere}">Supprimer</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
</div>
<!-- /.container -->

