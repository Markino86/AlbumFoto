
albumFotografico.controller('visualizzaFotoCtrl', function($scope, $route, $routeParams, dataServices, $location, $sessionStorage) {

    var confermaVisualizza = function (data) {
        if(data.codice === 0){
            $scope.album = $routeParams.nomeAlbum;
            $scope.username = $sessionStorage.utente.username;
            $scope.listaF = data.risultato;
            $scope.mostraErrFoto = false;

        }    
        else {
            $scope.messaggioFoto = data.messaggio;
            $scope.mostraErrFoto = true;
        }    
    },
    asyncCallbackCommento = function(risposta) {
        if (risposta.codice === 0) {
            $scope.commento = risposta.risultato;
            $route.reload();
        }
        else {
            $scope.messaggio = risposta.messaggio;
        }
    },
        visualizzaCommentiCallback = function (data) {
            if(data.codice === 0){
                $scope.commenti = data.risultato;
            }  
            else {
                $scope.messaggio = data.messaggio;
            }
    },
    eliminaFotoCallback = function(data){
        if(data.codice === 0){
            toastr.success(data.messaggio);
            $route.reload();
        }    
        else {
            toastr.error(data.messaggio);
        }  
    },
    mostraEliminaCallback = function(data){
        if(data.codice === 0){
            $scope.mostraElimina=true;
        }
        else {
            $scope.mostraElimina=false;
        }
    };
    
    dataServices.listaFoto($routeParams.nomeAlbum,confermaVisualizza);
    
    $scope.inviaCommento = function(commento){
        dataServices.inviaCommento(commento, $routeParams.nomeAlbum, $sessionStorage.utente.username, asyncCallbackCommento);
    };
    
    dataServices.visualizzaCommenti($routeParams.nomeAlbum, visualizzaCommentiCallback);
    
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
    $scope.mostraLogout = function(){
        if($sessionStorage.utente.username === undefined)
                    return false;
        else
                    return true;
    };
    
    $scope.eliminaSingleFoto = function(nomeFoto){
        dataServices.eliminaSingleFoto(nomeFoto,eliminaFotoCallback)
    };
    
    dataServices.mostraElimina($routeParams.nomeAlbum, $sessionStorage.utente.username, mostraEliminaCallback);     
});


