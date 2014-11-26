albumFotografico.controller('HomeCtrl', function ($scope,$route, dataServices, $location, $sessionStorage) {
    var confermaCreazione = function (data) {
        if (data.codice === 0) {
            toastr.success("Album creato con successo");
            $route.reload();
        }
        else {
            toastr.error(data.messaggio)
        }
    }, stampaListaAlbum = function (risposta) {
        $scope.lista = risposta.risultato;
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
     },callbackCambiaProprieta = function(data){
         if(data.codice===0){
          toastr.success(data.messaggio);
            $route.reload();
        }
        else {
            toastr.error(data.messaggio);
        }
     },callbackEliminaAlbum = function(data){
        if(data.codice===0)
            $scope.messaggio = data.messaggio;
            $location.path("/home");
         
     }; 
    
    $scope.creaAlbum = function (album) {
        if(isNotEmpty()){
        album.utente = $sessionStorage.utente;
        dataServices.creaAlbum(album, confermaCreazione);
        }
    };
    dataServices.listaAlbum($sessionStorage.utente, stampaListaAlbum);
    
    $scope.cambiaProprieta = function(album){
        dataServices.cambiaProprieta(album,callbackCambiaProprieta);
    };
    
    $scope.eliminaAlbum = function(album){
        dataServices.eliminaAlbum(album,callbackEliminaAlbum);
    };
    
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
    
    return {
        isEmpty: isNotEmpty
    };
   
});

