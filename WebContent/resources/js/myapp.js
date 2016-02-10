/**
 * 
 */

var app = angular.module('myApp', [ '$http' ]);

app.config([ '$locationProvider', function($locationProvider) {
	$locationProvider.html5Mode({
		enabled : true,
		requireBase : false
	});
} ]);

