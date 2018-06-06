<%@include file="permission.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <base href="${pageContext.request.contextPath}/"/>
  <link rel="stylesheet" href="resources/css/base.css"/>
  <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="resources/css/bootstrap-cosmo.min.css"/>
  <link rel="stylesheet" href="resources/css/jquery-ui.css"/>
  <script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="resources/js/jquery-ui-1.11.2.js"></script>
  <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="resources/js/angular.min.js"></script>
  <script type="text/javascript" src="resources/js/global_util.js"></script>
  <script type="text/javascript" src="resources/js/global_error_handler.js"></script>
  <script src="resources/lang/ge.js"></script>

  <script>
    function menuCtrl($scope, $http) {
      $scope.version = '';

      function user(res) {
        $scope.currentUser = res.firstName + ' ' + res.lastName;
      }

      ajaxCall($http, "misc/get-current-user", {}, user);
    }
  </script>
</head>
<body ng-app="app" class="bg">
<div class="container-fluid">
  <div>
    <div class="row">
      <div class="col-md-1" ng-controller="menuCtrl">
        <div class="row logo">
          <a href="home" class="navbar-brand"></a>
          <br>
          <span class="version " ng-bind="version"></span>
        </div>
        <div class="row side left">
          <div class="vertical-menu">
            <div class="btn-group-vertical leftMenu upper-font">
              <a href="home" class="btn btn-default">სტრუქტურა</a>
              <a href="info" class="btn btn-default">ჩემი გვერდი</a>
              <c:if test="<%=hasPermissions(request, SysPermissionDTO.HR_MENU_TEST)%>">
                <a href="test?type=test" class="btn btn-default">შეფასება</a>
              </c:if>
              <a href="vote" class="btn btn-default">კითხვარი</a>
              <c:if test="<%=hasPermissions(request, SysPermissionDTO.HR_MENU_REPORT)%>">
                <a href="report?type=scores" class="btn btn-default">რეპორტი</a>
              </c:if>
            </div>
          </div>
          <div class="line hidden-sm hidden-xs"></div>
        </div>
      </div>
      <div class="col-md-11">
        <div class="row">
          <div class=" col-md-10"></div>
          <div class="col-md-2"></div>
        </div>
        <script>
          function logout() {
            $.post("/logout", {}, function () {
              window.location = "index";
            });
          }
        </script>
        <div class="row">
          <div class="col-md-2 col-md-offset-10" ng-controller="menuCtrl">
            <a class="logout pull-right" href="logout">&nbsp;&nbsp;{{currentUser}}</a>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12 main-content">
            <br/>

