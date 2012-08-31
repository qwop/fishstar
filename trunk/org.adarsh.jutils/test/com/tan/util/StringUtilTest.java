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
	
	@Test
	public void testJars() {
			String str = 
			"D:\\Eclipses\\MyEclipse\\Common\\binary\\com.sun.java.jdk.win32.x86_1.6.0.013\n" +
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.core.runtime_3.8.0.v20120521-2346.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.osgi_3.8.0.v20120529-1548.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.equinox.common_3.6.100.v20120522-1841.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.core.jobs_3.5.200.v20120521-2346.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.core.runtime.compatibility.registry_3.5.100.v20120521-2346\\runtime_registry_compatibility.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.equinox.registry_3.5.200.v20120522-1841.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.equinox.preferences_3.5.0.v20120522-1841.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.core.contenttype_3.4.200.v20120523-2004.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.equinox.app_1.3.100.v20120522-1841.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.core.resources_3.8.0.v20120522-2034.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.core.filebuffers_3.5.200.v20120523-1310.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.jdt_3.8.0.v201206081400.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.jdt.ui_3.8.0.v20120524-1551.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.jdt.core_3.8.1.v20120531-0637.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.jdt.compiler.apt_1.0.500.v20120522-1651.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.jdt.compiler.tool_1.0.101.v20120522-1651.jar\n" + 
			"D:\\Eclipse-Plugins\\wb_v1.0.0_updatesite_for_eclipse3.7\\plugins\\org.eclipse.wb.jdt.fragment_1.0.0.r37x201106081531.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.jface.text_3.8.0.v20120531-0600.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.text_3.5.200.v20120523-1310.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.ui_3.103.0.v20120521-2329.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.swt_3.100.0.v4233d.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.swt.win32.win32.x86_3.100.0.v4233d.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.jface_3.8.0.v20120521-2329.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.core.commands_3.6.1.v20120521-2329.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.ui.workbench_3.103.0.v20120530-1824.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.e4.ui.workbench3_0.12.0.v20120521-2329.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.ui.ide_3.8.0.v20120521-2329.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.ui.workbench.texteditor_3.8.0.v20120523-1310.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.core.filesystem_1.3.200.v20120522-2012.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.jdt.launching_3.6.100.v20120523-1953.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.eclipse.ui.views_3.6.100.v20120521-2329.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.junit_4.10.0.v4_10_0_v20120426-0900\\junit.jar\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\log4j-1.2.15.jar\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\asm-all-3.2.jar\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\commons-logging-1.1.1.jar\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\dependency-0.4.jar\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\easymock-3.0.jar\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\jar_list.txt\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\jdeb-0.7.jar\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\log4j-1.2.15.jar\n" + 
			"E:\\Projects\\fishstar\\apache\\lib\\servlet-api.jar\n" + 
			"D:\\Eclipses\\eclipse-jee-juno-win32\\eclipse\\plugins\\org.hamcrest.core_1.1.0.v20090501071000.jar";
				String[] paths = str.split( "\n" );
				
				
				for ( final String path : paths ) {
					System.out.println( StringUtil.toAnt( path ) );
				}
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