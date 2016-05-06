'use strict';

app.factory('HomeService', function ($state,$cookies, Network) {
    return {
        getUser: function (onSuccess, onError) {
            var headers = $cookies.getObject('userHeader');
            console.log(headers);
            Network.sendGetHeaders("./service/user/admin", {},headers, onSuccess, onError);
        }
    };
});