<%-- Affichage d'un formulaire de recherche d'un compte --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Affichage des informations du compte recherché --%>

<div class="container Site-Content">

    <h1 class="mt-4 mb-3">Gestion des comptes</h1>

    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="go?action=afficherAccueil">Accueil</a>
        </li>
        <li class="breadcrumb-item">
            Administration
        </li>
        <li class="breadcrumb-item active">Gestion des comptes</li>
    </ol>
    <div class="card mb-4">
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="card-title"></h2>
                    <form class="form-horizontal" action="go">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="numero">Numero de membre:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="recherche" name="numero">
                            </div>
                        </div>
                        <input type="hidden" name="action" value="afficherGestionCompte" />
                        <div class="form-group">        
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Rechercher</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <a class='col-sm-2' href="go?action=afficherCreationCompte">Cr&eacute;er un nouveau compte</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${requestScope.compte !=null}">
        <div class="card mb-4">
            <div class="card-header">
            </div>            
            <div class="card-body">
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="card-title">${requestScope.compte.getNom()} ${requestScope.compte.getPrenom()}</h2>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <td><p class="card-text">Nom: </p></td>
                                    <td><p class="card-text">${requestScope.compte.getPrenom()} ${requestScope.compte.getNom()}</p></td>
                                </tr>
                                <tr>
                                    <td><p class="card-text">Type: </p></td>
                                    <c:if test= "${requestScope.compte.getType() eq 1}">
                                        <td><p class="card-text">Membre</p></td>
                                    </c:if>
                                    <c:if test="${requestScope.compte.getType() eq 2}">
                                        <td><p class="card-text">Administrateur</p></td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td><p class="card-text">Numéro de membre: </p></td>
                                    <td><p class="card-text">${requestScope.compte.getNumero()}</p></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
</c:if>
<c:if test="${requestScope.compte !=null}">
        <h2>R&eacute;servations</h2>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>Date de r&eacute;servation</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${requestScope.listeReservation}">
                    <tr>
                        <td>${reservation.getExemplaire().getEdition().getOuvrage().getTitre()}</td>
                        <td> ${reservation.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom()} ${reservation.getExemplaire().getEdition().getOuvrage().getAuteur().getNom()}</td>
                        <td>${reservation.getDateDebut()}</td>
                        <td>${reservation.getDateFin()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
<c:if test="${requestScope.compte !=null}">
        <h2>Emprunts</h2>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>Date d'emprunt</th>
                    <th>Date de retour</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="emprunt" items="${requestScope.listeEmprunt}">
                            <tr>
                                <td>${emprunt.getExemplaire().getEdition().getOuvrage().getTitre()}</td>
                                <td>${emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom()} ${emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getNom()}</td>
                                <td>${emprunt.getDateDebut()}</td>
                                <td>${emprunt.getDateFin()}</td>
                            </tr>
                        </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
