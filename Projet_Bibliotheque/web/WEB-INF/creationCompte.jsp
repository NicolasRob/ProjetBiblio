<%-- Formulaire pour ajouter un nouveau compte --%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container Site-Content">

    <h1 class="mt-4 mb-3">Créer un nouveau compte</h1>

    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="go?action=afficherAccueil">Accueil</a>
        </li>
        <li class="breadcrumb-item">
            Administration
        </li>
        <li class="breadcrumb-item active">Création d'un compte</li>
    </ol>

    <div class="card mb-4">
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="card-title"></h2>
                    <p class="text-danger">${requestScope.erreur}</p>
                    <p class="text-danger">${requestScope.erreurException}</p>
                    <p class="text-success">${param.message}</p>
                    <form class="form-horizontal" action="go">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="numero">Numero:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="numero" name="numero">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="mdp">Mot de passe:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="mdp" name="mdp">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="prenom">Prenom:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="prenom" name="prenom">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="nom">Nom:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="nom" name="nom">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="type">Type:</label>
                            <div class="col-sm-10">          
                                <select class="form-control" id="type" name="type">
                                    <option value="1">Membre</option>
                                    <option value="2">Employé</option>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="action" value="creerCompte" />
                        <div class="form-group">        
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Créer</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
