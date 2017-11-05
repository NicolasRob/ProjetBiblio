    <%-- Affichage des emprunts du compte connecté --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="container Site-Content">

        <h1 class="mt-4 mb-3">Emprunts</h1>

        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="go?action=afficherAccueil">Accueil</a>
            </li>
            <li class="breadcrumb-item">
                Compte
            </li>
            <li class="breadcrumb-item active">Mes emprunts</li>
        </ol>
    lol
        <table class="table table-striped table-bordered">
            lol
            <thead>
                lol
                <tr>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>Date d'emprunt</th>
                    <th>Date de retour</th>
                </tr>
            </thead>
            <tbody>
                    ${requestScope}
                    <c:forEach var="emprunt" items="${requestScope.listeEmprunt}">
                        <tr>
                            <td>${emprunt.getExemplaire().getEdition().getOuvrage().getTitre()}</td>
                            <td>${emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getNom().concat(" ").concat(reservation.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom())}</td>
                            <td>${emprunt.getDateDebut()}</td>
                            <td>${emprunt.getDateFin()}</td>
                        </tr>
                    </c:forEach>
                        lol
            </tbody>
        </table>
                        lol
    </div>