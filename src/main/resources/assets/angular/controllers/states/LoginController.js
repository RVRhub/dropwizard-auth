'use strict';

angular.module('Login').controller('LoginController', function ($window, $scope, $state, $cookies, AuthenticationService) {
    $scope.login = function () {
        $scope.dataLoading = true;
        AuthenticationService.login($scope.credentials,
            function onSuccess(payload) {
                console.log(payload);
                $state.go("home");
            },
            function onError() {
                $state.go("login");
                $scope.error = response.message;
                $scope.dataLoading = false;
            }
        );
    };
});