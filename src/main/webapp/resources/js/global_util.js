function ajaxCall(http, url, data, sucessCallback, errorCallback) {
  http(
      {
        method: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        data: data
      }).success(function (data, status, headers, config) {
    sucessCallback(data);
  }).error(function (data, status, headers, config) {
    if (errorCallback) {
      errorCallback(data);
    } else {
      alert(data);
    }
  });
}

function ajaxCallNotJsonContentType(http, url, data, sucessCallback, errorCallback) {
  http(
      {
        method: "POST",
        url: url,
        data: data
      }).success(function (data, status, headers, config) {
    sucessCallback(data);
  }).error(function (data, status, headers, config) {
    if (errorCallback)
      errorCallback(data);
    else {
      alert(data.resposeText);
    }
  });
}