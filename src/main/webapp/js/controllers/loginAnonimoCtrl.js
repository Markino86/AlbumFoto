albumFotografico.controller('loginAnonimoCtrl', function($scope,$routeParams,$sessionStorage, $location, dataServices){
    var callbackAlbumUtente = function(data) {
            if (data.codice === 0){
                $scope.utente = $routeParams.username;
                $scope.albums = data.risultato;
            }
        };
    dataServices.albumUtente($routeParams.username,callbackAlbumUtente);  
    
    $scope.mostraLogout = function(){
        if($sessionStorage.utente.username === undefined)
            return false;
        else
            return true;
    };
    
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
});

