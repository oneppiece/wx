/**
 * Created by Administrator on 2016/9/13.
 */


var app = angular.module("index", ['ngRoute']);
/* 控制器 */
app.controller("indexController", ['indexFactory', '$scope', '$http',
    function (indexFactory, $scope, $http) {
        indexFactory.sayIndex("都这样");
    }]);
app.controller("computersController", ['$rootScope', '$scope', '$http',
    function ($rootScope, $scope, $http) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('computersController页面加载完成');
        });
    }]);
app.controller("printersController", ['$rootScope', '$scope', '$http',
    function ($rootScope, $scope, $http) {
        $scope.$on('$viewContentLoaded', function () {
            console.log('printersController页面加载完成');
        });
    }]);
/* 路由 */
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when("/", {
        controller: 'indexController',
        templateUrl: "index1.html"
    }).when("/computers", {
        controller: 'computersController',
        templateUrl: "computers.html"
    }).when("/printers", {
        controller: 'printersController',
        templateUrl: "printers.html"
    }).when("/user", {
        controller: 'printersController',
        templateUrl: "index.html"
    }).otherwise({
        redirectTo: '/'
    });
}]);