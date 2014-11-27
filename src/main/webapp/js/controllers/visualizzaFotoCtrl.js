albumFotografico.controller('visualizzaFotoCtrl', function($scope,$routeParams,$route, dataServices, $location, $sessionStorage) {
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
    },eliminaFotoCallback = function(data){
        if(data.codice === 0){
            toastr.success(data.messaggio);
            $route.reload();
        }    
        else {
            toastr.error(data.messaggio);
        }  
    };
    
    dataServices.listaFoto($routeParams.nomeAlbum,confermaVisualizza);
    
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
    }
       
});


