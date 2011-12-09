var ua = navigator.userAgent.toLowerCase();
var ie,firefox,opera,safari;
if (ua.match(/msie ([\d.]+)/)) {
    ie = ua.match(/msie ([\d.]+)/)[1]
}
if (ua.match(/firefox\/([\d.]+)/)) {
    firefox = ua.match(/firefox\/([\d.]+)/)[1]
}
if (ua.match(/opera.([\d.]+)/)) {
    opera = ua.match(/opera.([\d.]+)/)[1]
}
if (ua.match(/version\/([\d.]+)/)) {
    safari = ua.match(/version\/([\d.]+)/)[1];
}


var _timeout; 
var _length = _contents.length;
var _count = 0;
var _closeable = false;
var _speed = 100;
var _mod = 50;
function _type(){ 
  if(_timeout!=undefined){ 
	clearTimeout(_timeout); 
  } 
  if (_closeable){
	_count = 0; _closeable = false;
	delete _timeout,_length,_count,_closeable,_speed,_mod,_contents;
	return;
  } 
  appendChar(); 
  _timeout = window.setTimeout(_type,_speed); 
} 
function appendChar(){ 
  if (_count == _length - 1){
	_closeable = true;
	return;
  }
  
  var c = _contents.charAt(_count++);
  if ( c == ' ' ) { // fix the nbsp's bug.
 	c = '&nbsp';
  }
  if(firefox){
		  var element = document.createElement('b'); 
		  if (_count % _mod == 0){
			  element.innerHTML= c + '<BR>'; 
		  } else {
		    element.innerHTML= c; 
		  }
		  document.getElementById('content').appendChild(element);        
    }
  else if ( ie ) {
	  var element = document.createElement('<b>'); 
	  if (_count % _mod == 0){
		  element.innerHTML= c + '<BR>'; 
	  } else {
	    element.innerHTML= c; 
	  }
	  document.getElementById('content').appendChild(element); 
  }   
} 