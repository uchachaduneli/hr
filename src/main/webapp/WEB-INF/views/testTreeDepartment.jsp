<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="resources/css/style.min.css"/>
<script src="resources/js/jstree.min.js"></script>
<script>
  $(function () {
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
    var to2 = false;
    $('#whos_query').keyup(function () {
      if (to2) {
        clearTimeout(to2);
      }
      to2 = setTimeout(function () {
        var v2 = $('#whos_query').val();
        $('#jstree2').jstree(true).search(v2);
      }, 250);
    });
  });
</script>
<script>
  var app = angular.module("app", []);
  app.controller("TreeController", function ($scope, $http, $filter, $location) {
    var absUrl = $location.absUrl();
    $scope.currentTestId = absUrl.split("?")[1].split("=")[1];

    $scope.personals = [];
    $scope.departments = [];
    $scope.tmpCandidates = [];
    $scope.tmpVoters = [];
    $scope.oldVoters = [];
    $scope.candidates = [];
    $scope.voters = [];
    $scope.votePair = [];
    $scope.currentTransactionId = 1;
    $scope.selectedVoterId = 0;
    $scope.selectedDepartmentId = 0;
    $scope.edit = false;

    $scope.t = function (s) {
      return Lang.get(s);
    };
    $('#jstree').on("changed.jstree", function (e, data) {
      $scope.tmpVoters = [];
      angular.forEach(data.selected, function (val, key) {
        if ($scope.tmpCandidates.indexOf(parseInt(val)) == -1) {
          $scope.tmpVoters.push(parseInt(val));
        }
        else {
          $('#jstree').jstree(true).deselect_node(parseInt(val));
        }
      });
    });
    $('#jstree2').on("changed.jstree", function (e, data) {
      $scope.tmpCandidates = [];
      angular.forEach(data.selected, function (val, key) {
        if ($scope.tmpVoters.indexOf(parseInt(val)) == -1) {
          $scope.tmpCandidates.push(parseInt(val));
        }
        else {
          $('#jstree2').jstree(true).deselect_node(parseInt(val));
        }
      });

    });


    function successGetPersonal(res) {
      $('#jstree').jstree({
        "core": {
          'data': res
        }, "types": {
          "file": {
            "icon": "glyphicon glyphicon-file",
            "valid_children": []
          }
        },
        "plugins": ["wholerow", "checkbox", "search", "types"]
      });

      function successGetAllPersonal(res) {
        $scope.personals = res;

        function successGetVotePair(res) {
          $scope.votePair = res;
          angular.forEach($scope.votePair, function (v, k) {
            var selectedVoter = $filter('filter')($scope.voters, {id: v.voterId}, true);
            if (selectedVoter.length == 0) {
              var a = {};
              a.id = v.voterId;
              var selected = $filter('filter')($scope.personals, {id: v.voterId}, true);
              a.text = selected[0].firstName + ' ' + selected[0].lastName;
              $scope.voters.push(a);
            }
          });
        }

        ajaxCall($http, "vote/get-vote-pair?testId=" + $scope.currentTestId, {}, successGetVotePair);
      }

      ajaxCall($http, "personal/get-all-personal", {}, successGetAllPersonal);
    }

    ajaxCall($http, "personal/get-tree-test", {}, successGetPersonal);

    function successGetDepartmentTree(res) {
      $('#jstree2').jstree({
        "core": {
          'data': res
        },
        "plugins": ["wholerow", "checkbox", "search"]
      });

      function successGetAllDepartment(res) {
        $scope.departments = res;
      }

      ajaxCall($http, "personal/get-all-structure", {}, successGetAllDepartment);
    }

    ajaxCall($http, "personal/get-department-tree", {}, successGetDepartmentTree);

    $scope.addCandidates = function () {
      if ($scope.edit) {
        angular.forEach($scope.oldVoters, function (v, k) {
          $scope.voters.splice($scope.voters.indexOf(v), 1);
          var votePairCopy = $scope.votePair;
          angular.forEach(votePairCopy, function (val, key) {
            if (val.voterId == v) {
              $scope.votePair.splice($scope.votePair.indexOf(val), 1);
            }
          });
        });
      }
      angular.forEach($scope.tmpVoters, function (v, k) {
        var selectedVoter = $filter('filter')($scope.voters, {id: v}, true);
        if (selectedVoter.length == 0) {
          var selectedPersonal = $filter('filter')($scope.personals, {id: v}, true);
          if (selectedPersonal.length != 0) {
            var a = {};
            a.id = v;
            a.text = selectedPersonal[0].firstName + ' ' + selectedPersonal[0].lastName;
            $scope.voters.push(a);
            angular.forEach($scope.tmpCandidates, function (value, key) {
              var tmp = {};
              tmp.testId = $scope.currentTestId;
              tmp.voterId = v;
              tmp.departmentId = value;
              tmp.transactionId = $scope.currentTransactionId;
              $scope.votePair.push(tmp);
            });
          }
        }
      });
      $scope.currentTransactionId = $scope.currentTransactionId + 1;
      $scope.candidates = [];
      $('#jstree').jstree(true).deselect_all();
      $('#jstree2').jstree(true).deselect_all();
      $scope.tmpCandidates = [];
      $scope.tmpVoters = [];
    };

    $scope.editCandidate = function (voterId) {
      if (voterId == 0) {
        alert('აირჩიეთ შემფასებელი');
      } else {
        $scope.tmpCandidates = [];
        $scope.tmpVoters = [];
        $scope.oldVoters = [];
        $('#jstree').jstree(true).deselect_all();
        $('#jstree2').jstree(true).deselect_all();

        var voter = $filter('filter')($scope.votePair, {voterId: parseInt(voterId)}, true);
        //var selected = $filter('filter')($scope.votePair, {transactionId: voter[0].transactionId});
        angular.forEach(voter, function (v, k) {
          if ($scope.oldVoters.indexOf(v.voterId) < 0) {
            $scope.oldVoters.push(v.voterId);
          }
          $('#jstree').jstree(true).select_node(v.voterId);
          $('#jstree2').jstree(true).select_node(v.departmentId);
        });

        $scope.edit = true;
      }
    };

    $scope.changeVoter = function (voterId) {
      $scope.candidates = [];
      var selected = $filter('filter')($scope.votePair, {voterId: parseInt(voterId)}, true);
      angular.forEach(selected, function (v, k) {
        var s = {};
        s.id = v.departmentId;
        var selected = $filter('filter')($scope.departments, {id: v.departmentId}, true);
        s.text = selected[0].name;
        $scope.candidates.push(s);

      });
      console.log($scope.candidates);

    };

    $scope.saveCandidates = function () {
      ajaxCall($http, "vote/save-voter-candidate", angular.toJson($scope.votePair), reload);
    };
    $scope.unSelect = function () {
      $scope.edit = false;
      $('#jstree').jstree(true).deselect_all();
      $('#jstree2').jstree(true).deselect_all();
      $scope.tmpCandidates = [];
      $scope.tmpVoters = [];
    };
    $scope.cancel = function () {
      window.location = "test?type=test";
    };
  });
</script>


<div class="col-md-12" ng-controller="TreeController">
  <div class="row">
    <div class="col-md-12">
      <div class="panel panel-default">
        <div class="panel-heading">
          <div class="row">
            <div class="col-md-9">
            </div>
          </div>
        </div>
        <br/>

        <div class="panel-body maxPanel">
          <div class="col-md-6">
            <div class="form-group col-md-6">
              <input type="text" id="who_query" class="form-control input-sm" placeholder="ვინ აფასებს">
            </div>
            <div class="col-md-12">
              <div id="jstree"></div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group col-md-6">
              <input type="text" id="whos_query" class="form-control input-sm" placeholder="ვის აფასებს">
            </div>
            <div class="col-md-12">
              <div id="jstree2"></div>
            </div>
          </div>
          <div class="col-md-12">
            <div class="form-group col-md-3 col-md-offset-9 text-right">
              <br/>
              <button class="btn btn-sm" title="დამატება" ng-click="addCandidates();">
                <span class="glyphicon glyphicon-plus"></span>
              </button>
              <button class="btn btn-sm btn-danger" title="გასუფთავება" ng-click="unSelect();">
                <span class="glyphicon glyphicon-remove"></span>
              </button>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group col-md-10">
              <label class="control-label">შემფასებლები: </label>
              <select class="form-control input-sm" ng-model="selectedVoterId" ng-change="changeVoter(selectedVoterId)">
                <option ng-repeat="t in voters" value="{{t.id}}">{{t.text}}</option>
              </select>
            </div>
            <div class="col-md-2">
              <label class="control-label"> </label>
              <button class="btn btn-xs" style="margin-top: 25px;" title="რედაქტირება"
                      ng-click="editCandidate(selectedVoterId);">
                <span class="glyphicon glyphicon-pencil"></span>
              </button>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group col-md-10">
              <label class="control-label">შესაფასებლები: </label>
              <select class="form-control input-sm" ng-model="selectedDepartmentId">
                <option ng-repeat="t in candidates" value="{{t.id}}">{{t.text}}</option>
              </select>
            </div>
            <div class="form-group col-md-4 col-md-offset-8 text-right">
              <input type="button" class="btn btn-primary btn-sm" value="{{t('save')}}" ng-click="saveCandidates();">
              <input type="button" class="btn btn-default btn-sm" value="{{t('cancel')}}" ng-click="cancel();">
            </div>
          </div>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>
  </div>
</div>
<%@include file="footer.jsp" %>



