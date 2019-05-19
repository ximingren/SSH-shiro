var app = angular.module("login", ['oitozero.ngSweetAlert']);
app.controller('loginCtrl', function ($scope,SweetAlert) {
    $scope.isUserExist=true;
    $scope.wrongPassword=false;
    SweetAlert.swal("1",1);
    $scope.login=function (event) {
        $scope.isUserExist=false;
        $scope.wrongPassword=false;
        var data=JSON.parse(localStorage.getItem("database"))||{};
        for(var property in data){
            if (data.hasOwnProperty(property)) {
                if ($scope.UserName == property) {
                    $scope.isUserExist=true;
                    if ($scope.Password != data[property].password) {
                        $scope.wrongPassword=true;
                    }
                }
            }
        }
    //    TODO for后面不用加分号吗
        if ($scope.isUserExist == false || $scope.wrongPassword == true) {
            event.preventDefault();
        } else {
            sessionStorage.setItem('UserName', $scope.UserName);
        }

    }
});
