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
});



test( "JQUERY" , function() {
	var domEle = $( "#qunit-tests" );
	equal( 1, domEle.length, "��ȡ id : qunit-tests dom Ԫ��" );
	

	//var domEle1 = $( "ol" );
	//equal( domEle, domEle1.get( 2 )  );


	$( "<div><p>Hello</p></div>" ).appendTo( "body" );
});