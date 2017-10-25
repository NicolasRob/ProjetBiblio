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
      The Empty Thone - Bernard Cornwell
    </div>
    <div class="card-body">
      <div class="row">
        <div class="col-lg-2 my-auto">
          <a href="#">
            <img class="img-fluid rounded mx-auto d-block" src="./img/TheEmptyThrone.jpg" alt="" >
          </a>
        </div>
        <div class="col-lg-10">
          <h2 class="card-title">The Empty Throne</h2>
          <table class="table">
            <tbody>
                <tr>
                    <td><p class="card-text">Auteur: </p></td>
                    <td><p class="card-text">Bernard Cornwell</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">Éditeur: </p></td>
                    <td><p class="card-text">HarperCollins</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">Année de publication: </p></td>
                    <td><p class="card-text">2014</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">ISBN: </p></td>
                    <td><p class="card-text">9780007504169</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">Emplacement: </p></td>
                    <td><p class="card-text">F C821e</p></td>
                </tr>
                <tr>
                    <td><p class="card-text">Description: </p></td>
                    <td><p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Possimus aut mollitia eum ipsum fugiat odio officiis odit.</p></td>
                </tr>
            </tbody>
          </table>
          <c:if test="${sessionScope.login != null}">
              <a href="#" class="btn btn-primary">Réserver</a>
          </c:if>
        </div>
      </div>
    </div>
  </div>
</div>

