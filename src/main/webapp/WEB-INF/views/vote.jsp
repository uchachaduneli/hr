<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
  var app = angular.module("app", []);
  app.controller("manageVote", function ($scope, $http, $filter) {
    $scope.candidates = [];
    $scope.votes = [];
    $scope.tmpList = [];
    $scope.tmpListText = [];
    $scope.tmpValue = [];
    $scope.questions = [];
    $scope.personals = [];
    $scope.currentTestId = 0;
    $scope.testDisabled = true;
    $scope.currentTestTypeId = 0;
    $scope.answers = {};
    $scope.personal = {};


    $scope.t = function (s) {
      return Lang.get(s);
    };

    function successTest(res) {
      $scope.tests = res;
    }

    ajaxCall($http, "vote/get-active-test-by-user", {}, successTest);

    function successGetPersonal(res) {
      $scope.personal = res;
      console.log(res);

    }

    ajaxCall($http, "personal/get-personal-info", {}, successGetPersonal);


    $scope.changeTest = function (testId) {
      if ($scope.personal.positionId < 4) {
        function successGetVoteAnswer(res) {
          $scope.answers = res;
          console.log(res);
        }

        ajaxCall($http, "vote/get-vote-answer?testId=" + testId, {}, successGetVoteAnswer);
      }
      $scope.testDisabled = $scope.tests.length < 1;
      $scope.currentTestId = testId;
      var selected = $filter('filter')($scope.tests, {id: parseInt(testId)}, true);
      $scope.description = selected[0].description;
      $scope.currentTestTypeId = selected[0].typeId;
      if ($scope.currentTestTypeId != 4) {
        function successGetCandidate(res) {
          $scope.candidates = res;

          function successGetQuestion(res) {
            angular.forEach($scope.candidates, function (v, k) {
              v.questions = [];
              angular.forEach(res, function (val, key) {
                if (
                    (val.questionTypeId != 2 && ((val.positionId == 1) ||
                            (val.positionId > 2 && v.positionId == val.positionId) ||
                            (val.positionId == 2 && v.positionId > 6) ||
                            ($scope.personal.id == v.id))
                    ) || (val.questionTypeId == 2 && $scope.personal.id == v.id)
                ) {
                  v.questions.push(val);
                  $scope.tmpList[v.id + '' + val.id] = 0;
                  $scope.tmpListText[v.id + '' + val.id] = "";
                }
              });
              console.log($scope.tmpListText);
            });
          }

          ajaxCall($http, "vote/get-question-by-test?testId=" + testId, {}, successGetQuestion);
        }

        ajaxCall($http, "vote/get-vote-candidate?testId=" + testId, {}, successGetCandidate);
      } else {
        function successQuestion(res) {
          $scope.questions = res;
          console.log(res);
        }

        ajaxCall($http, "vote/get-question-by-test?testId=" + testId, {}, successQuestion);

        function successGetPersonal(res) {
          $scope.personals = res;
          console.log(res);
        }

        ajaxCall($http, "personal/get-non-management-personal", {}, successGetPersonal);
      }


    };

    $scope.saveVote = function () {
      if ($scope.currentTestTypeId != 4) {
        console.log($scope.tmpListText);
        console.log($scope.tmpList);
        angular.forEach($scope.candidates, function (v, k) {
          angular.forEach(v.questions, function (val, key) {
            var vote = {};
            vote.testId = $scope.currentTestId;
            if ($scope.currentTestTypeId == 2) {
              vote.departmentId = v.id;
            } else {
              vote.candidateId = v.id;
            }
            vote.questionId = val.id;
            vote.answerId = $scope.tmpList[v.id + '' + val.id];
            if ($scope.currentTestTypeId == 5) {
              vote.answerText = $scope.tmpListText[v.id + '' + val.id];
            }
            $scope.votes.push(vote);
          });
        });
      } else if ($scope.currentTestTypeId == 4) {
        angular.forEach($scope.questions, function (v, k) {
          angular.forEach(v.answers, function (val, key) {
            var vote = {};
            vote.testId = $scope.currentTestId;
            vote.questionId = v.id;
            vote.answerId = val.id;
            vote.candidateId = $scope.tmpList[v.id + '' + val.id];
            $scope.votes.push(vote);
          });
        });
      }
      console.log($scope.votes);
      ajaxCall($http, "vote/save-user-vote", angular.toJson($scope.votes), reload);
    };

    $scope.editTest = function (id) {
      $('#testList').addClass('hidden');
      $('#testPanel').removeClass('hidden');
      $scope.test = id;
    };
    $scope.testPanelHide = function () {
      $scope.test = null;
      $('#testPanel').addClass('hidden');
      $('#testList').removeClass('hidden');
    };

    $scope.testQuestionPanelHide = function () {
      $('#questionList').addClass('hidden');
      $('#testList').removeClass('hidden');
    };

    $scope.removeTest = function (id) {
      ajaxCall($http, "misc/remove-test?testId=" + id, {}, reload);
    };
    $scope.filterAnswer = function (pid, qid, id) {
      var count = 0;
      angular.forEach($scope.tmpValue, function (v, k) {
        if (v.id == id) {
          count++;
          return;
        }
      });
      if (count == 0) {
        angular.forEach($scope.tmpValue, function (v, k) {
          if (v.pid == pid && v.qid == qid) {
            v.id = id;
            count++;
            return;
          }
        });
        if (count == 0) {
          var item = {'pid': pid, 'qid': qid, 'id': id};
          $scope.tmpValue.push(item);
        }
      } else {
        $scope.tmpList = [];
        $scope.tmpValue = [];
//                $scope.tmpList.splice(pid + '' + qid, 1);
//                var selected = $filter('filter')($scope.tmpValue, {'pid': pid, 'qid': qid}, true);
//                if ($scope.tmpValue.indexOf(selected[0]) != -1) {
//                    $scope.tmpValue.splice($scope.tmpValue.indexOf(selected[0]), 1);
//                }
        alert("ამ ტესტში არ შეიძლება ერთნაირი პასუხების არჩევა გთხოვთ შეცვალოთ");
      }
    };

    $scope.getAnswerByPersonId = function (personalId, questionId) {
      console.log($scope.answers);
      var selected = $filter('filter')($scope.answers, {
        'voterId': personalId,
        'questionId': questionId,
        'candidateId': personalId
      }, true);
      console.log(selected);
      return selected[0].answerText;
    };


  });

</script>

<div class="col-md-12" ng-controller="manageVote">
  <div class="row">
    <div class="col-md-12">
      <div class="panel panel-default">
        <div class="panel-heading">
          <div class="row">
            <div class="col-md-3 upper-font"><b>{{t('vote')}}</b></div>
            <div class="col-md-9">
            </div>
          </div>
        </div>
        <br/>
        <div class="panel-body maxPanel">
          <div class="col-md-9">
            <br/>
            <div class="form-group col-md-9">
              <label class="control-label">აირჩიეთ ტესტი: </label>
              <select class="form-control input-sm" ng-model="vote.testId" ng-change="changeTest(vote.testId)">
                <option ng-repeat="t in tests" value="{{t.id}}">{{t.name}}</option>
              </select>
            </div>
            <div class="form-group col-md-9">
              <label class="control-label">{{t('description')}}</label>
              <input type="text" id="description" ng-model="description" disabled="true" class="form-control input-sm"
                     placeholder="{{t('description')}}">
            </div>

            <ul style="list-style: none;" class="list-group col-md-12">
              <li class="list-group-item" ng-repeat="p in candidates" ng-show="currentTestTypeId != 4">
                <span class="label label-primary">{{$index + 1}}. {{p.name}}</span>
                <ul style="list-style: none; margin-top: 20px;">
                  <li ng-repeat="q in p.questions">
                    <b>{{q.title}}</b><br/>
                    {{q.description}}<br/>
                    <ul style="list-style: none;" ng-show="{{q.answerGroupId != 3}}">
                      <!--kuratorebs uchant mxolod Sevsebuli da ara shesavsebi position (1,2,3)-->
                      <div ng-show="(currentTestTypeId == 5 && personal.positionId > 3)">
                        <textarea rows="3" id="description" name="description" ng-model="tmpListText[p.id + '' + q.id]"
                                  class="form-control input-sm"></textarea>
                      </div>
                      <div ng-show="(currentTestTypeId == 5 && personal.positionId < 4)">
                        <p><b>კომენტარი:</b></p>
                        {{getAnswerByPersonId(p.id,q.id)}}
                      </div>
                      <li ng-repeat="a in q.answers">
                        <div class="radio">
                          <label>
                            <input type="radio" name="employeeTypes{{p.id}}{{q.id}}" ng-value="{{a.id}}"
                                   ng-model="tmpList[p.id + '' + q.id]" id="radio{{p.id}}{{q.id}}{{a.id}}">
                            {{a.text}}
                          </label>
                        </div>
                      </li>
                    </ul>
                    <br/>

                    <select style="width:200px;" class="form-control input-sm" ng-show="{{q.answerGroupId == 3}}"
                            ng-model="tmpList[p.id + '' + q.id]"
                            ng-change="filterAnswer(p.id, q.id, tmpList[p.id + '' + q.id])">
                      <option ng-repeat="ts in q.answers" value="{{ts.id}}">{{ts.text}}</option>
                    </select>
                  </li>
                </ul>
              </li>
              <li ng-show="currentTestTypeId == 4">
                <ul style="list-style: none; margin-top: 20px;">
                  <li ng-repeat="qq in questions">
                    <b>{{qq.title}}</b><br/>
                    {{qq.description}}<br/>

                    <ul style="list-style: none;">
                      <li ng-repeat="a in qq.answers">
                        <select style="width:200px;" class="form-control input-sm"
                                ng-model="tmpList[qq.id + '' + a.id]">
                          <option ng-repeat="p in personals" value="{{p.id}}">{{p.firstName}} {{p.lastName}}</option>
                        </select>
                      </li>
                    </ul>
                  </li>
                </ul>
              </li>
            </ul>

            <div class="form-group col-md-12">
              <button type="button" class="btn btn-primary btn-sm" ng-click="saveVote()" onclick="this.disabled = true;"
                      ng-disabled="testDisabled">{{t('send')}}
              </button>
            </div>
          </div>


        </div>

        <div class="panel-footer"></div>
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp" %>



