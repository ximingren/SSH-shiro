var app = angular.module('content', []);
app.controller('content', function ($scope) {
    $scope.userName = sessionStorage.getItem('UserName');
    $scope.logout = function () {
        alert('退出成功');
        sessionStorage.clear();
    };
});