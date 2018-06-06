var app = angular.module("app", ['kendo.directives']).run(function ($rootScope, $location) {
});


app.controller("TreeController", [
  "$scope",
  "$location",
  "$log",
  function ($scope, $location, $log) {

    $scope.treeObj = undefined;


    $scope.initTree = function () {
      var arrayObj = [{
        id: 1,
        text: "My Documents",
        expanded: true,
        spriteCssClass: "rootfolder",
        items: [{
          id: 2,
          text: "Kendo UI Project",
          expanded: true,
          spriteCssClass: "folder",
          items: [{
            id: 3,
            text: "about.html",
            spriteCssClass: "html"
          }, {
            id: 4,
            text: "index.html",
            spriteCssClass: "html"
          }, {
            id: 5,
            text: "logo.png",
            spriteCssClass: "image"
          }]
        }, {
          id: 6,
          text: "New Web Site",
          expanded: true,
          spriteCssClass: "folder",
          items: [{
            id: 7,
            text: "mockup.jpg",
            spriteCssClass: "image"
          }, {
            id: 8,
            text: "Research.pdf",
            spriteCssClass: "pdf"
          },]
        }, {
          id: 9,
          text: "Reports",
          expanded: true,
          spriteCssClass: "folder",
          items: [{
            id: 10,
            text: "February.pdf",
            spriteCssClass: "pdf"
          }, {
            id: 11,
            text: "March.pdf",
            spriteCssClass: "pdf"
          }, {
            id: 12,
            text: "April.pdf",
            spriteCssClass: "pdf"
          }]
        }]
      }];
      var knobj = new kendo.data.HierarchicalDataSource({
        data: arrayObj
      });

      //setting heirarchial data to scope
      $scope.treeObj = knobj;

    };


    $scope.treeOptions = {
      checkboxes: {
        checkChildren: true
      },
      dataBound: function (e) {
        $scope.attachChangeEvent(e);

      }

    };
    $scope.attachChangeEvent = function (e) {

      var dataSource = e.sender.dataSource;
      // issue : change event is getting called multiple times for every click on checkbox
      dataSource.bind("change", function (e) {
        var selectedNodes = 0;

        var checkedNodes = [];

        $scope.checkedNodeIds(dataSource.view(), checkedNodes);


        for (var i = 0; i < checkedNodes.length; i++) {
          var nd = checkedNodes[i];
          if (nd.checked) {
            selectedNodes++;
          }
        }
        // check the console - this is called multiple times for one checkbox click
        console.log(selectedNodes);
      });
    };
    $scope.checkedNodeIds = function (nodes, checkedNodes) {

      for (var i = 0; i < nodes.length; i++) {
        var ndo = nodes[i];
        checkedNodes.push(ndo);

        if (ndo.hasChildren) {
          $scope.checkedNodeIds(ndo.children.view(), checkedNodes);
        }
      }
    };

    $scope.initTree();
  }
]);