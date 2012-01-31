package util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.impl.StackArray;
import util.impl.StackSingleLinked;

/**
 * 堆栈的应用
 * 
 * @author dolphin
 * 
 *         2012-1-31 下午5:23:22
 */
public class StackTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStackBaseConversion() { // 进制转换
		int toConvert = 2007;

		Stack s = new StackArray();
		int radix = 8;
		while (toConvert > 0) {
			s.push(toConvert % radix + "");
			toConvert = toConvert / radix;
		}
		while (!s.isEmpty()) {
			System.out.print(s.pop());
		}
	}

	@Test
	public void testBracketMatch() {
		assertTrue( bracketMatch( "[]" ) );
		assertTrue( bracketMatch( "{}" ) );
		assertTrue( bracketMatch( "()" ) );
		assertTrue( bracketMatch( "[{()}]" ) );

		assertFalse( bracketMatch( "[" ) );
		assertFalse( bracketMatch( "}}" ) );
		assertFalse( bracketMatch( ")()" ) );
		assertFalse( bracketMatch( "[({()}]" ) );

	}

	private boolean bracketMatch( String str ) {
		Stack s =  new StackSingleLinked();
		
		for ( int i = 0; i < str.length(); i++ ) {
			char c = str.charAt( i );
			
			switch (c) {
			case '{': 
			case '[':
			case '(': s.push( Integer.valueOf( c ) ); break;
			case '}':
				if ( !s.isEmpty() && ( ( Integer ) s.pop() ) == '{' )  {
					break;
				} else {
					return false;
				}
			case ']':
				if ( !s.isEmpty() && ( ( Integer ) s.pop() ) == '[' )  {
					break;
				} else {
					return false;
				}
			case ')':
				if ( !s.isEmpty() && ( ( Integer ) s.pop() ) == '(' )  {
					break;
				} else {
					return false;
				}
			}
		}
		
		return s.isEmpty();
	}

	

}
