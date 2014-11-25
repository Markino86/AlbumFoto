albumFotografico.controller('RegistrazioneCtrl', function($scope, dataServices, $location, $sessionStorage) {
    var confermaCreazione = function(data) {
        if (data.codice===0) {
            $scope.registrazioKO = false;
            $location.path('/login');
        }
        else {
            $scope.messaggio = data.messaggio;
            $scope.registrazioKO = true;
        }
    },
        isNotEmpty = function () {
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "positionClass": "toast-top-right",
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
        if ($('#user').val().length === 0) {
            toastr.error('Il campo Username non può essere vuoto!');
            return false;
        }
        if ($('#psw').val().length === 0) {
            toastr.error('Il campo Password non può essere vuoto!');
            return false;
        }
        return true;

    };

    $scope.registrati = function(utente) {
        if(isNotEmpty())
            dataServices.registrati(utente, confermaCreazione);
    };
    
    return {
        isEmpty: isNotEmpty
    };
});

