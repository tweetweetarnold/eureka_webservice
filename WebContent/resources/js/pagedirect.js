/**
 * 
 */

$(document).ready(function(){
	
//	initial
//	alert("welcome");
	$('#content').load("homepage.jsp");
	
//	header bar clicks
	$('ul#headerbar li a').click(function() {
		var page = $(this).attr('title');
		$('#content').load(page + ".jsp");
	});
	
});