package com.tan.util;

import static com.tan.util.StringUtil.*;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * The class <code>StringUtilTest</code> contains tests for the class {@link
 * <code>StringUtil</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 11-12-7 �*1:57
 *
 * @author dolphin
 *
 * @version $Revision$
 */
public class StringUtilTest extends TestCase {

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public StringUtilTest(String name) {
		super(name);
	}

	/**
	 * Run the boolean isRightReleaseCode(String) method test
	 */
	public void testIsRightReleaseCode() {
		final String[] codes = 
			{
					"BeanDataHandler handler = getHandler();//\n " 
				+	"handler.release();",
				"BeanDataHandler handler = getHandler(); \r\n" +
				" //handler.release          (            );", // wrong code 1
				
				
				"BeanDataHandler handler = getHandler(); /*\r\n " +
				"handler.release          (            );*/", // wrong code 2
			};
		
		
		int i = 1; /*
		
		assertTrue( true, true );
		*/
		
//		System.out.println( value.matches( "^.+//\\s*\\w+\\.release\\(\\).+$" ) );
		for ( final String code : codes ) {
			if ( !StringUtil.isRightReleaseCode( code ) ) {
				System.err.println( "wrong : " + code);
			}
		}
		
	}

	/**
	 * Run the boolean isEmpty(String) method test
	 */
	public void testIsEmpty() {
		
		// add test code here
		String v = null;
		boolean result = StringUtil.isEmpty(v);
		assertTrue( result );
	}

	/**
	 * Run the void append(StringBuffer, Object[]) method test
	 */
	public void testAppend() {
		
		// add test code here
		StringBuffer b = new StringBuffer();
		
		StringUtil.append(b, "one", "two");
		assertEquals( "onetwo", b.toString() ) ;
	}

	/**
	 * Run the boolean isNotNull(String) method test
	 */
	public void testIsNotNull() {
		assertTrue( StringUtil.isNotNull( "fuck" ) );
		assertFalse( StringUtil.isNotNull( "null" ) );
	}

	/**
	 * Run the boolean isNotEmpty(String) method test
	 */
	public void testIsNotEmpty() {
		
		// add test code here

		assertTrue( StringUtil.isNotEmpty( "fuck" ) );
		assertTrue( StringUtil.isNotEmpty( "null" ) );
		assertFalse( StringUtil.isNotEmpty( " " ) );
		assertFalse( StringUtil.isNotEmpty( "" ) );
		assertFalse( StringUtil.isNotEmpty( null ) );
	}

	/**
	 * Run the boolean isNumber(String) method test
	 */
	public void testIsNumber() {
		assertTrue(StringUtil.isNumber("12"));
	}

	/**
	 * Run the boolean isWhitespace(String) method test
	 */
	public void testIsWhitespace() {

		// add test code here
		assertTrue(StringUtil.isWhitespace("    "));
	}

	/**
	 * Run the String filter(String, char, char) method test
	 */
	public void testFilter() {
		
		// add test code here
		String value = "one open obtain";
		char oldChar = 'o';
		char newChar = 'f';
		String result = StringUtil.filter(value, oldChar, newChar);
		assertEquals( "fne fpen fbtain", result );
	}

	/**
	 * Run the String filter0(String, char, char) method test
	 */
	public void testFilter0() {
		
		// add test code here
		String value = "one open obtain";
		char oldChar = 'o';
		char newChar = 'f';
		String result = StringUtil.filter0(value, oldChar, newChar);
		assertEquals( "fne fpen fbtain", result );
	}

	/**
	 * Run the String getString(String, String) method test
	 */
	public void testGetString() {
		
	
	}

	/**
	 * Run the Object getInstanceSentence(String) method test
	 */
	public void testGetInstanceSentence() {
	
	}

	/**
	 * Run the String getComment(String, String, boolean) method test
	 */
	public void testGetComment() {
		/****
		 * fuck
		 * @param args
		 * @throws Exception
		 */
		
		
		
			 System.out.println(getComment("none",
			 "//common comment 3. public static void main", false));
			 System.out.println(getComment("none",
			 "//java field comment 4. \r\n private float height;", false));
			 System.out.println(getComment("none",
			 "//java field comment 5. \n private float height;", false));
			 System.out.println(getComment("none",
			 " \n private float height; //java field comment 6.;", false));
			 System.out.println(getComment("none",
			 "// prefix comment. \n private Field field; // suffix comment 7.;",
			 false));
			 
			 System.out.println(getComment("none",
					 " \r\n \n private float height; //java comment 8.;", false));
			 
			 
			 System.out.println(getComment("height",
					 " \r\n \n private float height; // \"height\".", false));
			 
			 
			 System.out.println(getComment("height",
			 " \r\n \n private float height; // height.", false));
			 
			 
			 System.out.println(getComment("none", " ", true));
			 
			 
			 
			 System.out.println(getComment("",

	"/****\n" +
	"\t * fuck\n" + 
	"\t * @param args\n" + 
	"\t * @throws Exception\n" + 
	"\t */"


					 , true));
			
		}


	public void testdelLastSymbol() {
		assertEquals( "", delLastSymbol( "" ) , "" );
		assertEquals( "", delLastSymbol( "1" ) , "1" );
		assertEquals( "", delLastSymbol( "1，" ) , "1" );
		assertEquals( "", delLastSymbol( "1、" ) , "1" );
		assertEquals( "", delLastSymbol( "1。" ) , "1" );
		assertEquals( "", delLastSymbol( "1." ) , "1" );
		assertEquals( "", delLastSymbol( "1." ) , "1" );
		assertEquals( "", delLastSymbol( "1." ) , "1" );
		assertEquals( "", delLastSymbol( "1." ) , "1" );
		assertEquals( "", delLastSymbol( "1." ) , "1" );
		assertEquals( "", delLastSymbol( "1." ) , "1" );
		assertEquals( "", delLastSymbol( "1." ) , "1" );
		
		
	}
	
	/**
	 * Run the String replace(String, String, String) method test
	 */
	public void testReplace() {
		
	
	}

	/**
	 * Run the String getDummyField(String, String[]) method test
	 */
	public void testGetDummyField() {
	/*	// add test code here
		String typeSignature = null;
		String[] strings = null;
		String result = StringUtil.getDummyField(typeSignature, strings);
		assertTrue(false);*/
	}
	
	
	@Test
	public void testTrimQuot() {
		assertEquals( "两边都有双引号", "fuck", StringUtil.trimQuot( "\"fuck\"" ) );
		assertEquals( "", "fuck", StringUtil.trimQuot( "\'fuck\"" ) );
		assertEquals( "", "fuck", StringUtil.trimQuot( "fuck\"" ) );
		assertEquals( "", "fuck", StringUtil.trimQuot( "\"fuck" ) );
		assertEquals( "", "fuck", StringUtil.trimQuot( "\'fuck" ) );
		assertEquals( "", "fuck", StringUtil.trimQuot( "\'fuck\'" ) );
	}
	
	@Test
	public void testfilterJavaDoc() {
		String value =  "查看应用详情(正式表)\r\n      @return ApplicationInfo \r\n @param strappid strappid \r\n " +
				"@param handler handler \r\n      @throws ThirdgsException ThirdgsException	 " ;
		assertEquals( "过滤javadoc内容", "查看应用详情(正式表)", StringUtil.filterJavaDoc( "查看应用详情(正式表)\r\n      @return ApplicationInfo \r\n @param strappid strappid \r\n " +
				"@param handler handler \r\n      @throws ThirdgsException ThirdgsException	 " ) );
		
		System.out.println( value.replaceAll( "@([a-zA-Z]+)([a-zA-Z\\s])+", "" ).trim() );
	}
}

/*$CPS$ This comment was generated by CodePro. Do not edit it.
 * patternId = com.instantiations.assist.eclipse.pattern.testCasePattern
 * strategyId = com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames = 
 * assertTrue = false
 * callTestMethod = true
 * createMain = false
 * createSetUp = false
 * createTearDown = false
 * createTestFixture = false
 * createTestStubs = true
 * methods = isRightReleaseCode(QString;)
 * package = com.tan.util
 * package.sourceFolder = org.adarsh.jutils/test
 * superclassType = junit.framework.TestCase
 * testCase = StringUtilTest
 * testClassType = com.tan.util.StringUtil
 */