<%-- Affichage d'un formulaire de recherche d'un compte --%>
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
    <div class="card mb-4">
        <div class="card-header">
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="card-title">Nicolas Robillard</h2>
                    <table class="table">
                        <tbody>
                            <tr>
                                <td><p class="card-text">Nom: </p></td>
                                <td><p class="card-text">Nicolas Robillard</p></td>
                            </tr>
                            <tr>
                                <td><p class="card-text">Type: </p></td>
                                <td><p class="card-text">Membre</p></td>
                            </tr>
                            <tr>
                                <td><p class="card-text">Numéro de membre: </p></td>
                                <td><p class="card-text">00000001</p></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

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
            <tr>
                <td>The Pillars of the Earth</td>
                <td>Ken Follet</td>
                <td>22 octobre 2017</td>
            </tr>
            <tr>
                <td>Great Expectations</td>
                <td>Charles Dickens</td>
                <td>10 novembre 2017</td>
            </tr>
            <tr>
                <td>The Road</td>
                <td>Cormac McCarthy</td>
                <td>15 octobre 2017</td>
            </tr>
        </tbody>
    </table>

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
            <tr>
                <td>The Pillars of the Earth</td>
                <td>Ken Follet</td>
                <td>23 octobre 2017</td>
                <td>7 novembre 2017</td>
            </tr>
            <tr>
                <td>Great Expectations</td>
                <td>Charles Dickens</td>
                <td>12 novembre 2017</td>
                <td>26 novembre 2017</td>
            </tr>
            <tr>
                <td>The Road</td>
                <td>Cormac McCarthy</td>
                <td>17 octobre 2017</td>
                <td>31 octobre 2017</td>
            </tr>
        </tbody>
    </table>

</div>
