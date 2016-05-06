'use strict';

angular.module('Home').controller('HomeController', function ($scope, $state, AuthenticationService,HomeService) {

    $scope.user = {};

    $scope.logout = function () {
        AuthenticationService.logout(
            function onSuccess() {
                console.log("logout was successful");
                $state.go("main");
            }, function onError() {
                console.log("logout was with error");
                $state.go("main");
            }
        )
    };

    $scope.getUser = function () {
        HomeService.getUser(
            function onSuccess(response) {
                $scope.user = response.user;
            },
            function onError() {
               console.log("Error");
            }
        )
    }
});