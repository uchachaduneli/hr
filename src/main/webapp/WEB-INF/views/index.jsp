<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <base href="${pageContext.request.contextPath}/"/>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="resources/css/base.css"/>
  <link rel="stylesheet" href="resources/css/bootstrap-cosmo.min.css"/>
  <script src="resources/js/jquery-1.9.1.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
  <script src="resources/js/angular.min.js"></script>
  <script src="resources/js/global_util.js"></script>
  <script src="resources/js/global_error_handler.js"></script>
  <script src="resources/js/dom_util.js"></script>
  <script src="resources/lang/ge.js"></script>
  <title>HR</title>
  <script>
    var app = angular.module("app", []);
    app.controller("loginCtrl", function ($scope, $http) {
      $scope.t = function (s) {
        return Lang.get(s);
      };
      $scope.user = {};

      $scope.login = function () {
        function errorLogin(res) {
          alert('არასწორი მომხმარებელი ან პაროლი');
        }

        ajaxCall($http, "index?username=" + $scope.user.username + "&password=" + $scope.user.password, {}, reload, errorLogin);
      };
    });
  </script>
</head>
<body ng-app="app" class="auth_bg">
<div class="container" data-role="none" ng-controller="loginCtrl">
  <div class="row">
    <div class="col-md-12">
      <br/>
      <div class="row">
        <div class="col-md-12">
          <div class="center-block" id="logo"></div>
          <div class="center-block" id="slogan"></div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4 col-md-offset-4">
          <form action="index" method="post">
            <div class="row">
              <br/>
              <div class="col-md-10 col-md-offset-1">
                <div class="form-group" id="UserNameGroup">
                  <input type="text" id="username" name="username" ng-model="user.username"
                         class="form-control input-sm" placeholder="{{t('username')}}"/>
                </div>
                <div class="form-group" id="PasswordGroup">
                  <input type="password" id="password" name="password" ng-model="user.password"
                         class="form-control input-sm" placeholder="{{t('password')}}"/>
                </div>
              </div>
            </div>
            <div class="form-group text-center">
              <input type="submit" ID="loginButton" class="btn btn-primary btn-sm" value="ავტორიზაცია"/>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>

