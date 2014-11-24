albumFotografico.controller('uploadFotoCtrl', function ($scope, $routeParams, dataServices, $location, $sessionStorage) {
    var confermaUpload = function (data) {
        if (data.messaggio === "ok") {
            toastr.success("immagine aggiunta con successo");
        }
        else{
            toastr.error("immagine non aggiunta");
        }

    };
    $scope.uploadFile = function() {
        dataServices.aggiungiFoto($routeParams.nomeAlbum,$scope.file, confermaUpload);
    };
    $scope.uploadFile1 = function(){
        var file = $scope.myFile;
        console.log('file is ' + JSON.stringify(file));
        var uploadUrl = 'salvafoto.do';
        dataServices.uploadFileToUrl(file, uploadUrl);
    };
});


