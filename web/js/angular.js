var app = angular.module("register", []);
app.controller("registerCtrl", function ($scope) {
    $scope.form = {};
    $scope.Vertification='';
    $scope.existName=false;
    $scope.hasUse=false;
    $scope.codeError=false;
    $scope.form.info='';
    $scope.form.vertifications = ['Email', 'Phone'];
    $scope.PostForm = function () {
        if ($scope.code != '123456') {
            $scope.codeError=true;
            return false;
        }
        var data = JSON.parse(localStorage.getItem("database")) || {};
        for(var property in data){
            if (data.hasOwnProperty(property)) {
                if ($scope.UserName == property) {
                    $scope.existName=true;
                    return false;
                }
                for(var verifyInfo in data[property]){
                    alert(verifyInfo);
                    if (verifyInfo == $scope.Vertification && $scope.form.info == data[property][verifyInfo]) {
                        $scope.hasUse=true;
                        return false;
                    }
                }
            }
        }
        data[$scope.UserName]={
            password:$scope.password
        };
        data[$scope.UserName][$scope.Vertification]=$scope.form.info;
        localStorage.setItem('database', JSON.stringify(data));
        $scope.codeError=false;
        $scope.existName=false;
        $scope.hasUse=false;
        alert("注册成功");
        return true;
    };
    $scope.Clear = function () {
        $scope.hasUse=false;
    };
});