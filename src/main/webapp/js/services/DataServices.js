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
            uploadFileToUrl = function(file, uploadUrl,nomeAlbum,username,confermaUpload) {
                var fd = new FormData();
                fd.append('file', file);
                fd.append('nomeAlbum', nomeAlbum);
                fd.append('username', username);
                $http.post(uploadUrl, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                    }).
                        success(function(data, status, headers, config) {
                            confermaUpload(data);
                        });
            },
            listaFoto = function(nomeAlbum,confermaVisualizza) {
                $http.get('listaFoto.do', {params: {nomeAlbum : nomeAlbum}}).
                        success(function(data, status, headers, config) {
                           confermaVisualizza(data); 
                });
            },
            listaUtenti = function(callbackUtenti) {
                $http.get('listaUtenti.do').
                        success(function(data, status, headers, config) {
                           callbackUtenti(data); 
                });
            },
            albumUtente = function(username,callbackAlbumUtente) {
                $http.get('albumUtente.do',{params: {username : username}}).
                        success(function(data, status, headers, config) {
                            callbackAlbumUtente(data);
                });
            },
            cambiaProprieta = function(album,callbackCambiaProprieta){
                $http.put('cambiaProprieta.do',album).
                        success(function(data,status,headers,config){
                            callbackCambiaProprieta(data);
                        });
            };

    return {
        creaAlbum: creaAlbum,
        login: login,
        registrati: registrati,
        listaAlbum: listaAlbum,
        uploadFileToUrl: uploadFileToUrl,
        listaFoto : listaFoto,
        listaUtenti : listaUtenti,
        albumUtente : albumUtente,
        cambiaProprieta : cambiaProprieta
    };

});

