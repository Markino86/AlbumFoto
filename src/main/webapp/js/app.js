var albumFotografico = angular.module('albumFotografico', ['ngRoute','ngStorage']);
albumFotografico.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/login', {
                    templateUrl: 'partials/login-partial.html',
                    controller: 'LoginCtrl'
                }).
                when('/home', {
                    templateUrl: 'partials/home-partial.html',
                    controller: 'HomeCtrl'
                }).
                when('/registrazione', {
                    templateUrl: 'partials/registrazione-partial.html',
                    controller: 'RegistrazioneCtrl'
                }).
                when('/uploadFoto/:nomeAlbum', {
                    templateUrl: 'partials/uploadFoto-partial.html',
                    controller: 'uploadFotoCtrl'
                }).
                when('/visualizzaFoto/:nomeAlbum', {
                    templateUrl: 'partials/visualizzaFoto-partial.html',
                    controller: 'visualizzaFotoCtrl'
                }).
                when('/loginAnonimo',{
                   templateUrl:'partials/loginAnonimo-partial.html',
                   controller: 'loginAnonimoCtrl'
                }).        
                otherwise({
                    redirectTo: '/login'

                });
    }]);
albumFotografico.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
