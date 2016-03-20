var app=angular.module('app',[])
var app=angular.module('app', ['ngRoute'])
app.config(['$routeProvider', function($routeProvider) {
$routeProvider
.when('/', {
templateUrl: 'view1.php',
controller:'mycontroller'
})
.when('/technology', {
templateUrl: 'slotbooking.php',
controller:'mycontroller'
})
}]);

app.controller('mycontroller', function ($scope) {
console.log("hello")
});