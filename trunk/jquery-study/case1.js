//// ����Ͷ���Ĳ���

test("Jquery�ĵ�һ������", function() {

	var actual = "tanyuanji";	
	var expected = "tanyuanji";

	ok( expected == actual, "����ͨ��" );
});



test( "jquery������ʽ�Ĳ���", function() {
	//����  ���һ���ַ������Ƿ��зǿհ��ַ�
	var rnotwhite = /\S/;
	var right = rnotwhite.test( "not white" );
	ok( right , "�ַ����к��зǿհ��ַ�����ͨ��!" );

	// Match a standalone tag
	//ƥ��һ�������ı�ǩ  <div>   <div></div>
	//<div>adf</div>   <div></span>  �Ȳ���ͨ��
	var rsingleTag = /^<(\w+)\s*\/?>(?:<\/\1>)?$/;
	right = 
		rsingleTag.test( "<tag>" ) && 
		rsingleTag.test( "<tag/>" ) && 
		rsingleTag.test( "<tag></tag>" ) && 
		rsingleTag.test( "<tag			\t/>" ) && 
		rsingleTag.test( "<tag ></tag>" );

	ok( right , "ƥ��һ������html��ǩ����ͨ��!" );


	// JSON RegExp
	// json ������ʽ
	var rvalidchars = /^[\],:{}\s]*$/,
	rvalidescape = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,
	rvalidtokens = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
	rvalidbraces = /(?:^|:|,)(?:\s*\[)+/g;
	
	right = rvalidchars.test( "{  ,:\t] }" );
	ok( right , "validchars ����ͨ��!" );

	right = rvalidescape.test( "\\uaaaa" );
	ok( right , "rvalidescape ����ͨ��!" );

	right = rvalidtokens.test( "-122121.12ee+1" );
	ok( right , "rvalidtokens ����ͨ��!" );

	right = rvalidbraces.test( "|:,|:,|:,|:,|:,|:,|:,              [" );
	ok( right , "rvalidbraces ����ͨ��!" );


	var quickExpr = /^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/;

	right = quickExpr.test( "#test" ) 
	&& quickExpr.test( "#fuck" );

	ok( right, "ƥ��id����ͨ��" );
});



test( "JQUERY" , function() {
	var domEle = $( "#qunit-tests" );
	equal( 1, domEle.length, "��ȡ id : qunit-tests dom Ԫ��" );
	

	//var domEle1 = $( "ol" );
	//equal( domEle, domEle1.get( 2 )  );


	$( "<div><p>Hello</p></div>" ).appendTo( "body" );
});



test( "isPlainObject ����������" , function() {
	var a = {}, a1 = new Object,
		c = / /, c1 = new RegExp( "test" );

	ok( $.isPlainObject( {} ), "{}����������ͨ��" );
	ok( $.isPlainObject( a1 ), " Object ����������ͨ��" );
	ok( $.isPlainObject( {name: '����', age: 20 } ), "{name: '����', age: 20 }����������ͨ��" );
	ok( $.isPlainObject( {name: '����', age: 20, fn	: function() {} } ), "{name: '����', age: 20, fn	: function() {} }����������ͨ��" );
	

});


test( " test jquery's Map function " , function() {
	var actual = $.map( [0,1,2], function( n ) {
		return n + 4;
	});

	var expected = '4,5,6';
	
	equal( expected , actual , "Map ����1" );

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

	equal( expected, actual, "Map ���� 2" );
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