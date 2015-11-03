/**
 * 
 */

function onImageError(num) {
	var s1 = "image" + num.toString();
	document.getElementById(s1).src = "resources/img/img-error.jpg";
}
