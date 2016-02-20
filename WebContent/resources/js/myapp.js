/**
 * 
 */

var app = angular.module('myApp', [ 'cgBusy' ]);

app.config([ '$locationProvider', function($locationProvider) {
	$locationProvider.html5Mode({
		enabled : true,
		requireBase : false
	});
} ]);
