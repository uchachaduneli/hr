<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="resources/css/style.min.css"/>
<script src="resources/js/jstree.min.js"></script>

<script>
  var app = angular.module("app", []);
  app.controller('structureTreeCtrl', function ($scope, $http, $filter, $location) {

    $scope.personal = {};
    $scope.getPersonalInfo = function (personalId) {
      function successGetPersonal(res) {
        $scope.personal = res;
        $scope.personal.birthDate = $filter("date")($scope.personal.birthDate, 'dd/MM/yyyy');
        $('#exampleModal').modal();
      }

      ajaxCall($http, "personal/get-personal-by-id?personalId=" + personalId, {}, successGetPersonal);

    };
    $scope.syncTree = function () {
      ajaxCall($http, "personal/syncronize-tree", {}, reload);
    };

    var to = false;
    $('#who_query').keyup(function () {
      if (to) {
        clearTimeout(to);
      }
      to = setTimeout(function () {
        var v = $('#who_query').val();
        $('#jstree').jstree(true).search(v);
      }, 250);
    });

    function successGetTree(res) {
      $('#jstree').jstree({
        "core": {
          'data': res
        }, "types": {
          "file": {
            "icon": "glyphicon glyphicon-file",
            "valid_children": []
          }
        }, "contextmenu": {
          "items": function ($node) {
            if ($node.type == "file" && <%=hasPermissions(request, SysPermissionDTO.HR_MENU_STRUCTURE)%>) {
              return {
                "Edit": {
                  "label": "რედაქტირება",
                  "icon": "glyphicon glyphicon-pencil",
                  "action": function (obj) {
                    window.location.href = "personal?id=" + $node.id;
                  }
                },
                "View": {
                  "label": "ინფორმაცია",
                  "icon": "glyphicon glyphicon-info-sign",
                  "action": function (obj) {
                    $scope.getPersonalInfo($node.id);
                  }
                }
              };
            } else if ($node.type == "file") {
              return {
                "View": {
                  "label": "ინფორმაცია",
                  "icon": "glyphicon glyphicon-info-sign",
                  "action": function (obj) {
                    $scope.getPersonalInfo($node.id);
                  }
                }
              };
            }
          }
        },
        "plugins": ["types", "search", "contextmenu"]
      });
      $('#jstree').bind("dblclick.jstree", function (event) {
        var node = $(event.target).closest("li");
        var data = node.data($('#jstree'));
        if (data[0].className.indexOf("leaf") != -1) {
          $scope.getPersonalInfo(data[0].id);
        }
      });
    }

    ajaxCall($http, "personal/get-tree-test", {}, successGetTree);


  });

</script>

<div class="col-md-12" ng-controller="structureTreeCtrl">

  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                  aria-hidden="true">&times;</span></button>
          <h4 class="modal-title upper-font" id="exampleModalLabel">დეტალური ინფორმაცია</h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-md-12">
              <div class="form-group col-md-12">
                <label class="control-label">სახელი</label>
                <input type="text" id="firstName" ng-model="personal.firstName" readonly="true"
                       class="form-control input-sm">
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">გვარი</label>
                <input type="text" id="lastName" ng-model="personal.lastName" readonly="true"
                       class="form-control input-sm">
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">ელ.ფოსტა</label><br/>
                <a href="mailto:{{personal.mail}}">{{personal.mail}}</a>
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">სტრუქტურული ერთეული</label>
                <input type="text" id="structureName" ng-model="personal.structureName" disabled="true"
                       class="form-control input-sm">
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">თანამდებობა</label>
                <input type="text" id="positionName" ng-model="personal.positionName" readonly="true"
                       class="form-control input-sm">
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">დაბადების თარიღი</label>
                <input type="text" id="birthDate" ng-model="personal.birthDate" disabled="true"
                       class="form-control input-sm">
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">დახურვა</button>
        </div>
      </div>
    </div>
  </div>

  <div class="panel panel-default">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-3 upper-font">სტრუქტურა</div>
      </div>
    </div>
    <div class="panel-body maxPanel">
      <div class="form-group col-md-3">
        <input type="text" id="who_query" class="form-control input-sm" placeholder="მოძებნეთ თანამშრომელი">
      </div>
      <div class="form-group col-md-9 text-right">
        <c:if test="<%=hasPermissions(request, SysPermissionDTO.HR_MENU_TEST)%>">
          <button class="btn btn-sm" title="თანამშრომლების ხის განახლება" ng-click="syncTree()"><span
                  class="glyphicon glyphicon-refresh"></span></button>
        </c:if>
      </div>
      <div class="col-md-12">
        <div id="jstree"></div>
      </div>
    </div>
    <div class="panel-footer"></div>
  </div>

</div>
<%@include file="footer.jsp" %>