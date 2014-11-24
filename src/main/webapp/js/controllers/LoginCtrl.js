albumFotografico.controller('LoginCtrl', function($scope,$sessionStorage,$location,dataServices){
    var asyncCallbackLogin = function(risposta) {
                            if(risposta.codice === 0){ 
                                $scope.$storage.utente = risposta.risultato;
                                $scope.mostraLogin = false;
                                $location.path('/home');  
                            }
                            else{
                                $scope.mostraLogin = true;
                                $scope.messaggio = risposta.messaggio;
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
                        
    $scope.$storage = $sessionStorage.$default({
       utente: {} 
    });                    
    $scope.login = function(utente){
        if(isNotEmpty())
            dataServices.login(utente,asyncCallbackLogin);
    };
    
    return {
        isEmpty: isNotEmpty
    };
    
});




