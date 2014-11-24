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
    };
    dataServices.listaUtenti(callbackUtenti);
});

