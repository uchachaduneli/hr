<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
  $(document).ready(function () {
    $("#birthDate").datepicker({
      changeMonth: true,
      changeYear: true,
      yearRange: '1930:2030',
      dateFormat: 'dd/mm/yy',
      onSelect: function () {
        $("#birthDate").change();
      }
    });

  });

</script>
<script>
  var app = angular.module("app", []);
  app.directive("fileread", [function () {
    return {
      scope: {
        fileread: "="
      },
      link: function (scope, element, attributes) {
        element.bind("change", function (changeEvent) {
          var reader = new FileReader();
          reader.onload = function (loadEvent) {
            scope.$apply(function () {
              scope.fileread = loadEvent.target.result;
            });
          }
          reader.readAsDataURL(changeEvent.target.files[0]);
        });
      }
    }
  }]);
  app.controller("managePersonal", function ($scope, $http, $filter, $location) {
    var absUrl = $location.absUrl();
    var personalId = absUrl.split("?")[1].split("=")[1];

    function successGetPersonal(res) {

      $scope.personal = res;
      $scope.personal.birthDate = $filter("date")($scope.personal.birthDate, 'dd/MM/yyyy');
    }

    ajaxCall($http, "personal/get-personal-by-id?personalId=" + personalId, {}, successGetPersonal);

    function successGetGroup(res) {
      $scope.groups = res;
    }

    ajaxCall($http, "user/get-groups", {}, successGetGroup);

    $scope.showPanel = function (id) {
      $('#' + id).removeClass('hidden');
    };
    $scope.t = function (s) {
      return Lang.get(s);
    };

    $scope.savePersonal = function () {
      if ($('#birthDate').val() != "") {
        $scope.personal.birthDate = $('#birthDate').datepicker('getDate');
      }
      ajaxCall($http, "personal/save-personal", angular.toJson($scope.personal), reload);
    };
    $scope.personalPanelHide = function () {
      $scope.personal = null;
      window.location.href = "/hr/home";
    };
    $scope.deleteFile = function (id) {
      ajaxCall($http, "personal/delete-file?id=" + id, {}, reload);
    };
  });</script>

<div class="col-md-12" ng-controller="managePersonal">
  <div class="panel panel-default">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-3 upper-font"><b>{{t('personal')}}</b></div>
        <div class="col-md-2 col-md-offset-7">
        </div>
      </div>
    </div>
    <div class="panel-body maxPanel">
      <div class="col-md-6" id="medicamentPanel">
        <div class="col-md-12 text-center"><h4>{{t('personalInfo')}}</h4></div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('name')}}</label>
          <input type="text" id="name" name="name" ng-model="personal.firstName" class="form-control input-sm"
                 placeholder="{{t('name')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('lastname')}}</label>
          <input type="text" id="lastName" ng-model="personal.lastName" class="form-control input-sm"
                 placeholder="{{t('lastname')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('mail')}}</label>
          <input type="text" id="mail" ng-model="personal.mail" readonly="true" class="form-control input-sm"
                 placeholder="{{t('mail')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('structure')}}</label>
          <input type="text" id="structureName" ng-model="personal.structureName" disabled="true"
                 class="form-control input-sm" placeholder="{{t('structure')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('position')}}</label>
          <input type="text" id="positionName" ng-model="personal.positionName" readonly="true"
                 class="form-control input-sm" placeholder="{{t('position')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('personalNumber')}}</label>
          <input type="text" id="pidNumber" ng-model="personal.pidNumber" class="form-control input-sm"
                 placeholder="{{t('personalNumber')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('birthDate')}}</label>
          <input type="text" id="birthDate" ng-model="personal.birthDate" class="form-control input-sm"
                 placeholder="{{t('birthDate')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('group')}}</label>
          <select class="form-control input-sm" ng-model="personal.groupId">
            <option ng-repeat="g in groups" value="{{g.id}}">{{g.name}}</option>
          </select>
        </div>
        <div class="form-group col-md-12">
          <input type="submit" class="btn btn-danger btn-sm" value="{{t('save')}}" ng-click="savePersonal();">
          <input type="button" class="btn btn-default btn-sm" value="{{t('cancel')}}" ng-click="personalPanelHide();">
        </div>
        </form>
      </div>
      <div class="col-md-6">

        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingOne">
              <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false"
                   aria-controls="collapseOne">
                  პირადი ინფორმაცია
                </a>
              </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
              <div class="panel-body">
                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('cv')}}</label>
                  <input type="text" ng-model="personal.cvName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_2" fileread="personal.cv" class="input-sm col-md-8">

                </div>
                <div class="form-group col-md-12" ng-repeat="cv in personal.documents| filter: {documentTypeId:2}">
                  <a href="file/get-file?identifier={{cv.name}}&group={{personal.id}}" target="_blank">
                    {{cv.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(cv.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('diploma')}}</label>
                  <input type="text" ng-model="personal.diplomaName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_3" fileread="personal.diploma"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="dd in personal.documents| filter: {documentTypeId:3}">
                  <a href="file/get-file?identifier={{dd.name}}&group={{personal.id}}" target="_blank">
                    {{dd.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(dd.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('idcard')}}</label>
                  <input type="text" ng-model="personal.idcardName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_4" fileread="personal.idcard"
                         class="input-sm col-md-8 upload-file">
                </div>
                <div class="form-group col-md-12" ng-repeat="ic in personal.documents| filter: {documentTypeId:4}">
                  <a href="file/get-file?identifier={{ic.name}}&group={{personal.id}}" target="_blank">
                    {{ic.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(ic.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>
                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('certificates')}}</label>
                  <input type="text" ng-model="personal.certificatesName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_15" fileread="personal.certificates"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="c in personal.documents| filter: {documentTypeId:15}">
                  <a href="file/get-file?identifier={{c.name}}&group={{personal.id}}" target="_blank">
                    {{c.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(c.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('image')}}</label>
                  <input type="text" ng-model="personal.fileName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_16" fileread="personal.file"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="i in personal.documents| filter: {documentTypeId:16}">
                  <a href="file/get-file?identifier={{i.name}}&group={{personal.id}}" target="_blank">
                    {{i.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(i.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>


              </div>
            </div>
          </div>
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingTwo">
              <h4 class="panel-title">
                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"
                   aria-expanded="false" aria-controls="collapseTwo">
                  ცნობები
                </a>
              </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
              <div class="panel-body">
                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('hrRecordSheet')}}</label>
                  <input type="text" ng-model="personal.hrRecordName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" fileread="personal.hrRecord" class="input-sm col-md-8">
                </div>
                <div class="form-group col-md-12" ng-repeat="hr in personal.documents| filter: {documentTypeId:1}">
                  <a href="file/get-file?identifier={{hr.name}}&group={{personal.id}}" target="_blank">
                    {{hr.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(hr.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('convictionCertificate')}}</label>
                  <input type="text" ng-model="personal.convictionCertificateName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_5" fileread="personal.convictionCertificate"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="cc in personal.documents| filter: {documentTypeId:5}">
                  <a href="file/get-file?identifier={{cc.name}}&group={{personal.id}}" target="_blank">
                    {{cc.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(cc.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('drugReference')}}</label>
                  <input type="text" ng-model="personal.drugReferenceName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_6" fileread="personal.drugReference"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="dr in personal.documents| filter: {documentTypeId:6}">
                  <a href="file/get-file?identifier={{dr.name}}&group={{personal.id}}" target="_blank">
                    {{dr.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(dr.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('oathOfEmployee')}}</label>
                  <input type="text" ng-model="personal.oathOfEmployeeName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_7" fileread="personal.oathOfEmployee"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="oe in personal.documents| filter: {documentTypeId:7}">
                  <a href="file/get-file?identifier={{oe.name}}&group={{personal.id}}" target="_blank">
                    {{oe.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(cv.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('receiptOfEmployee')}}</label>
                  <input type="text" ng-model="personal.receiptOfEmployeeName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_8" fileread="personal.receiptOfEmployee"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="e in personal.documents| filter: {documentTypeId:8}">
                  <a href="file/get-file?identifier={{e.name}}&group={{personal.id}}" target="_blank">
                    {{e.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(e.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

              </div>
            </div>
          </div>
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingThree">
              <h4 class="panel-title">
                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"
                   aria-expanded="false" aria-controls="collapseThree">
                  ბრძანებები
                </a>
              </h4>
            </div>
            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
              <div class="panel-body">
                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('orderOfAppointment')}}</label>
                  <input type="text" ng-model="personal.orderOfAppointmentName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_9" fileread="personal.orderOfAppointment"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="a in personal.documents| filter: {documentTypeId:9}">
                  <a href="file/get-file?identifier={{a.name}}&group={{personal.id}}" target="_blank">
                    {{a.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(a.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('orderOfPosition')}}</label>
                  <input type="text" ng-model="personal.orderOfPositionName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_10" fileread="personal.orderOfPosition"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="op in personal.documents| filter: {documentTypeId:10}">
                  <a href="file/get-file?identifier={{op.name}}&group={{personal.id}}" target="_blank">
                    {{op.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(op.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('orderOfPromotion')}}</label>
                  <input type="text" ng-model="personal.orderOfPromotionName" class="input-sm col-md-4">
                  <input type="file" id="personalfile_11" fileread="personal.orderOfPromotion"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="p in personal.documents| filter: {documentTypeId:11}">
                  <a href="file/get-file?identifier={{p.name}}&group={{personal.id}}" target="_blank">
                    {{p.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(p.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('maternityLeave')}}</label>
                  <input type="text" ng-model="personal.maternityLeaveName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_12" fileread="personal.maternityLeave"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="m in personal.documents| filter: {documentTypeId:12}">
                  <a href="file/get-file?identifier={{m.name}}&group={{personal.id}}" target="_blank">
                    {{m.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(m.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('impositionOfDuties')}}</label>
                  <input type="text" ng-model="personal.impositionOfDutiesName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_13" fileread="personal.impositionOfDuties"
                         class="input-sm col-md-8 upload-file">
                </div>
                <div class="form-group col-md-12" ng-repeat="d in personal.documents| filter: {documentTypeId:13}">
                  <a href="file/get-file?identifier={{d.name}}&group={{personal.id}}" target="_blank">
                    {{d.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(d.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('orderOfDismissal')}}</label>
                  <input type="text" ng-model="personal.orderOfDismissalName" class="input-sm col-md-4"
                         placeholder="{{t('fileName')}}">
                  <input type="file" id="personalfile_14" fileread="personal.orderOfDismissal"
                         class="input-sm col-md-8 upload-file">

                </div>
                <div class="form-group col-md-12" ng-repeat="o in personal.documents| filter: {documentTypeId:14}">
                  <a href="file/get-file?identifier={{o.name}}&group={{personal.id}}" target="_blank">
                    {{o.title}}
                  </a>
                  <button type="button" class="btn btn-xs" ng-click="deleteFile(o.id);">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
    <div class="panel-footer"></div>
  </div>
</div>

<%@include file="footer.jsp" %>



