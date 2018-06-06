<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@include file="header.jsp" %>

<link rel="stylesheet" href="resources/angular-ui-tree/angular-ui-tree.min.css"/>
<script src="resources/angular-ui-tree/angular-ui-tree.min.js"></script>

<script>

  angular.module('app', ['ui.tree'])

      .controller('structureTreeCtrl', function ($scope, $http, $filter) {

        $scope.users = [];

        $scope.item = {id: "", name: ""};
        $scope.newItem = {id: "", name: "", parentId: ""};
        $scope.user = {userId: "", parentId: ""};
        $scope.t = function (s) {
          return Lang.get(s);
        };
        $scope.remove = function (scope) {
          scope.removeItem($scope.item.id);
        };
        $scope.editItem = function (isvalid) {
          if (isvalid) {
            $scope._addNewItem({id: $scope.item.id, name: $scope.item.name, parentId: $scope.item.parentId});
          } else {
            alert('invalid');
          }
        };
        $scope.addNewItem = function (isvalid) {
          if (isvalid) {
            $scope.newItem.parentId = $scope.item.id == '' ? 0 : $scope.item.id;
            $scope._addNewItem($scope.newItem);
          }
        };
        $scope._addNewItem = function (data) {
          ajaxCall($http, "employee-structure/add-item", angular.toJson(data), reload);

          function sucessCallback(res) {
            location.reload();
          }
        };
        $scope.editItem = function (item) {
          window.location.href = "personal?id=" + item;
        };
        $scope.removeItem = function (scope) {
          var item = scope.$modelValue;
          ajaxCall($http, "employee-structure/delete-item?itemId=" + item.id, null, sucessCallback);

          function sucessCallback(res) {
            if (res == 'true') {
              scope.remove();
            } else {
              alert('წაშლა შუეძლებელია');
            }
          }
        };
        $scope.getData = function (scope) {
          $scope.loading = true;
          ajaxCall($http, "personal/get-structure-tree", {}, sucessCallback);


          function sucessCallback(res) {
            $scope.data = res;
            $scope.loading = false;

//                        var map = {}, node, roots = [];
//                        for (var i = 0; i < res.length; i += 1) {
//                            node = res[i];
//                            node.nodes = [];
//                            map[node.id] = i;
//                            if (node.parentId != 0) {
//                                res[map[node.parentId]].nodes.push(node);
//                            } else {
//                                roots.push(node);
//                            }
//                            function load(node) {
//                                ajaxCall($http, "employee/get-employees-by-parent", JSON.stringify({parentId: node.id}), sucessCallback, null);
//
//                                function sucessCallback(res) {
//                                    if (res) {
//                                        for (var i = 0; i < res.length; i++) {
//                                            res[i].leaf = true;
//                                            node.nodes.push(res[i]);
//                                        }
//                                    }
//                                }
//                            }
//                            load(node);
//                        }
//                        $scope.data = roots;
          }

        };
        $scope.newSubItem = function (scope) {
          var nodeData = scope.$modelValue;
          $scope.item = nodeData;
        };
        $scope.newAmployee = function (scope) {
          var nodeData = scope.$modelValue;
          $scope.item = nodeData;

          ajaxCall($http, "personal/get-structure-tree", {}, sucessCallback, null);

          function sucessCallback(res) {
            $scope.users = res;
          }
        };
        $scope.getData();

        $scope.addUser = function () {

          ajaxCall($http, "employee/add-employee-to-node", JSON.stringify({
            parentId: $scope.item.id,
            id: $scope.user.userId
          }), sucessCallback, null);

          function sucessCallback(res) {
            location.reload();
          }
        };
      });


</script>
<style>
  .btn {
    margin-right: 8px;
  }

  .angular-ui-tree-handle {
    /* background: #f8faff;*/
    border: 1px solid #dddddd;
    color: #333333;
    padding: 8px;
  }

  .leaf {
    padding: 5px;
  }

  .angular-ui-tree-handle:hover {
    color: #333333;
    background: #f5f5f5;
    border-color: #dddddd;
  }

  .angular-ui-tree-placeholder {
    background: #f0f9ff;
    border: 2px dashed #bed2db;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
  }

  .group-title {
    background-color: #687074 !important;
    color: #FFF !important;
  }
</style>
<div class="col-md-12" ng-controller="structureTreeCtrl">

  <!--<div class="alert alert-info text-center" ng-show="loading" role="dialog"><h4>იტვირთება . . .</h4></div>-->
  <div class="panel panel-default">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-3 upper-font">{{t('structure')}}</div>
      </div>
    </div>
    <div class="panel-body maxPanel">
      <div class="col-md-12">
        <div class="form-group col-md-3">
          <input ng-model="pattern" class="form-control input-sm" placeholder="მოძებნეთ თანამშრომელი">
        </div>
      </div>
      <div class="col-md-8" id='structure'>
        <!-- Nested node template -->
        <script type="text/ng-template" id="nodes_renderer.html">
          <div ui-tree-handle class="tree-node tree-node-content"
               ng-class="{ 'bg-dark' : !node.leaf,'leaf' : node.leaf}">
            <a class="btn btn-xs btn-default" ng-if="node.nodes && node.nodes.length > 0" nodrag
               ng-click="toggle(this)"><span class="glyphicon"
                                             ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>
            {{node.leaf ? node.fullName : node.name}}
            <span class="label label-primary label-standart">{{node.leaf ? node.positionName : ''}}</span>
            <c:if test="<%=hasPermissions(request, SysPermission.HR_MENU_STRUCTURE)%>">
              <a class="pull-right btn btn-xs btn-primary" ng-class="{'hidden':!node.leaf}" nodrag
                 ng-click="editItem(node.id)"><span class="glyphicon glyphicon-pencil"></span></a>
            </c:if>
          </div>
          <ol ui-tree-nodes="" ng-model="node.nodes" ng-class="{hidden: collapsed}">
            <li ng-repeat="node in node.nodes | filter:pattern" ui-tree-node ng-include="'nodes_renderer.html'">
            </li>
          </ol>
        </script>
        <div ui-tree id="tree-root" data-drag-enabled="false">
          <ol ui-tree-nodes ng-model="data">
            <li ng-repeat="node in data| filter:pattern" ui-tree-node ng-include="'nodes_renderer.html'"></li>
          </ol>
        </div>
      </div>
      <div class="col-md-4 hidden">

        <div class="col-md-12 text-center"><h4>{{t('nodeInfo')}}</h4></div>
        <form name="parentForm" ng-submit="editItem(parentForm.$valid);" novalidate>
          <div class="form-group col-md-12"
               ng-class="{ 'has-error' : (parentForm.name.$invalid && !parentForm.name.$pristine) || (parentForm.id.$invalid && !parentForm.name.$pristine)}">
            <label class="control-label">{{t('selectedItem')}}</label>
            <div class="input-group">
              <input type="text" id="item" name="name" ng-model="item.name" class="form-control input-sm"
                     placeholder="{{t('selectedItem')}}" required="true">
              <span class="input-group-btn">
                                <input type="hidden" id="item" name="id" ng-model="item.id"
                                       class="form-control input-sm" required="true">
                                <button type="submit" class="btn btn-sm"><span
                                        class="glyphicon glyphicon-pencil"></span></button>
                            </span>
            </div>
            <p ng-show="parentForm.name.$invalid && !parentForm.name.$pristine" class="help-block">Name is required</p>
            <p ng-show="parentForm.id.$invalid && !parentForm.name.$pristine" class="help-block">Select one item</p>
          </div>
        </form>
        <form name="itemForm" ng-submit="addNewItem(itemForm.$valid);" novalidate>
          <div class="form-group col-md-12"
               ng-class="{ 'has-error' : itemForm.new.$invalid && !itemForm.new.$pristine}">
            <label class="control-label">{{t('newItem')}}</label>
            <div class="input-group">
              <input type="text" name="new" ng-model="newItem.name" class="form-control input-sm"
                     placeholder="{{t('newItemName')}}" required="true">
              <span class="input-group-btn">
                                <button type="submit" class="btn btn-primary btn-sm"><span
                                        class="glyphicon glyphicon-plus"></span></button>
                            </span>
            </div>
            <p ng-show="itemForm.new.$invalid && !itemForm.new.$pristine" class="help-block">Name is required</p>
          </div>
        </form>
        <form>
          <div class="form-group col-md-12">
            <label class="control-label">{{t('users')}}</label>
            <select class="form-control input-sm" ng-model="user.userId">
              <option ng-repeat="user in users" value="{{user.id}}">{{user.fullName}} {{user.positionName}}</option>
            </select>
          </div>
          <div class="form-group col-md-12">
            <input type="button" ng-click="addUser()" value="{{t('addUser')}}" class="btn btn-sm btn-primary right"/>
          </div>
        </form>
      </div>
    </div>
    <div class="panel-footer"></div>
  </div>
</div>
<%@include file="footer.jsp" %>