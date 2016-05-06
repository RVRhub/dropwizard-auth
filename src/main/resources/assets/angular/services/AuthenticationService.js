'use strict';

app.factory('AuthenticationService', function ($state, $cookies, Network,sha1) {
    return {
        login: function (credentials, onSuccess, onError) {
            var ascii = /^[ -~]+$/;
            if (!ascii.test(credentials.username) || (!ascii.test(credentials.password)))
                return onError("Username or password must be written using only Latin letters and special symbols, please try again.");
           // var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" +  sha1.hash(credentials.password))} : {};
            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" +  credentials.password)} : {};
            $cookies.putObject('userHeader', headers);
            Network.sendGetHeaders('./service/user/admin2', {}, headers, onSuccess, onError);
        },

        logout: function (onSuccess, onError) {
            $cookies.remove('userHeader');
            $state.go('login');
            //Network.sendGet("./service/authentication/logout", {}, onSuccess, onError);
        }
    };
});