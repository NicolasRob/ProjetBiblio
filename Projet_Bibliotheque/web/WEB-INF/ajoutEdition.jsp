<%-- Formulaire pour ajouter une nouvelle edition à un ouvrage --%>

<div class="container Site-Content">

    <h1 class="mt-4 mb-3">Ajouter une Édition</h1>

    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="go?action=afficherAccueil">Accueil</a>
        </li>
        <li class="breadcrumb-item">
            <a href="go?action=afficherGestionCatalogue">Gestion du catalogue</a>
        </li>
        <li class="breadcrumb-item active">Ajout d'une édition</li>
    </ol>
    <div class="card mb-4">
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="card-title"></h2>
                    <form class="form-horizontal" action="go" enctype="multipart/form-data" method="POST">
                        <p class="text-danger" >${requestScope.erreurException}</p>
                        <p class="text-danger" >${param.erreurAjout}</p>
                        <p class="text-success" >${param.message}</p>
                        <p class="text-danger" >${param.messageErreur}</p>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="titre">Titre:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="titre" name="titre" 
                                       readonly value="${requestScope.ouvrage.getTitre()}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="auteur">Auteur:</label>
                            <div class="col-sm-10">          
                                <input type="text" class="form-control" id="auteur" name="auteur" 
                                       readonly value="${requestScope.ouvrage.getAuteur().getPrenom()} ${requestScope.ouvrage.getAuteur().getNom()}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="isbn">ISBN:</label>
                            <div class="col-sm-10">          
                                <input type="text" class="form-control" id="isbn" name="isbn" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="editeur">Éditeur:</label>
                            <div class="col-sm-10">          
                                <input type="text" class="form-control" id="editeur" name="editeur">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="pages">Nombre de pages:</label>
                            <div class="col-sm-10">          
                                <input type="text" class="form-control" id="pages" name="pages">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="date">Date de publication (YYYY-MM-DD):</label>
                            <div class="col-sm-10">          
                                <input type="text" class="form-control" id="date" name="date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="image">Image:</label>
                            <div class="col-sm-10">          
                                <input type="file" class="form-control" id="image" name="image">
                            </div>
                        </div>
                        <input type="hidden" name="id" value="${requestScope.ouvrage.getId()}" />
                        <input type="hidden" name="action" value="ajouterEdition" />
                        <div class="form-group">        
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Ajouter</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
