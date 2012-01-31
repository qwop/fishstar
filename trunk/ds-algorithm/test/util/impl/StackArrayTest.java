package util.impl;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Stack;
import util.impl.StackArray;

public class StackArrayTest {
	Stack stack;

	@Before
	public void setUp() throws Exception {
		stack = new StackArray();
	}

	@After
	public void tearDown() throws Exception {
		stack = null;
	}

	private void push5elements() {
		for (int i = 0; i < 5; i++) {
			stack.push("" + i);
		}
	}

	@Test
	public void testStackArray() {

	}

	@Test
	public void testGetSize() {
		assertEquals("", 0, stack.getSize());
	}

	@Test
	public void testIsEmpty() {
		assertTrue("", stack.isEmpty());
	}

	@Test
	public void testPush() {
		push5elements();
		/**
		 * [4] [3] [2] [1] [0]
		 */
		assertEquals("", 5, stack.getSize());
		assertFalse("", stack.isEmpty());
	}

	@Test
	public void testPop() {
		push5elements();
		/**
		 * [4] [3] [2] [1] [0]
		 */
		assertEquals( "top of stack element", "4", stack.pop() );
		assertEquals( "top of stack element", "3", stack.pop() );
		assertEquals( "top of stack element", "2", stack.pop() );
		assertEquals( "top of stack element", "1", stack.pop() );
		
		assertEquals( "get size: ", 1, stack.getSize() );
	}

	@Test
	public void testPeek() {
		// is Empty stack
		
		try {
			stack.peek();
		} catch( Exception e ){
			System.err.println( e.getMessage() );
		}
		
		push5elements();
		
		assertEquals( "peek of the stack element: ", "4", stack.peek() );
	}

}
