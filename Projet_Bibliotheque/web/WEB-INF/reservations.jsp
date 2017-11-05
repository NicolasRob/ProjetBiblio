<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container Site-Content">

  <h1 class="mt-4 mb-3">R&eacute;servations</h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="index.html">Accueil</a>
    </li>
    <li class="breadcrumb-item">
      Compte
    </li>
    <li class="breadcrumb-item active">Mes r&eacute;servations</li>
  </ol>
     
  <table class="table table-striped table-bordered">
    <thead>
      <tr>
        <th>Titre</th>
        <th>Auteur</th>
        <th>Date de r&eacute;servation</th>
      </tr>
    </thead>
    <tbody> 
            <c:forEach var="reservation" items="${requestScope.reservations}">
                <tr>
                    <td>${reservation.getExemplaire().getEdition().getOuvrage().getTitre()}</td>
                    <td>${reservation.getExmplaire().getEdtion().getOuvrage.getAuteur().getNom()}</td>
                    <td>${reservation.getDate()}</td>
                </tr>
            </c:forEach>
    </tbody>
  </table>

</div>