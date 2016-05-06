'use strict';

angular.module('Libraries', [
    'angular-sha1',
    'ui.router',
    'ngCookies',
    'ngResource'
]);

angular.module('Login',[]);
angular.module('Home', []);

var app = angular.module('main', [
    'Login',
    'Home',
    'Libraries'])
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider,$resourceProvider) {
        $urlRouterProvider.when('', '/login').when('/', '/login');

        $stateProvider.state('login', {
            url: '/login',
            controller: 'LoginController',
            templateUrl: './angular/templates/states/login.html'
            })
            .state('home', {
                url: '/home',
                controller: 'HomeController',
                templateUrl: './angular/templates/states/home.html'
            })
            .state('main', {
                url: '/',
                controller: '',
                templateUrl: ''
            });

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $resourceProvider.defaults.stripTrailingSlashes = false;
    }).run(function ($rootScope) {
    });
