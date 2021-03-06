<%-- Affichage d'un formulaire de recherche du catalogue et des r�sultats appropri�s --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">

    <h1 class="mt-4 mb-3">Catalogue
    </h1>

    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="go?action=afficherAccueil">Accueil</a>
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
                                    <option value="auteur">Nom de famille de l'auteur</option>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="action" value="catalogue" />
                        <div class="form-group">        
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Rechercher</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${!empty param.recherche && !empty param.critere}">
        <div class="row">
            <c:forEach var="edition" items="${requestScope.editions}">
                <div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">
                    <div class="card h-100">
                        <a href="go?action=detailsLivre&id=${edition.getId()}&recherche=${param.recherche}&critere=${param.critere}"><img class="card-img-top" src="/Projet_Bibliotheque/img/${edition.getImage()}" alt="" height="300px"></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="go?action=detailsLivre&id=${edition.getId()}">${edition.getOuvrage().getTitre()}</a>
                            </h4>
                            <p class="card-text">${edition.getOuvrage().getAuteur().getPrenom()} ${edition.getOuvrage().getAuteur().getNom()}</p>
                            <p class="card-text">${edition.getEditeur()}, ${edition.getDatePublication()}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

