albumFotografico.factory('dataServices', function($http, $sessionStorage) {
    var creaAlbum = function(album, confermaCreazione) {

        $http.post('creaAlbum.do', album).
                success(function(data, status, headers, config) {
                    confermaCreazione(data);
                });
    },
            registrati = function(utente, confermaRegistrazione) {

                $http.post('registrati.do', utente).
                        success(function(data, status, headers, config) {
                            confermaRegistrazione(data);
                        });
            },
            login = function(utente, asyncCallbackLogin) {
                $http.post('login.do', utente).
                        success(function(data, status, headers, config) {
                            asyncCallbackLogin(data);
                        });
            },
            listaAlbum = function(utente, stampaListaAlbum) {
                $http.get('listaAlbum.do', {params: {username: utente.username}}).
                        success(function(data, status, headers, config) {
                            stampaListaAlbum(data);
                        });
            },

            aggiungiFoto = function(nomeAlbum, file, confermaUpload) {

                $http.post('salvafoto.do', {url: file, album: nomeAlbum}).
                        success(function(data, status, headers, config) {
                            confermaUpload("success");
                        });
            },
            uploadFileToUrl = function(file, uploadUrl) {
                var fd = new FormData();
                fd.append('file', file);
                $http.post(uploadUrl, fd, {
                    transformRequest: angular.identity,
                    withCredentials: true,
                    headers: {'Content-Type': 'multipart/form-data'}
                    }).
                        success(function(data, status, headers, config) {
                            confermaUpload("success");
                        });
            };

    return {
        creaAlbum: creaAlbum,
        login: login,
        registrati: registrati,
        listaAlbum: listaAlbum,
        aggiungiFoto: aggiungiFoto,
        uploadFileToUrl: uploadFileToUrl
    };

});

