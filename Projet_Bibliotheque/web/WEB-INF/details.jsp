<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">

  <h1 class="mt-4 mb-3">Détails
    <small>Subheading</small>
  </h1>

  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="index.html">Accueil</a>
    </li>
    <li class="breadcrumb-item">Recherche</li>
    <li class="breadcrumb-item active">Détails</li>
  </ol>

  <div class="card mb-4">
    <div class="card-header">
      ${requestScope.edition.getOuvrage().getTitre()} - ${requestScope.edition.getOuvrage().getAuteur().getPrenom()} ${requestScope.edition.getOuvrage().getAuteur().getNom()}
    </div>
    <div class="card-body">
      <div class="row">
        <div class="col-lg-2 my-auto">
          <a href="#">
            <img class="img-fluid rounded mx-auto d-block" src="./img/${requestScope.edition.getImage()}.jpg" alt="" >
          </a>
        </div>
        <div class="col-lg-10">
          <h2 class="card-title">${requestScope.edition.getOuvrage().getTitre()}</h2>
          <table class="table">
            <tbody>
                <tr>
                    <td><p class="card-text">Auteur: </p></td>
                    <td><p class="card-text">${requestScope.edition.getOuvrage().getAuteur().getPrenom()} ${requestScope.edition.getOuvrage().getAuteur().getNom()}</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">Éditeur: </p></td>
                    <td><p class="card-text">${requestScope.edition.getEditeur()}</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">Date de publication: </p></td>
                    <td><p class="card-text">${requestScope.edition.getDatePublication()}</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">Nombre de pages: </p></td>
                    <td><p class="card-text">${requestScope.edition.getNombrePage()}</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">ISBN: </p></td>
                    <td><p class="card-text">${requestScope.edition.getIsbn()}</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">Exemplaires: </p></td>
                    <td>
                        <ol>
                            <c:forEach var="exemplaire" items="${requestScope.exemplaires}">
                                <li><p class="card-text">${exemplaire.getKey().getEmplacement()} : <c:if test="${!empty exemplaire.getValue()}" >
                                            Reservé jusqu'au ${exemplaire.getValue()}
                                            </c:if>
                                            <c:if test="${empty exemplaire.getValue()}" >
                                            Disponible
                                            </c:if>
                                            </p></li>           
                            </c:forEach>
                        </ol>
                    </td>
                </tr>
            </tbody>
          </table>
          <c:if test="${sessionScope.login != null}">
              <a href="go?action=reserver?id=${requestScope.edition.getId()}" class="btn btn-primary">Réserver</a>
          </c:if>
        </div>
      </div>
    </div>
  </div>
</div>

