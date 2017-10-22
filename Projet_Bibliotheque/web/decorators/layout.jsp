<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Bibliotheque municipale de Saint-Baudille</title>

    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/modern-business.css" rel="stylesheet">

  </head>

  <body class="Site">

    <!-- Menu -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
          <a class="navbar-brand" href="index.html">Biblioth&egrave;que Saint-Baudille</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="go?action=accueil">Accueil</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="go?action=catalogue">Catalogue</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCompte" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Mon compte
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownCompte">
                <a class="dropdown-item" href="go?action=afficherReservations">Mes r&eacute;servations</a>
                <a class="dropdown-item" href="go?action=afficherEmprunts">Mes emprunts</a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="go?action=afficherSuggestion">Suggestion</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAdmin" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Administration
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownAdmin">
                <a class="dropdown-item" href="go?action=afficherGestionCatalogue">Gestion du catalogue</a>
                <a class="dropdown-item" href="go?action=afficherGestionCompte">Gestion des comptes</a>
                <a class="dropdown-item" href="go?action=afficherGestionSuggestion">Consulter les suggestions</a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="go?action=afficherLogin">Se connecter</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Corps de la page -->
    <decorator:body />

    <!-- Pied de page -->
    <footer class="py-4 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Blais Robillard 2017</p>
      </div>
    </footer>

    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

  </body>

</html>

