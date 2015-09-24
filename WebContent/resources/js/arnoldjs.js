/**
 * 
 */
$(document).ready(function() {
	
//	initial
//	alert("Welcome");
//	$('#content').load("homepage.jsp");

//	subsequent menu clicks
	$('ul#headerbar li a').click(function() {
//		var page = $(this).attr('href');
		$('#content').load("profile.jsp");
	});
});