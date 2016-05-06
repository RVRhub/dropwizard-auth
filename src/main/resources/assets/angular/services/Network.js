'use strict';

app.factory('Network', function ($http, $cookies, $state) {
    function isResponseDataValid(responseData) {
        if (!responseData.status)
            return false;

        if (responseData.status !== "OK")
            return false;

        return responseData != undefined;
    }

    function isResponseValid(response) {
        if (!response)
            return false;

        return isResponseDataValid(response.data);
    }

    function getRequest(method, url, parameters, headers) {
        return ((method === "GET") ? $http({
            method: method,
            url: url,
            params: parameters,
            headers: headers
        }) : $http({method: method, url: url, data: parameters}));
    }

    var sendQuery = function (url, method, parameters, headers, onSuccess, onError) {
        var russian = /^[ -~]*$/;
        for (var p in parameters) {
            if (parameters.hasOwnProperty(p)) {
                if (typeof parameters[p] == 'string') {
                    if (!russian.test(parameters[p])) {
                        return onError("Use only Latin letters and special symbols, please try again.");
                    }
                }
            }
        }
        getRequest(method, url, parameters, headers).then(function onSuccessCallback(response) {
                if (!isResponseValid(response)) {
                    if (onError)
                        return onError(response.data.message);
                    return $state.go("login");
                }

                if (onSuccess) {
                    console.log(response.data);
                    onSuccess(response.data);
                }
            },
            function (message) {
                console.log(message);
                return $state.go("login");
            });
    };

    return {
        sendGet: function (url, parameters, onSuccess, onError) {
            sendQuery(url, "GET", parameters, {}, onSuccess, onError);
        },

        sendPost: function (url, parameters, onSuccess, onError) {
            sendQuery(url, "POST", parameters, {}, onSuccess, onError);
        },

        sendGetHeaders: function (url, parameters, headers, onSuccess, onError) {
            sendQuery(url, "GET", parameters, headers, onSuccess, onError);
        }
    }
});