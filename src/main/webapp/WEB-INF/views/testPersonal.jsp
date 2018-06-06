<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script src="http://cdn.kendostatic.com/2013.2.716/js/kendo.all.min.js"></script>
<script type="text/javascript" src="resources/js/angular-kendo.js"></script>
<script type="text/javascript" src="resources/js/script.js"></script>


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
          <div class="col-md-9">

            <div kendo-tree-view kendo-tree-view k-options="treeOptions"
                 k-data-source="treeObj"></div>
            <br></br>

          </div>


        </div>

        <div class="panel-footer"></div>
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp" %>



