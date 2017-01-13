var app = angular.module("rount", ['ngRoute']);
/* 自定义表单验证指令 */
app.directive('ensureUnique', ['$http', '$timeout', '$window',
// '$location',
    function ($http, $timeout, $window) {
        // console.log($location);
        return {
            restrict: "A",
            require: 'ngModel',
            link: function (scope, ele, attrs, loginController) {
                console.log(ele);
                scope.$watch(attrs.ngModel, function (n) {
                    if (!n)
                        return;
                    $timeout.cancel($window.timer);
                    $window.timer = $timeout(function () {
                        $http({
                            method: 'get',
                            url: scope.myUrl + '/ngApi/login/name', // 根据换成自己的url
                            params: {
                                "name1": n
                            }
                        }).success(function (data) {
                            console.log(data);
                            loginController.$setValidity('unique', data); // 这个取决于你返回的，其实就是返回一个是否正确的字段，具体的这块可以自己修改根据自己的项目
                        }).error(function (data) {
                            loginController.$setValidity('unique', false);
                        });
                    }, 500);
                });
            }
        };
    }]);
/* 依赖注入service */
app.service("indexFactory", function () {
    return {
        sayIndex: function (name) {
            alert(name + " Hello!");
        }
    }
});
/* 控制器 */
app.controller("loginController", [
    '$scope',
    '$http',
    '$location',
    function ($scope, $http, $location) {
        $scope.myUrl = $location.absUrl();
        $scope.myUrl = $scope.myUrl.substring(0, $scope.myUrl
            .lastIndexOf("/"));
        $scope.initForm = {
            username: 'dzy',
            password: 'dzy',
            rememberMe: true,
            number: 'a',
            selectedRoless: '',
            roles: [{
                roleId: 0,
                roleName: "user"
            }, {
                roleId: 1,
                roleName: "admin"
            }, {
                roleId: 2,
                roleName: "dba"
            }]
        };
        $scope.jumpToUrl = function (path) {
            $location.path(path);
        };
        $scope.reset = function () {
            $scope.user = angular.copy($scope.initForm);
        };
        $scope.submit = function () {
            $scope.formData = {};
            $scope.processForm();
            console.log($scope.user);
            console.log('0.' + $.param($scope.formData));
        };
        $scope.processForm = function () {
            $http(
                {
                    method: 'POST',
                    url: $scope.myUrl + '/login',
                    params: $scope.user,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).success(
                function (data) {
                    console.log(data);
                    if (data.messageId == 100) {
                        alert("登录失败" + data.messageDetails);
                    }
                    if (data.messageId == 200) {
                        // $scope.jumpToUrl(data.messageDetails);
                        window.location.href = $scope.myUrl + "/"
                            + data.messageDetails;
                    }
                }).error(function (data) {
                alert("失败");
            });
        };
        $scope.reset();
    }]);