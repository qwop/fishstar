test("Jquery的第一个测试", function() {

	var actual = "tanyuanji";	
	var expected = "tanyuanji";

	ok( expected == actual, "测试通过" );
});



test( "jquery正则表达式的测试", function() {
	//正则  检查一个字符串中是否含有非空白字符
	var rnotwhite = /\S/;
	var right = rnotwhite.test( "not white" );
	ok( right , "字符串中含有非空白字符测试通过!" );

	// Match a standalone tag
	//匹配一个单独的标签  <div>   <div></div>
	//<div>adf</div>   <div></span>  等不能通过
	var rsingleTag = /^<(\w+)\s*\/?>(?:<\/\1>)?$/;
	right = 
		rsingleTag.test( "<tag>" ) && 
		rsingleTag.test( "<tag/>" ) && 
		rsingleTag.test( "<tag></tag>" ) && 
		rsingleTag.test( "<tag			\t/>" ) && 
		rsingleTag.test( "<tag ></tag>" );

	ok( right , "匹配一个单独html标签测试通过!" );


	// JSON RegExp
	// json 正则表达式
	var rvalidchars = /^[\],:{}\s]*$/,
	rvalidescape = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,
	rvalidtokens = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
	rvalidbraces = /(?:^|:|,)(?:\s*\[)+/g;
	
	right = rvalidchars.test( "{  ,:\t] }" );
	ok( right , "validchars 测试通过!" );

	right = rvalidescape.test( "\\uaaaa" );
	ok( right , "rvalidescape 测试通过!" );

	right = rvalidtokens.test( "-122121.12ee+1" );
	ok( right , "rvalidtokens 测试通过!" );

	right = rvalidbraces.test( "|:,|:,|:,|:,|:,|:,|:,              [" );
	ok( right , "rvalidbraces 测试通过!" );
});



test( "JQUERY" , function() {
	var domEle = $( "#qunit-tests" );
	equal( 1, domEle.length, "获取 id : qunit-tests dom 元素" );
	

	//var domEle1 = $( "ol" );
	//equal( domEle, domEle1.get( 2 )  );


	$( "<div><p>Hello</p></div>" ).appendTo( "body" );
});