
var app=angular.module('myApp', ['ngRoute'])
app.config(['$routeProvider', function($routeProvider) {
$routeProvider

.when('/', {
templateUrl: 'index.html',
controller:'myController'
})
.when('/filterpage', {
templateUrl: 'index.html',
controller:'myController'
})
.when('/robo', {
templateUrl: 'index.html',
controller:'myController'
})

}]);

app.controller('myController',function($scope){

})