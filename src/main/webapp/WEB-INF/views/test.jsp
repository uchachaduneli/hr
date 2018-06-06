<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
  var app = angular.module("app", []);
  app.controller("managePreferences", function ($scope, $http, $filter, $location) {
    var absUrl = $location.absUrl();
    var type = absUrl.split("?")[1].split("=")[1];
    $scope.test = {activeStatusId: 1, typeId: 1};
    if (type) {
      $('li.active').removeClass('active');
      $('li#' + type).addClass('active');
    }
    $scope.showPanel = function (id) {
      $('#testPanel').addClass('hidden');
      $('#testList').addClass('hidden');
      $('#questionPanel').addClass('hidden');
      $('#questionList').addClass('hidden');
      $('#answerGroupPanel').addClass('hidden');
      $('#answerGroupList').addClass('hidden');
      $('#answerTypePanel').addClass('hidden');
      $('#answerTypeList').addClass('hidden');
      $('#' + id).removeClass('hidden');
    };
    $scope.changeTest = function (testId) {
      function question(res) {
        $scope.questions = res;
      }

      ajaxCall($http, "misc/get-question-by-test?testId=" + testId, {}, question);
    };
    $scope.testStatuses = [];
    $scope.testTypes = [];
    $scope.selectedTestId = 0;

    if (type == 'test') {
      $scope.showPanel('testList');

      function successTest(res) {
        $scope.tests = res;
      }

      ajaxCall($http, "misc/get-test", {}, successTest);

      function successTestStatus(res) {
        $scope.testStatuses = res;
      }

      ajaxCall($http, "misc/get-test-status", {}, successTestStatus);

      function successTestType(res) {
        $scope.testTypes = res;
      }

      ajaxCall($http, "misc/get-test-type", {}, successTestType);

    } else if (type == 'questions') {
      if (absUrl.split("?")[2]) {
        $scope.selectedTestId = absUrl.split("?")[2].split("=")[1];
      }
      $scope.showPanel('questionList');

      function successTest(res) {
        $scope.tests = res;
      }

      ajaxCall($http, "misc/get-test", {}, successTest);

      function groups(res) {
        $scope.answerGroups = res;
      }

      ajaxCall($http, "misc/get-answer-groups", {}, groups);

      function getQuestionTypes(res) {
        $scope.questionTypes = res;
      }

      ajaxCall($http, "misc/get-question-types", {}, getQuestionTypes);

      function positions(res) {
        $scope.positions = res;
      }

      ajaxCall($http, "misc/get-question-positions", {}, positions);
      $scope.changeTest($scope.selectedTestId);


    } else if (type == 'answers') {
      $scope.showPanel('answerList');

      function answers(res) {
        $scope.answers = res;
      }

      ajaxCall($http, "misc/get-answers", {}, answers);

    } else if (type == 'answerGroups') {
      $scope.answerGroup = {typeId: 1};
      $scope.showPanel('answerGroupList');

      function groups(res) {
        $scope.answerGroups = res;
      }

      ajaxCall($http, "misc/get-answer-groups", {}, groups);

    } else if (type == 'answerTypes') {
      $scope.showPanel('answerTypeList');

      function types(res) {
        $scope.answerTypes = res;
      }

      ajaxCall($http, "misc/get-answer-types", {}, types);

    }
    $scope.t = function (s) {
      return Lang.get(s);
    };
    $scope.saveTest = function (isValid) {
      if (isValid) {
        ajaxCall($http, "misc/save-test", angular.toJson($scope.test), reload);
      } else {
        alert('no valid');
      }
    };
    $scope.editTest = function (item) {
      $('#testList').addClass('hidden');
      $('#testPanel').removeClass('hidden');
      $scope.test = item;
    };
    $scope.testConfirmPanelHide = function () {
      $scope.objId = null;
      $('#removeTestConfirm').addClass('hidden');
      $('#testList').removeClass('hidden');
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
      if (confirm("დარწმუნებული ხართ რომ გსურთ ტესტის წაშლა?")) {
        ajaxCall($http, "misc/remove-test?testId=" + id, {}, reload);
      }
    };

    $scope.showQuestion = function (testId) {
      window.location = "test?type=questions?testId=" + testId;
    };
    $scope.saveQuestion = function (isValid) {
      if (isValid) {
        ajaxCall($http, "misc/save-question", angular.toJson($scope.question), reload);
      } else {
        alert('no valid');
      }
    };
    $scope.editQuestion = function (selectItem) {
      $('#questionList').addClass('hidden');
      $('#questionPanel').removeClass('hidden');
      $scope.question = selectItem;

      function groups(res) {
        $scope.answerGroups = res;
      }

      ajaxCall($http, "misc/get-answer-groups", {}, groups);
    };
    $scope.questionPanelHide = function () {
      $scope.question = null;
      $('#questionPanel').addClass('hidden');
      $('#questionList').removeClass('hidden');
    };
    $scope.removeQuestion = function (id) {
      ajaxCall($http, "misc/remove-question?questionId=" + id, {}, reload);
    };

    $scope.saveAnswer = function (isValid) {
      if (isValid) {
        ajaxCall($http, "misc/save-answer", angular.toJson($scope.answer), reload);
      } else {
        alert('no valid');
      }
    };
    $scope.editAnswer = function (id) {
      $('#answerList').addClass('hidden');
      $('#answerPanel').removeClass('hidden');
      $scope.answer = id;
    };
    $scope.answerPanelHide = function () {
      $scope.answer = null;
      $('#answerPanel').addClass('hidden');
      $('#answerList').removeClass('hidden');
    };
    $scope.removeAnswer = function (id) {
      ajaxCall($http, "misc/remove-answer?answerId=" + id, {}, reload);
    };

    $scope.saveAnswerGroup = function (isValid) {
      if (isValid) {
        ajaxCall($http, "misc/save-answerGroup", angular.toJson($scope.answerGroup), reload);
      } else {
        alert('no valid');
      }
    };
    $scope.editAnswerGroup = function (selected) {
      $('#answerGroupList').addClass('hidden');
      $('#answerGroupPanel').removeClass('hidden');
      $scope.answerGroup = selected;
    };
    $scope.answerGroupPanelHide = function () {
      $scope.answerGroup = {typeId: 1};
      $('#answerGroupPanel').addClass('hidden');
      $('#answerGroupList').removeClass('hidden');
    };
    $scope.removeAnswerGroup = function (id) {
      ajaxCall($http, "misc/remove-answerGroup?answerGroupId=" + id, {}, reload);
    };
    $scope.answerGroupAnswerPanelHide = function () {
      $('#answerList').addClass('hidden');
      $('#answerGroupList').removeClass('hidden');
    };
    $scope.addAnswer = function (id) {
      $('#answerGroupList').addClass('hidden');
      $('#answerPanel').removeClass('hidden');
      $scope.answer = {'groupId': id};
    };
    $scope.showAnswer = function (id) {
      $('#answerGroupList').addClass('hidden');
      $('#answerList').removeClass('hidden');

      function answers(res) {
        $scope.answers = res;
      }

      ajaxCall($http, "misc/get-answers-by-answer-group?answerGroupId=" + id, {}, answers);
    };


    $scope.saveAnswerType = function (isValid) {
      if (isValid) {
        ajaxCall($http, "misc/save-answerType", angular.toJson($scope.answerType), reload);
      } else {
        alert('no valid');
      }
    };
    $scope.editAnswerType = function (id) {
      $('#answerTypeList').addClass('hidden');
      $('#answerTypePanel').removeClass('hidden');
      $scope.answerType = id;
    };
    $scope.answerTypePanelHide = function () {
      $scope.answerType = null;
      $('#answerTypePanel').addClass('hidden');
      $('#answerTypeList').removeClass('hidden');
    };
    $scope.removeAnswerType = function (id) {
      ajaxCall($http, "misc/remove-answerType?answerTypeId=" + id, {}, reload);
    };
    $scope.addPersonal = function (item) {
      if (item.typeId == 1 || item.typeId == 3 || item.typeId == 5) {
        window.location = "testTree?testId=" + item.id;
      } else if (item.typeId == 2) {
        window.location = "testTreeDepartment?testId=" + item.id;
      }
    };


    $scope.showEditableForm = function () {
      if (type == 'test') {
        $scope.organisation = null;
        $('#testList').addClass('hidden');
        $('#testPanel').removeClass('hidden');
      } else if (type == 'questions') {
        if ($scope.selectedTestId == 0) {
          alert('აირჩიეთ ტესტი რომელზეც ამატებთ შეკითხვას');
        } else {
          $scope.question = {testId: $scope.selectedTestId, questionTypeId: 1, answerGroupId: 1, positionId: 1};
          $('#questionList').addClass('hidden');
          $('#questionPanel').removeClass('hidden');
        }
      } else if (type == 'answers') {
        $scope.group = null;
        $('#answerList').addClass('hidden');
        $('#answerPanel').removeClass('hidden');
      } else if (type == 'answerGroups') {
        $scope.unit = null;
        $('#answerGroupList').addClass('hidden');
        $('#answerGroupPanel').removeClass('hidden');
      } else if (type == 'answerTypes') {
        $scope.type = null;
        $('#answerTypeList').addClass('hidden');
        $('#answerTypePanel').removeClass('hidden');
      }
    };

  });

</script>

<div class="col-md-12" ng-controller="managePreferences">
  <div class="row">
    <div class="col-md-12">
      <div class="panel panel-default">
        <div class="panel-heading">
          <div class="row">
            <div class="col-md-3 upper-font"><b>შეფასება</b></div>
            <div class="col-md-9">
              <button class="btn btn-primary btn-sm pull-right" ng-click="showEditableForm();">
                <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;დამატება
              </button>
            </div>
          </div>
        </div>
        <br/>
        <div class="col-md-12">
          <ul class="nav nav-tabs upper-font">
            <li id="test" role="presentation" class="active"><a href="test?type=test">შეფასება</a></li>
            <li id="questions" role="presentation" class="active"><a href="test?type=questions">შეკითხვები</a></li>
            <li id="answerGroups" role="presentation"><a href="test?type=answerGroups">პასუხის ჯგუფები</a></li>
            <li id="answerTypes" role="presentation"><a href="test?type=answerTypes">პასუხის ტიპები</a></li>
          </ul>
        </div>
        <div class="panel-body maxPanel">
          <div class="col-md-9">
            <br/>
            <table class="table table-striped table-hover" id="testList">
              <tr>
                <th>Id</th>
                <th>სახელი</th>
                <th>აღწერა</th>
                <th>ტიპი</th>
                <th>სტატუსი</th>
                <th style="min-width: 130px; width: 150px;">ქმედება</th>
              </tr>
              <tr ng-repeat="x in tests">
                <td>{{ $index + 1}}</td>
                <td>{{ x.name}}</td>
                <td>{{ x.description}}</td>
                <td>{{ x.typeName}}</td>
                <td>{{ x.activeStatusName}}</td>
                <td>
                  <button class="btn btn-xs btn-success" title="ტესტის რედაქტირება" ng-click="editTest(x);">
                    <span class="glyphicon glyphicon-pencil"></span>
                  </button>
                  <button class="btn btn-xs btn-danger" ng-disabled="x.activeStatusId != 1" title="ტესტის წაშლა"
                          ng-click="removeTest(x.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                  <button class="btn btn-xs" title="შეკითხვები" ng-click="showQuestion(x.id);">
                    <span class="glyphicon glyphicon-tag"></span>
                  </button>
                  <button class="btn btn-xs" title="თანამშრომლების მიბმა"
                          ng-disabled="x.activeStatusId == 3 || x.typeId == 4" ng-click="addPersonal(x);">
                    <span class="glyphicon glyphicon-user"></span>
                  </button>
                </td>
              </tr>
            </table>

            <form name="questionListForm" class="hidden" id="questionList">
              <br/>
              <div class="form-group col-md-6">
                <label class="control-label">აირჩიეთ ტესტი: </label>
                <select class="form-control input-sm" ng-model="selectedTestId" ng-change="changeTest(selectedTestId)">
                  <option ng-repeat="t in tests" value="{{t.id}}">{{t.name}}</option>
                </select>
              </div>
              <table class="table table-striped table-hover col-md-12" id="">
                <tr>
                  <th>Id</th>
                  <th>დასახელება</th>
                  <th>აღწერა</th>
                  <th>პასუხის ჯგუფი</th>
                  <th>პოზიცია</th>
                  <th style="min-width: 90px; width: 100px;">{{t('action')}}</th>
                </tr>
                <tr ng-repeat="ch in questions">
                  <td>{{ $index + 1}}</td>
                  <td>{{ch.title}}</td>
                  <td>{{ch.description}}</td>
                  <td>{{ch.answerGroupName}}</td>
                  <td>{{ch.positionName}}</td>
                  <td>
                    <button class="btn btn-xs btn-success" title="შეკითხვის რედაქტირება" ng-click="editQuestion(ch);">
                      <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                    <button class="btn btn-xs btn-danger" title="შეკითხვის წაშლა" ng-click="removeQuestion(ch.id);">
                      <span class="glyphicon glyphicon-remove"></span>
                    </button>
                  </td>
                </tr>
              </table>

            </form>

            <table class="table table-striped table-hover hidden " id="answerTypeList">
              <tr>
                <th>Id</th>
                <th>დასახელება</th>
                <th style="min-width: 90px; width: 100px;">{{t('action')}}</th>
              </tr>
              <tr ng-repeat="at in answerTypes">
                <td>{{ $index + 1}}</td>
                <td>{{at.name}}</td>
                <td>
                  <button class="btn btn-xs btn-success" ng-click="editAnswerType(at);">
                    <span class="glyphicon glyphicon-pencil"></span>
                  </button>
                  <button class="btn btn-xs btn-danger" ng-click="removeAnswerType(at.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </td>
              </tr>
            </table>

            <table class="table table-striped table-hover hidden" id="answerGroupList">
              <tr>
                <th>Id</th>
                <th>დასახელება</th>
                <th>ტიპი</th>
                <th style="min-width: 100px; width: 120px;">{{t('action')}}</th>
              </tr>
              <tr ng-repeat="gr in answerGroups">
                <td>{{ $index + 1}}</td>
                <td>{{gr.name}}</td>
                <td>{{gr.typeName}}</td>
                <td>
                  <button class="btn btn-xs btn-success" title="რედაქტირება" ng-click="editAnswerGroup(gr);">
                    <span class="glyphicon glyphicon-pencil"></span>
                  </button>
                  <button class="btn btn-xs btn-danger" title="წაშლა" ng-click="removeAnswerGroup(gr.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                  <button class="btn btn-xs" title="პასუხის დამატება" ng-click="addAnswer(gr.id);">
                    <span class="glyphicon glyphicon-plus"></span>
                  </button>
                  <button class="btn btn-xs" title="პასუხები" ng-click="showAnswer(gr.id);">
                    <span class="glyphicon glyphicon-tasks"></span>
                  </button>
                </td>
              </tr>
            </table>

            <form name="answerListForm" class="hidden" id="answerList">
              <div class="col-md-12"><h5><b>{{t('answers')}}</b></h5></div>
              <table class="table table-striped table-hover col-md-12">
                <tr>
                  <th>Id</th>
                  <th>დასახელება</th>
                  <th>ქულა</th>
                  <th style="min-width: 90px; width: 100px;">{{t('action')}}</th>
                </tr>
                <tr ng-repeat="ca in answers">
                  <td>{{ $index + 1}}</td>
                  <td>{{ca.text}}</td>
                  <td>{{ca.score}}</td>
                  <td>
                    <button class="btn btn-xs btn-success" title="პასუხის რედაქტირება" ng-click="editAnswer(ca);">
                      <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                    <button class="btn btn-xs btn-danger" title="პასუხის წაშლა" ng-click="removeAnswer(ca.id);">
                      <span class="glyphicon glyphicon-remove"></span>
                    </button>
                  </td>
                </tr>
              </table>
              <div class="form-group col-md-12">
                <input type="button" class="btn btn-default btn-sm" value="{{t('cancel')}}"
                       ng-click="answerGroupAnswerPanelHide();">
              </div>
            </form>


          </div>
          <div class="col-md-4 col-md-offset-4">

            <form name="testForm" class="hidden" id="testPanel" ng-submit="saveTest(testForm.$valid);" novalidate>
              <div class="col-md-12 text-center"><h4>{{t('testInfo')}}</h4></div>
              <div class="form-group col-md-12"
                   ng-class="{ 'has-error' : testForm.name.$invalid && !testForm.name.$pristine}">
                <label class="control-label">დასახელება</label>
                <input type="text" id="name" name="name" ng-model="test.name" class="form-control input-sm"
                       placeholder="დასახელება" required="true">
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">აღწერა</label>
                <input type="text" id="description" ng-model="test.description" class="form-control input-sm"
                       placeholder="აღწერა">
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">სტატუსი</label>
                <select class="form-control input-sm" ng-model="test.activeStatusId">
                  <option ng-repeat="ts in testStatuses" value="{{ts.id}}">{{ts.name}}</option>
                </select>
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">ტიპი</label>
                <select class="form-control input-sm" ng-model="test.typeId">
                  <option ng-repeat="tt in testTypes" value="{{tt.id}}">{{tt.name}}</option>
                </select>
              </div>
              <div class="form-group col-md-12">
                <button type="submit" class="btn btn-primary btn-sm">შენახვა</button>
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="testPanelHide();">
              </div>
            </form>

            <form name="questionForm" class="hidden" id="questionPanel" ng-submit="saveQuestion(questionForm.$valid);"
                  novalidate>
              <div class="col-md-12 text-center"><h4>შეკითხვის შესახებ</h4></div>
              <div class="form-group col-md-12"
                   ng-class="{ 'has-error' : questionForm.name.$invalid && !questionForm.name.$pristine}">
                <label class="control-label">{{t('title')}}</label>
                <input type="title" id="name" name="title" ng-model="question.title" class="form-control input-sm"
                       placeholder="{{t('title')}}" required="true">
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">აღწერა</label>
                <input type="text" id="description" name="description" ng-model="question.description"
                       class="form-control input-sm" placeholder="{{t('description')}}">
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">შეკითხვის ტიპი</label>
                <select class="form-control input-sm" ng-model="question.questionTypeId">
                  <option ng-repeat="q in questionTypes" value="{{q.id}}">{{q.name}}</option>
                </select>
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">{{t('answerGroup')}}</label>
                <select class="form-control input-sm" ng-model="question.answerGroupId">
                  <option ng-repeat="o in answerGroups" value="{{o.id}}">{{o.name}}</option>
                </select>
              </div>
              <div class="form-group col-md-12">
                <label class="control-label">პოზიცია</label>
                <select class="form-control input-sm" ng-model="question.positionId">
                  <option ng-repeat="p in positions" value="{{p.id}}">{{p.name}}</option>
                </select>
              </div>
              <div class="form-group col-md-12">
                <button type="submit" class="btn btn-primary btn-sm">{{t('save')}}</button>
                <input type="button" class="btn btn-default btn-sm" value="{{t('cancel')}}"
                       ng-click="questionPanelHide();">
              </div>
            </form>

            <form name="answerForm" class="hidden" id="answerPanel" ng-submit="saveAnswer(answerForm.$valid);"
                  novalidate>
              <div class="col-md-12 text-center"><h4>პასუხის შესახებ</h4></div>
              <div class="form-group col-md-12"
                   ng-class="{ 'has-error' : answerForm.name.$invalid && !answerForm.name.$pristine}">
                <label class="control-label">{{t('name')}}</label>
                <input type="text" id="name" name="name" ng-model="answer.text" class="form-control input-sm"
                       placeholder="{{t('name')}}" required="true">
              </div>
              <div class="form-group col-md-12"
                   ng-class="{ 'has-error' : answerForm.score.$invalid && !answerForm.score.$pristine}">
                <label class="control-label">{{t('score')}}</label>
                <input type="text" id="name" name="score" ng-model="answer.score" class="form-control input-sm"
                       placeholder="{{t('score')}}" required="true">
              </div>
              <div class="form-group col-md-12">
                <button type="submit" class="btn btn-primary btn-sm">შენახვა</button>
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="answerPanelHide();">
              </div>
            </form>

            <form name="answerGroupForm" class="hidden" id="answerGroupPanel"
                  ng-submit="saveAnswerGroup(answerGroupForm.$valid);" novalidate>
              <div class="col-md-12 text-center"><h4>პასუხის ჯგუფის შესახებ</h4></div>
              <div class="form-group col-md-12"
                   ng-class="{ 'has-error' : answerGroupForm.name.$invalid && !answerGroupForm.name.$pristine}">
                <label class="control-label">დასახელება</label>
                <input type="text" id="name" name="name" ng-model="answerGroup.name" class="form-control input-sm"
                       placeholder="დასახელება" required="true">
              </div>
              <div class="form-group col-md-12">
                <button type="submit" class="btn btn-primary btn-sm">{{t('save')}}</button>
                <input type="button" class="btn btn-default btn-sm" value="{{t('cancel')}}"
                       ng-click="answerGroupPanelHide();">
              </div>
            </form>

            <form name="answerTypeForm" class="hidden" id="answerTypePanel"
                  ng-submit="saveAnswerType(answerTypeForm.$valid);" novalidate>
              <div class="col-md-12 text-center"><h4>პასუხის ტიპის შესახებ</h4></div>
              <div class="form-group col-md-12"
                   ng-class="{ 'has-error' : answerTypeForm.name.$invalid && !answerTypeForm.name.$pristine}">
                <label class="control-label">დასახელება</label>
                <input type="text" id="name" name="name" ng-model="answerType.name" class="form-control input-sm"
                       placeholder="დასახელება" required="true">
              </div>
              <div class="form-group col-md-12">
                <button type="submit" class="btn btn-primary btn-sm">შენახვა</button>
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="answerTypePanelHide();">
              </div>
            </form>
          </div>
        </div>

        <div class="panel-footer"></div>
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp" %>



