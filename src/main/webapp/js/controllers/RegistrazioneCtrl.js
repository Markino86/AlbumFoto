albumFotografico.controller('RegistrazioneCtrl', function($scope, dataServices, $location, $sessionStorage) {
    var confermaCreazione = function(data) {
        if (data.messaggio === "ok") {
            $location.path('/login');
        }
        else {
            toastr.error(data.messaggio);
        }

    };

    $scope.registrati = function(utente) {
        dataServices.registrati(utente, confermaCreazione);
    };
});

