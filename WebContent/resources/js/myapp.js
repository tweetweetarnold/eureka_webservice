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

app.factory('myMessages', function() {
	var savedData = {}

	// savedData.set = function(data){
	// savedData = data.success;
	// }
	//	
	// savedData.get = function(){
	// return savedData;
	// }

	savedData.setSuccess = function(data) {
		savedData.success = data;
	}
	savedData.setError = function(data) {
		savedData.error = data;
	}

	savedData.getSuccess = function() {
		return savedData.success;
	}
	savedData.getError = function() {
		return savedData.error;
	}

	return savedData;

});
