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
  app.controller("managePersonal", function ($scope, $http, $filter) {
    function successGetPersonal(res) {
      $scope.personal = res;
      $scope.personal.birthDate = $filter("date")($scope.personal.birthDate, 'dd/MM/yyyy');
    }

    ajaxCall($http, "personal/get-personal-info", {}, successGetPersonal);
    $scope.showPanel = function (id) {
      $('#' + id).removeClass('hidden');
    };
    $scope.t = function (s) {
      return Lang.get(s);
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
          <input type="text" id="name" name="name" ng-model="personal.firstName" disabled="true"
                 class="form-control input-sm" placeholder="{{t('name')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('lastname')}}</label>
          <input type="text" id="lastName" ng-model="personal.lastName" disabled="true" class="form-control input-sm"
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
          <input type="text" id="pidNumber" ng-model="personal.pidNumber" disabled="true" class="form-control input-sm"
                 placeholder="{{t('personalNumber')}}">
        </div>
        <div class="form-group col-md-12">
          <label class="control-label">{{t('birthDate')}}</label>
          <input type="text" id="birthDate" ng-model="personal.birthDate" disabled="true" class="form-control input-sm"
                 placeholder="{{t('birthDate')}}">
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
                </div>
                <div class="form-group col-md-12" ng-repeat="cv in personal.documents| filter: {documentTypeId:2}">
                  <a href="file/get-file?identifier={{cv.name}}&group={{personal.id}}" target="_blank">
                    {{cv.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('diploma')}}</label>
                </div>
                <div class="form-group col-md-12" ng-repeat="dd in personal.documents| filter: {documentTypeId:3}">
                  <a href="file/get-file?identifier={{dd.name}}&group={{personal.id}}" target="_blank">
                    {{dd.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('idcard')}}</label>
                </div>
                <div class="form-group col-md-12" ng-repeat="ic in personal.documents| filter: {documentTypeId:4}">
                  <a href="file/get-file?identifier={{ic.name}}&group={{personal.id}}" target="_blank">
                    {{ic.title}}
                  </a>
                </div>
                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('certificates')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="c in personal.documents| filter: {documentTypeId:15}">
                  <a href="file/get-file?identifier={{c.name}}&group={{personal.id}}" target="_blank">
                    {{c.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('image')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="i in personal.documents| filter: {documentTypeId:16}">
                  <a href="file/get-file?identifier={{i.name}}&group={{personal.id}}" target="_blank">
                    {{i.title}}
                  </a>
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
                </div>
                <div class="form-group col-md-12" ng-repeat="hr in personal.documents| filter: {documentTypeId:1}">
                  <a href="file/get-file?identifier={{hr.name}}&group={{personal.id}}" target="_blank">
                    {{hr.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('convictionCertificate')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="cc in personal.documents| filter: {documentTypeId:5}">
                  <a href="file/get-file?identifier={{cc.name}}&group={{personal.id}}" target="_blank">
                    {{cc.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('drugReference')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="dr in personal.documents| filter: {documentTypeId:6}">
                  <a href="file/get-file?identifier={{dr.name}}&group={{personal.id}}" target="_blank">
                    {{dr.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('oathOfEmployee')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="oe in personal.documents| filter: {documentTypeId:7}">
                  <a href="file/get-file?identifier={{oe.name}}&group={{personal.id}}" target="_blank">
                    {{oe.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('receiptOfEmployee')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="e in personal.documents| filter: {documentTypeId:8}">
                  <a href="file/get-file?identifier={{e.name}}&group={{personal.id}}" target="_blank">
                    {{e.title}}
                  </a>
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

                </div>
                <div class="form-group col-md-12" ng-repeat="a in personal.documents| filter: {documentTypeId:9}">
                  <a href="file/get-file?identifier={{a.name}}&group={{personal.id}}" target="_blank">
                    {{a.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('orderOfPosition')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="op in personal.documents| filter: {documentTypeId:10}">
                  <a href="file/get-file?identifier={{op.name}}&group={{personal.id}}" target="_blank">
                    {{op.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('orderOfPromotion')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="p in personal.documents| filter: {documentTypeId:11}">
                  <a href="file/get-file?identifier={{p.name}}&group={{personal.id}}" target="_blank">
                    {{p.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('maternityLeave')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="m in personal.documents| filter: {documentTypeId:12}">
                  <a href="file/get-file?identifier={{m.name}}&group={{personal.id}}" target="_blank">
                    {{m.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('impositionOfDuties')}}</label>
                </div>
                <div class="form-group col-md-12" ng-repeat="d in personal.documents| filter: {documentTypeId:13}">
                  <a href="file/get-file?identifier={{d.name}}&group={{personal.id}}" target="_blank">
                    {{d.title}}
                  </a>
                </div>

                <div class="form-group col-md-12">
                  <label class="control-label col-md-12">{{t('orderOfDismissal')}}</label>

                </div>
                <div class="form-group col-md-12" ng-repeat="o in personal.documents| filter: {documentTypeId:14}">
                  <a href="file/get-file?identifier={{o.name}}&group={{personal.id}}" target="_blank">
                    {{o.title}}
                  </a>
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



