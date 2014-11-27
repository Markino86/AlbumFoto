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
    },
        visualizzaCommentiCallback = function (data) {
            if(data.codice === 0){
                $scope.commenti = data.risultato;
            }  
            else {
                $scope.messaggio = data.messaggio;
            }
        };
    
    dataServices.listaFoto($routeParams.nomeAlbum,confermaVisualizza);
    
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
       
});


