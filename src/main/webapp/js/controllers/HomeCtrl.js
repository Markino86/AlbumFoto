albumFotografico.controller('HomeCtrl', function ($scope, dataServices, $location, $sessionStorage) {
    var confermaCreazione = function (data) {
        if (data.messaggio === "ok") {
            toastr.success("Album creato con successo");
        }
        else {
            toast.error(data.messaggio)
        }
    }, stampaListaAlbum = function (risposta) {
        $scope.lista = risposta.risultato;
    };
    $scope.creaAlbum = function (album) {
        album.utente = $sessionStorage.utente;
        dataServices.creaAlbum(album, confermaCreazione);
    };
    dataServices.listaAlbum($sessionStorage.utente, stampaListaAlbum);
   
});

