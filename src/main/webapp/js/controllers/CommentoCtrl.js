//albumFotografico.controller('CommentoCtrl', function($scope, $route, $routeParams, $sessionStorage, $location, dataServices) {
//    var asyncCallbackCommento = function(risposta) {
//        if (risposta.codice === 0) {
//            $scope.commento = risposta.risultato;
////                                $scope.mostraLogin = false;
////                                $location.path('/home');  
//        }
//        else {
////                                $scope.mostraLogin = true;
//            $scope.messaggio = risposta.messaggio;
//        }
//    },
//        isNotEmpty = function() {
//            toastr.options = {
//                "closeButton": true,
//                "debug": false,
//                "positionClass": "toast-top-right",
//                "onclick": null,
//                "showDuration": "300",
//                "hideDuration": "1000",
//                "timeOut": "5000",
//                "extendedTimeOut": "1000",
//                "showEasing": "swing",
//                "hideEasing": "linear",
//                "showMethod": "fadeIn",
//                "hideMethod": "fadeOut"
//            };
//            if ($('#commento').val().length === 0) {
//                toastr.error('Il campo Commento non può essere vuoto!');
//                return false;
//            }
//            return true;
//        };
//
//    $scope.lasciaCommento = function(commento){
//        dataServices.lasciaCommento(commento, $routeParams.nomeAlbum, asyncCallbackCommento)
//    };
//
//    return {
//        isEmpty: isNotEmpty
//    };
//
//});