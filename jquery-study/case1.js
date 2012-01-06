//// 数组和对象的操作

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


	var quickExpr = /^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/;

	right = quickExpr.test( "#test" ) 
	&& quickExpr.test( "#fuck" );

	ok( right, "匹配id测试通过" );
});



test( "JQUERY" , function() {
	var domEle = $( "#qunit-tests" );
	equal( 1, domEle.length, "获取 id : qunit-tests dom 元素" );
	

	//var domEle1 = $( "ol" );
	//equal( domEle, domEle1.get( 2 )  );


	$( "<div><p>Hello</p></div>" ).appendTo( "body" );
});



test( "isPlainObject 字面量测试" , function() {
	var a = {}, a1 = new Object,
		c = / /, c1 = new RegExp( "test" );

	ok( $.isPlainObject( {} ), "{}字面量测试通过" );
	ok( $.isPlainObject( a1 ), " Object 字面量测试通过" );
	ok( $.isPlainObject( {name: '张三', age: 20 } ), "{name: '张三', age: 20 }字面量测试通过" );
	ok( $.isPlainObject( {name: '张三', age: 20, fn	: function() {} } ), "{name: '张三', age: 20, fn	: function() {} }字面量测试通过" );
	

});


test( " test jquery's Map function " , function() {
	var actual = $.map( [0,1,2], function( n ) {
		return n + 4;
	});

	var expected = '4,5,6';
	
	equal( expected , actual , "Map 测试1" );

	var plainObject = {
		num1: 11,
		num2: 12,
		num3: 13,
		num4: 14,
		num5: 15
	};

	actual = $.map( plainObject, function( v ) {
		return v + 1;
	} );

	expected = "12,13,14,15,16";

	equal( expected, actual, "Map 测试 2" );
});


test( " jquery's proxy function test " , function() {
	var obj = {
		name	:	"John",
		test	:	function() {
			alert( this.name );
			$( "#test" ).unbind( "click", obj.test );
			$( "#test" ).html( "FUCK YOU!" );
		}
	};


	$( "#test" ).click( $.proxy( obj.test, obj ) );

	// $( "#test" ).click( obj.test );
});