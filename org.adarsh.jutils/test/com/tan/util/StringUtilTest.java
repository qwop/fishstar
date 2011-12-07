package com.tan.util;

import junit.framework.TestCase;

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
				"BeanDataHandler handler = getHandler();//\r\n handler.release();",
				"BeanDataHandler handler = getHandler(); \r\n //handler.release          (            );", // wrong code 1
				"BeanDataHandler handler = getHandler(); /*\r\n handler.release          (            );*/", // wrong code 2
			};
		
		
		int i = 1; /*
		
		assertTrue( true, true );
		*/
		
//		System.out.println( value.matches( "^.+//\\s*\\w+\\.release\\(\\).+$" ) );
		for ( final String code : codes ) {
			if ( !StringUtil.isRightReleaseCode( code ) ) {
				System.out.println( "wrong : " + code);
			}
		}
		
	}

	/**
	 * Run the boolean isEmpty(String) method test
	 */
	public void testIsEmpty() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String v = null;
		boolean result = StringUtil.isEmpty(v);
		assertTrue(false);
	}

	/**
	 * Run the void append(StringBuffer, Object[]) method test
	 */
	public void testAppend() {
		fail("Newly generated method - fix or disable");
		// add test code here
		StringBuffer b = null;
		Object[] args = null;
		StringUtil.append(b, args);
		assertTrue(false);
	}

	/**
	 * Run the boolean isNotNull(String) method test
	 */
	public void testIsNotNull() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String v = null;
		boolean result = StringUtil.isNotNull(v);
		assertTrue(false);
	}

	/**
	 * Run the boolean isNotEmpty(String) method test
	 */
	public void testIsNotEmpty() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String v = null;
		boolean result = StringUtil.isNotEmpty(v);
		assertTrue(false);
	}

	/**
	 * Run the boolean isNumber(String) method test
	 */
	public void testIsNumber() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String v = null;
		boolean result = StringUtil.isNumber(v);
		assertTrue(false);
	}

	/**
	 * Run the boolean isWhitespace(String) method test
	 */
	public void testIsWhitespace() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String v = null;
		boolean result = StringUtil.isWhitespace(v);
		assertTrue(false);
	}

	/**
	 * Run the String filter(String, char, char) method test
	 */
	public void testFilter() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String value = null;
		char oldChar = 0;
		char newChar = 0;
		String result = StringUtil.filter(value, oldChar, newChar);
		assertTrue(false);
	}

	/**
	 * Run the String filter0(String, char, char) method test
	 */
	public void testFilter0() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String value = null;
		char oldChar = 0;
		char newChar = 0;
		String result = StringUtil.filter0(value, oldChar, newChar);
		assertTrue(false);
	}

	/**
	 * Run the String getString(String, String) method test
	 */
	public void testGetString() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String basename = null;
		String key = null;
		String result = StringUtil.getString(basename, key);
		assertTrue(false);
	}

	/**
	 * Run the Object getInstanceSentence(String) method test
	 */
	public void testGetInstanceSentence() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String owner = null;
		Object result = StringUtil.getInstanceSentence(owner);
		assertTrue(false);
	}

	/**
	 * Run the String getComment(String, String, boolean) method test
	 */
	public void testGetComment() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String fieldName = null;
		String string = null;
		boolean isJavaDoc = false;
		String result = StringUtil.getComment(fieldName, string, isJavaDoc);
		assertTrue(false);
	}



	/**
	 * Run the String replace(String, String, String) method test
	 */
	public void testReplace() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String value = null;
		String replaceMent = null;
		String content = null;
		String result = StringUtil.replace(value, replaceMent, content);
		assertTrue(false);
	}

	/**
	 * Run the String getDummyField(String, String[]) method test
	 */
	public void testGetDummyField() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String typeSignature = null;
		String[] strings = null;
		String result = StringUtil.getDummyField(typeSignature, strings);
		assertTrue(false);
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