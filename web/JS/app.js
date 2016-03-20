var app=angular.module('app',[])
var app=angular.module('app', ['ngRoute'])
app.config(['$routeProvider', function($routeProvider) {
$routeProvider
.when('/', {
templateUrl: 'view1.php',
controller:'mycontroller'
})
.when('/booking', {
templateUrl: 'slotbooking.php',
controller:'mycontroller'
})
}]);

app.controller('mycontroller', function ($scope, $http) {

$scope.status=false;
$scope.main="";
$scope.photo=""
$scope.message="https://apius.faceplusplus.com/v2/detection/detect?url=http://i.forbesimg.com/media/lists/people/brad-pitt_416x416.jpg&api_secret=SVj8WHWd3cKsYZGOWcq9eG-uKFxQxg2w&api_key=237cf93c1ad81c8e7d7bc22b67b3c2fa&attribute=glass,pose,gender,age,race,smiling";
$scope.book=function(){
	$scope.status=true;
	$http.get($scope.message).
    success(function(data, status, headers, config) {
         $scope.value=data;
      	 $scope.vat=$scope.value.face[0].face_id;
      	 $scope.main=$scope.vat;
         console.log($scope.main);
            }).
    error(function(data, status, headers, config) {
     console.log("error");
    });
}
$scope.compare=function(){
	$scope.status=true;
	$http.get("https://apius.faceplusplus.com/v2/recognition/compare?api_secret=SVj8WHWd3cKsYZGOWcq9eG-uKFxQxg2w&api_key=237cf93c1ad81c8e7d7bc22b67b3c2fa&face_id2=72de5307afdf7f54ec1b262f5523aae0&face_id1="+$scope.main
).
    success(function(data, status, headers, config) {
       $scope.compareddata=data;
        
            }).
    error(function(data, status, headers, config) {
     console.log("error");
    });
}
console.log($scope.main);

});