albumFotografico.controller('HomeCtrl', function ($scope, $route, dataServices, $location, $sessionStorage, $modal) {
    var confermaCreazione = function (data) {
        if (data.codice === 0) {
            toastr.success("Album creato con successo");
            $route.reload();
        }
        else {
            toastr.error(data.messaggio)
        }
    }, stampaListaAlbum = function (data) {
        if(data.codice===0){
            $scope.lista = data.risultato;
            $scope.mostraAlbumUtenti = true;
        }else{
            $scope.mostraAlbumUtenti = false;
            $scope.messaggioNoAlbum = data.messaggio;
        }  
    }, isNotEmpty = function () {
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
        if ($('#album').val().length === 0) {
            toastr.error('Il campo nome album non può essere vuoto!');
            return false;
        }
        return true;
    }, callbackCambiaProprieta = function (data) {
        if (data.codice === 0) {
            toastr.success(data.messaggio);
            $route.reload();
        }
        else {
            toastr.error(data.messaggio);
        }

    }, callbackEliminaAlbum = function (data) {
        if (data.codice === 0)
            toastr.success(data.messaggio);
        $route.reload();
    },
            callbackNomi = function (risposta) {
                if (risposta.codice === 0) {
                    $scope.mostraUtenti = true;
                    $scope.nomiUtenti = risposta.risultato;
                }
                else {
                    $scope.mostraUtenti = false;
                    $scope.messaggio = risposta.messaggio;
                }
            };

    $scope.creaAlbum = function (album) {
        if (isNotEmpty()) {
            album.utente = $sessionStorage.utente;
            dataServices.creaAlbum(album, confermaCreazione);
        }
    };
    dataServices.listaAlbum($sessionStorage.utente, stampaListaAlbum);

    $scope.cambiaProprieta = function (album) {
        dataServices.cambiaProprieta(album, callbackCambiaProprieta);
    };

    $scope.eliminaAlbum = function (nomeAlbum) {
        var modalInstance = $modal.open({
            templateUrl: 'eliminaAlert.html',
            controller: 'HomeCtrlModal'
        });

        modalInstance.result.then(function () {
            dataServices.eliminaAlbum(nomeAlbum, $sessionStorage.utente.username, callbackEliminaAlbum);
        }, function () {
            //$log.info('Modal dismissed at: ' + new Date());
        });
    };

    $scope.logout = function () {
        $sessionStorage.$reset();
        $location.path('/login');
    };
    $scope.listaNomi = function (lettere) {
        if (lettere === "")
            $route.reload();
        else
            dataServices.listaNomi(lettere, callbackNomi);
    };

    return {
        isEmpty: isNotEmpty
    };

});

albumFotografico.controller('HomeCtrlModal', function ($scope, $modalInstance) {
  $scope.ok = function () {
    $modalInstance.close();
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});