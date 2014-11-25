albumFotografico.controller('loginAnonimoCtrl', function($scope,$location,dataServices){
    var callbackUtenti = function (data) {
            if (data.codice === 0){
                $scope.utenti = data.risultato;
                $scope.mostraNoUtenti = false;
            }
            else{
                $scope.messNoUtenti = data.messaggio;
                $scope.mostraNoUtenti = true;
            }    
    },
        callbackAlbumUtente = function(data) {
            if (data.codice === 0){
                $scope.albums = data.risultato;
                $scope.mostraTabella = true;
            }
            else {
                $scope.mostraTabella = false;
            }
        };
    dataServices.listaUtenti(callbackUtenti);
    
    $scope.albumUtente = function(username){
        dataServices.albumUtente(username,callbackAlbumUtente);  
    };
});

