albumFotografico.controller('uploadFotoCtrl', function ($scope, $routeParams, dataServices, $location, $sessionStorage) {
    var confermaUpload = function (data) {
            $scope.messaggio = data.messaggio;

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


