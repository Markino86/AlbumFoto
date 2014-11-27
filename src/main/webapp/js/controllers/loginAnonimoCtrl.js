albumFotografico.controller('loginAnonimoCtrl', function($scope,$routeParams,$location,dataServices){
    var callbackAlbumUtente = function(data) {
            if (data.codice === 0){
                $scope.utente = $routeParams.username;
                $scope.albums = data.risultato;
            }
            else {
                
            }
        };
    dataServices.albumUtente($routeParams.username,callbackAlbumUtente);  
});

