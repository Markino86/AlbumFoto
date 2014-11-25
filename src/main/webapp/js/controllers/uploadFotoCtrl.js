albumFotografico.controller('uploadFotoCtrl', function ($scope, $routeParams, dataServices, $location, $sessionStorage) {
    var confermaUpload = function (data) {
        if(data.codice===0)
            toastr.success(data.messaggio);
        else if(data.codice ===1)
            toastr.error(data.messaggio);
        else
            toastr.warning(data.messaggio);
    };
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log('file is ' + JSON.stringify(file));
        var uploadUrl = 'salvafoto.do';
        dataServices.uploadFileToUrl(file, uploadUrl,$routeParams.nomeAlbum,$sessionStorage.utente.username,confermaUpload);
    };
    
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
});


