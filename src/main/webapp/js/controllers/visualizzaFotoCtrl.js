albumFotografico.controller('visualizzaFotoCtrl', function($scope,$routeParams, dataServices, $location, $sessionStorage) {
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
    };
    
    dataServices.listaFoto($routeParams.nomeAlbum,confermaVisualizza);
    
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };

});


