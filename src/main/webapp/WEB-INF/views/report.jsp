<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
  var app = angular.module("app", []);
  app.controller("manageReport", function ($scope, $http, $location, $filter) {
    var absUrl = $location.absUrl();
    $scope.type = absUrl.split("?")[1].split("=")[1];
    $scope.personals = [];
    $scope.tests = [];
    $scope.departments = [];
    $scope.searchVote = {};
    $scope.voteByPersonal = [];
    $scope.testScoreByPersonal = [];
    $scope.selectedTest = {typeId: 0};

    function successTest(res) {
      $scope.tests = res;
      console.log(res);
    }

    ajaxCall($http, "vote/get-all-test", {}, successTest);

    function successGetAllPersonal(res) {
      $scope.personals = res;
    }

    ajaxCall($http, "personal/get-all-personal", {}, successGetAllPersonal);

    function successGetQuestionTypes(res) {
      $scope.questionTypes = res;
    }

    ajaxCall($http, "misc/get-question-types", {}, successGetQuestionTypes);

    function successGetAllDepartment(res) {
      $scope.departments = res;
    }

    ajaxCall($http, "personal/get-all-structure", {}, successGetAllDepartment);
    $scope.search = function () {
      if ($scope.searchVote.testId) {
        function successSearchByPersonal(res) {
          $scope.voteByPersonal = res;
        }

        ajaxCall($http, "report/get-vote-by-personal", angular.toJson($scope.searchVote), successSearchByPersonal);
      } else {
        alert('გთხოვთ აირჩიოთ ტესტი');
      }
    };
    $scope.searchTestScore = function () {
      if ($scope.searchVote.testId) {
        if ($scope.selectedTest.typeId == 3) {
          $scope.searchVote.orderByClause = "real_score";
        }

        function successSearchTestScore(res) {
          $scope.searchVote.orderByClause = "";
          $scope.testScoreByPersonal = res;
        }

        ajaxCall($http, "report/get-test-score-by-personal", angular.toJson($scope.searchVote), successSearchTestScore);
      } else {
        alert('გთხოვთ აირჩიოთ ტესტი');
      }
    };
    $scope.searchSelfScore = function () {
      if ($scope.searchSelf.testId) {
        function successSearchSelfScore(res) {
          $scope.selfScores = res;
        }

        ajaxCall($http, "report/get-self-score", angular.toJson($scope.searchSelf), successSearchSelfScore);
      } else {
        alert('გთხოვთ აირჩიოთ ტესტი');
      }
    };
    $scope.clear = function () {
      $scope.searchVote = {};
    };
    $scope.clearSelf = function () {
      $scope.searchSelf = {};
    };
    $scope.changeTest = function (testId) {
      $scope.testScoreByPersonal = [];
      $scope.selfScores = [];
      var selected = $filter('filter')($scope.tests, {id: parseInt(testId)}, true);
      $scope.selectedTest = selected[0];

      function successGetPersonalByTest(res) {
        $scope.personals = res;
        console.log(res);
      }

      ajaxCall($http, "personal/get-personal-by-test?testId=" + testId, {}, successGetPersonalByTest);
    };
    $scope.showType = function (typeId) {
      return $scope.selectedTest.typeId == typeId;
    };
  });</script>
<script>
  function print(contentId) {
    var printContents = document.getElementById(contentId).innerHTML;
    var popupWin = window.open('', '_blank', 'width=1200,height=700');
    popupWin.document.open();
    popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="/hr/resources/css/bootstrap.min.css" /><link rel="stylesheet" type="text/css" </head><body onload="window.print()">' + printContents + '</html>');
    popupWin.document.close();
  }

  $(document).ajaxSend(function (event, request, settings) {
    $('.modal').modal('show');
  });

  $(document).ajaxComplete(function (event, request, settings) {
    $('.modal').modal('hide');
  });
</script>

<div class="col-md-12" ng-controller="manageReport">

  <div class="modal fade bd-example-modal-lg" data-backdrop="static" data-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-sm">
      <div class="modal-content" style="width: 48px">
        <span class="fa fa-spinner fa-spin fa-3x"></span>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="panel panel-default">
        <div class="panel-heading">
          <div class="row">
            <div class="col-md-3 upper-font"><b>{{t('report')}}</b></div>
            <div class="col-md-9">
            </div>
          </div>
        </div>
        <br/>
        <div class="col-md-12">
          <ul class="nav nav-tabs upper-font">
            <li id="top" ng-class="{
                                'active'
                                : type == 'scores'}"><a href="report?type=scores">ქულების მიხედვით</a></li>
            <li id="self" ng-class="{
                                'active' : type == 'self'}"><a href="report?type=self">თვითშეფასება</a></li>
            <li id="answer" ng-class="{
                                'active' : type == 'personals'}"><a href="report?type=personals">პასუხების მიხედვით</a>
            </li>
            <!--                        <li id="answer" ng-class="{
                                             'active' : type == 'myvoter'}"><a href="report?type=myvoter">ჩემი შეფასებები</a></li>-->
          </ul>
        </div>
        <div class="panel-body maxPanel">
          <div ng-show="{{type == 'personals'}}">
            <div class="col-md-12">
              <div class="row">
                <br/>
                <div class="col-md-10">
                  <div class="form-group col-md-3">
                    <label class="control-label">შეფასება</label>
                    <select class="form-control input-sm" ng-model="searchVote.testId">
                      <option ng-repeat="t in tests" value="{{t.id}}">{{t.name}}</option>
                    </select>
                  </div>
                  <div class="form-group col-md-3">
                    <label class="control-label">თანამშრომელი</label>
                    <select class="form-control input-sm" ng-model="searchVote.candidateId">
                      <option ng-repeat="p in personals" value="{{p.id}}">{{p.firstName}} {{p.lastName}}</option>
                    </select>
                  </div>
                  <div class="form-group col-md-3">
                    <label class="control-label">დეპარტამენტი</label>
                    <select class="form-control input-sm" ng-model="searchVote.departmentId">
                      <option ng-repeat="d in departments" value="{{d.id}}">{{d.name}}</option>
                    </select>
                  </div>
                </div>
                <div class="col-md-2">
                  <div style="margin-top: 20px;">
                    <button id="searchStore" class="btn btn-sm btn-primary" ng-click="search();">
                      <span class="glyphicon glyphicon-search"></span>
                    </button>
                    <button id="clearStore" class="btn btn-default btn-sm" ng-click="clear();">
                      <span class="glyphicon glyphicon-refresh"></span>
                    </button>
                    <button id="printTestResult" class="btn btn-sm" onclick="print('testResult');">
                      <span class="glyphicon glyphicon-print"></span>
                    </button>
                  </div>

                </div>
              </div>
            </div>
            <div class="col-md-12" id="testResult">
              <table class="table table-striped table-hover">
                <tr>
                  <!--<th >Id</th>-->
                  <th>კითხვა</th>
                  <th>შეფასებული</th>
                  <th>პასუხი</th>
                  <th>ქულათა ჯამი</th>
                  <th>პას. რაოდენობა</th>
                </tr>
                <tr ng-repeat="v in voteByPersonal">
                  <td>{{v.title}}</td>
                  <td>{{v.candidateFirstName}} {{v.candidateLastName}} {{v.departmentName}}</td>
                  <td>{{v.text}}</td>
                  <td>{{v.sumScore}}</td>
                  <td>{{v.countAnswer}}</td>
                </tr>
              </table>
            </div>
          </div>

          <div ng-show="{{type == 'scores'}}">
            <div class="col-md-12">
              <div class="row">
                <br/>
                <div class="col-md-9">
                  <div class="form-group col-md-3">
                    <label class="control-label">შეფასება</label>
                    <select class="form-control input-sm" ng-model="searchVote.testId"
                            ng-change="changeTest(searchVote.testId)">
                      <option ng-repeat="t in tests" value="{{t.id}}">{{t.name}}</option>
                    </select>
                  </div>
                  <div class="form-group col-md-3">
                    <label class="control-label">ტიპი</label>
                    <select class="form-control input-sm" ng-model="searchVote.questionTypeId">
                      <option ng-repeat="s in questionTypes" value="{{s.id}}">{{s.name}}</option>
                    </select>
                  </div>
                  <div class="form-group col-md-3">
                    <label class="control-label">თანამშრომელი</label>
                    <select class="form-control input-sm" ng-model="searchVote.candidateId">
                      <option ng-repeat="p in personals" value="{{p.id}}">{{p.firstName}} {{p.lastName}}</option>
                    </select>
                  </div>
                  <!--                                    <div class="form-group col-md-3" ng-class="{'hidden':searchVote.testId==0}">
                                                          <label class="control-label">დეპარტამენტი</label>
                                                          <select class="form-control input-sm" ng-model="searchVote.departmentId" >
                                                              <option ng-repeat="d in departments" value="{{d.id}}">{{d.name}}</option>
                                                          </select>
                                                      </div>-->
                  <div class="form-group col-md-3">
                    <label class="control-label">დეპარტამენტი</label>
                    <select class="form-control input-sm" ng-model="searchVote.candidateStructureId">
                      <option ng-repeat="d in departments" value="{{d.id}}">{{d.name}}</option>
                    </select>
                  </div>
                </div>
                <div class="col-md-3">
                  <div style="margin-top: 20px;">
                    <button id="searchStore" class="btn btn-sm btn-primary" ng-click="searchTestScore();">
                      <span class="glyphicon glyphicon-search"></span>
                    </button>
                    <button id="clearStore" class="btn btn-default btn-sm" ng-click="clear();">
                      <span class="glyphicon glyphicon-refresh"></span>
                    </button>
                    <button id="printScore" class="btn btn-sm" onclick="print('scoreResult');">
                      <span class="glyphicon glyphicon-print"></span>
                    </button>
                  </div>

                </div>
              </div>
            </div>
            <div class="col-md-12" id="scoreResult">
              <div class="hidden-visibility text-center">{{selectedTest.name}}</div>
              <div ng-class="{
                                    'hidden':!showType(4)}">
                <table class="table table-striped table-hover">
                  <tr>
                    <th>ID</th>
                    <th>შეფასებული</th>
                    <th>ტიპი</th>
                    <th>შეფასების რაოდენობა</th>
                  </tr>
                  <tr ng-repeat="t in testScoreByPersonal">
                    <td>{{$index + 1}}</td>
                    <td>{{t.candidateFirstName}} {{t.candidateLastName}} {{t.departmentName}}</td>
                    <td>{{t.questionTypeName}}</td>
                    <td>{{t.realScore}}</td>
                  </tr>
                </table>
              </div>
              <div ng-class="{
                                    'hidden':!showType(3)}">
                <table class="table table-striped table-hover">
                  <tr>
                    <th>ID</th>
                    <th>შეფასებული</th>
                    <th>ტიპი</th>
                    <th>რეიტინგი</th>
                  </tr>
                  <tr ng-repeat="t in testScoreByPersonal">
                    <td>{{$index + 1}}</td>
                    <td>{{t.candidateFirstName}} {{t.candidateLastName}} {{t.departmentName}}</td>
                    <td>{{t.questionTypeName}}</td>
                    <td>{{t.realScore}}</td>
                  </tr>
                </table>
              </div>
              <div ng-class="{
                                    'hidden'
                                    :!(showType(1))}">
                <table class="table table-striped table-hover">
                  <tr>
                    <th>ID</th>
                    <th>შეფასებული</th>
                    <th>ტიპი</th>
                    <th>მაქსიმალური ქულა</th>
                    <th>მიმდინარე ქულა</th>
                    <th>შემფასებელი სულ</th>
                    <th>არსებული შემფასებლები</th>
                    <th>საშუალო</th>
                    <th>%</th>
                    <th style="min-width:140px;">სტატუსი</th>
                  </tr>
                  <tr ng-repeat="t in testScoreByPersonal">
                    <td>{{$index + 1}}</td>
                    <td>{{t.candidateFirstName}} {{t.candidateLastName}} {{t.departmentName}}</td>
                    <td>{{t.questionTypeName}}</td>
                    <td>{{t.maxScore}}</td>
                    <td>
                                            <span ng-class="
                                                    {
                                                    'label label-danger':((t.realScore / t.maxScore) * 100) <= 50,
                                                            'label label-success':((t.realScore / t.maxScore) * 100) >= 90}">
                                                {{t.realScore}}
                                            </span>
                    </td>
                    <td>
                      {{t.voterFullCount}}
                    </td>
                    <td>
                      {{t.voterCurrentCount}}
                    </td>
                    <td>
                      {{ (t.realScore / t.voterCurrentCount) || 0 | number:2}}
                    </td>
                    <td>
                      {{(t.realScore / t.maxScore) * 100 | number:2}}
                    </td>
                    <td>
                      {{(((t.realScore / t.maxScore * 100) === 0) || ((t.realScore / t.maxScore * 100) <= 44)) ?
                      'დაბალი'
                      : (((t.realScore / t.maxScore * 100) > 44) && ((t.realScore / t.maxScore * 100) <= 69)) ?
                      'საშუალო'
                      : (((t.realScore / t.maxScore * 100) > 69) && ((t.realScore / t.maxScore * 100) <= 89)) ? 'კარგი'
                      : ((t.realScore / t.maxScore * 100) >= 90) ? 'აჭარბებს მოლოდინს'
                      : ''}}
                    </td>
                  </tr>
                </table>
              </div>
            </div>
          </div>

          <div ng-show="{{type == 'self'}}">
            <div class="col-md-12">
              <div class="row">
                <br/>
                <div class="col-md-9">
                  <div class="form-group col-md-2">
                    <label class="control-label">შეფასება</label>
                    <select class="form-control input-sm" ng-model="searchSelf.testId"
                            ng-change="changeTest(searchSelf.testId)">
                      <option ng-repeat="t in tests" value="{{t.id}}">{{t.name}}</option>
                    </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label class="control-label">ტიპი</label>
                    <select class="form-control input-sm" ng-model="searchSelf.questionTypeId">
                      <option ng-repeat="s in questionTypes" value="{{s.id}}">{{s.name}}</option>
                    </select>
                  </div>
                  <div class="form-group col-md-3">
                    <label class="control-label">თანამშრომელი</label>
                    <select class="form-control input-sm" ng-model="searchSelf.candidateId">
                      <option ng-repeat="p in personals" value="{{p.id}}">{{p.firstName}} {{p.lastName}}</option>
                    </select>
                  </div>
                  <!--                                    <div class="form-group col-md-3">
                                                          <label class="control-label">დეპარტამენტი</label>
                                                          <select class="form-control input-sm" ng-model="searchSelf.departmentId" >
                                                              <option ng-repeat="d in departments" value="{{d.id}}">{{d.name}}</option>
                                                          </select>
                                                      </div>-->
                  <div class="form-group col-md-3">
                    <label class="control-label">დეპარტამენტი</label>
                    <select class="form-control input-sm" ng-model="searchSelf.candidateStructureId">
                      <option ng-repeat="d in departments" value="{{d.id}}">{{d.name}}</option>
                    </select>
                  </div>
                </div>
                <div class="col-md-3">
                  <div style="margin-top: 20px;">
                    <button id="searchStore" class="btn btn-sm btn-primary" ng-click="searchSelfScore();">
                      <span class="glyphicon glyphicon-search"></span>
                    </button>
                    <button id="clearStore" class="btn btn-default btn-sm" ng-click="clearSelf();">
                      <span class="glyphicon glyphicon-refresh"></span>
                    </button>
                    <button id="printStore" class="btn btn-sm" onclick="print('selfResult');">
                      <span class="glyphicon glyphicon-print"></span>
                    </button>
                  </div>

                </div>
              </div>
            </div>
            <div class="col-md-12" id="selfResult" ng-class="{
                                    'hidden'
                                    :!showType(1)}">
              <div class="hidden-visibility text-center">{{selectedTest.name}} - თვითშეფასება</div>
              <table class="table table-striped table-hover">
                <tr>
                  <th>ID</th>
                  <th>შეფასებული</th>
                  <th>ტიპი</th>
                  <th>მაქსიმალური ქულა</th>
                  <th>თვითშეფასება</th>
                  <th>%</th>
                  <th style="min-width:140px;">სტატუსი</th>
                </tr>
                <tr ng-repeat="t in selfScores">
                  <td>{{$index + 1}}</td>
                  <td>{{t.candidateFirstName}} {{t.candidateLastName}} {{t.departmentName}}</td>
                  <td>{{t.questionTypeName}}</td>
                  <td>{{t.maxScore}}</td>
                  <td>
                                        <span ng-class="
                                                {
                                                'label label-danger'
                                                :(((t.selfScore / t.maxScore) * 100) <= 50 && t.questionTypeId != 2),
                                                        'label label-success'
                                                        :(((t.selfScore / t.maxScore) * 100) >= 90 && t.questionTypeId != 2)}">
                                            {{t.selfScore}}
                                        </span>
                  </td>
                  <td><span ng-class="{
                                            'hidden'
                                                    :t.questionTypeId == 2}">
                                            {{(t.selfScore / t.maxScore) * 100 | number:2}}
                                        </span>
                  </td>
                  <td>
                                        <span ng-class="{
                                                'hidden'
                                                        :t.questionTypeId == 2}">
                                            {{(((t.selfScore / t.maxScore * 100) === 0) || ((t.selfScore / t.maxScore * 100) <= 44)) ? 'დაბალი'
                                                        : (((t.selfScore / t.maxScore * 100) > 44) && ((t.selfScore / t.maxScore * 100) <= 69)) ? 'საშუალო'
                                                        : (((t.selfScore / t.maxScore * 100) > 69) && ((t.selfScore / t.maxScore * 100) <= 89)) ? 'კარგი'
                                                        : ((t.selfScore / t.maxScore * 100) >= 90) ? 'აჭარბებს მოლოდინს'
                                                        : ''}}
                                        </span>
                  </td>
                </tr>
              </table>
            </div>
          </div>


          <div ng-show="{{type == 'myvoter'}}">
            <div class="col-md-12">
              <div class="row">
                <br/>
                <div class="col-md-9">
                  <div class="form-group col-md-2">
                    <label class="control-label">შეფასება</label>
                    <select class="form-control input-sm" ng-model="searchVoter.testId"
                            ng-change="changeMyVoterTest(searchVoter.testId)">
                      <option ng-repeat="t in tests" value="{{t.id}}">{{t.name}}</option>
                    </select>
                  </div>
                  <div class="form-group col-md-3">
                    <label class="control-label">თანამშრომელი</label>
                    <select class="form-control input-sm" ng-model="searchVoter.candidateId">
                      <option ng-repeat="p in myPersonals" value="{{p.id}}">{{p.firstName}} {{p.lastName}}</option>
                    </select>
                  </div>
                  <!--                                    <div class="form-group col-md-3">
                                                          <label class="control-label">დეპარტამენტი</label>
                                                          <select class="form-control input-sm" ng-model="searchSelf.departmentId" >
                                                              <option ng-repeat="d in departments" value="{{d.id}}">{{d.name}}</option>
                                                          </select>
                                                      </div>-->
                </div>
                <div class="col-md-3">
                  <div style="margin-top: 20px;">
                    <button id="searchStore" class="btn btn-sm btn-primary" ng-click="searchMyVoter();">
                      <span class="glyphicon glyphicon-search"></span>
                    </button>
                    <button id="clearStore" class="btn btn-default btn-sm" ng-click="clearMyVoter();">
                      <span class="glyphicon glyphicon-refresh"></span>
                    </button>
                  </div>

                </div>
              </div>
            </div>
            <div class="col-md-12" id="MyScoreResult">
              <table class="table table-striped table-hover">
                <tr>
                  <th>ID</th>
                  <th>შეფასებული</th>
                  <th>ტიპი</th>
                  <th>მაქსიმალური ქულა</th>
                  <th>ჩემი შეფასება</th>
                  <th>%</th>
                  <th style="min-width:140px;">სტატუსი</th>
                </tr>
                <tr ng-repeat="t in selfScores">
                  <td>{{$index + 1}}</td>
                  <td>{{t.candidateFirstName}} {{t.candidateLastName}} {{t.departmentName}}</td>
                  <td>{{t.questionTypeName}}</td>
                  <td>{{t.maxScore}}</td>
                  <td>
                                        <span ng-class="
                                                {
                                                'label label-danger':(((t.selfScore / t.maxScore) * 100) <= 50 && t.questionTypeId != 2),
                                                        'label label-success'
                                                        :(((t.selfScore / t.maxScore) * 100) >= 90 && t.questionTypeId != 2)}">
                                            {{t.selfScore}}
                                        </span>
                  </td>
                  <td><span ng-class="{
                                            'hidden'
                                                    :t.questionTypeId == 2}">
                                            {{(t.selfScore / t.maxScore) * 100 | number:2}}
                                        </span>
                  </td>
                  <td>
                                        <span ng-class="{
                                                'hidden'
                                                        :t.questionTypeId == 2}">
                                            {{(((t.selfScore / t.maxScore * 100) === 0) || ((t.selfScore / t.maxScore * 100) <= 44)) ? 'დაბალი'
                                                        : (((t.selfScore / t.maxScore * 100) > 44) && ((t.selfScore / t.maxScore * 100) <= 69)) ? 'საშუალო'
                                                        : (((t.selfScore / t.maxScore * 100) > 69) && ((t.selfScore / t.maxScore * 100) <= 89)) ? 'კარგი'
                                                        : ((t.selfScore / t.maxScore * 100) > 90) ? 'აჭარბებს მოლოდინს'
                                                        : ''}}
                                        </span>
                  </td>
                </tr>
              </table>
            </div>
          </div>


        </div>
        <div class="panel-footer"></div>
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp" %>



