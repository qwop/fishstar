package util.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.impl.ListSingleLinked;

public class ListSingleLinkedTest {
	private ListSingleLinked list;

	@Before
	public void setUp() throws Exception {
		list = new ListSingleLinked();
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testSize() {
		assertEquals(0, list.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
	}

	@Test
	public void testContains() {
		assertFalse(list.contains("fuck"));
	}

	@Test
	public void testAddObject() {
		assertTrue(list.add("one"));
		assertTrue(list.add("two"));
		assertTrue(list.add("three"));
		assertTrue(list.add("four"));
		assertTrue(list.add("five"));

		assertEquals(5, list.size());
	}

	@Test
	public void testRemoveObject() {
		assertTrue(list.add("one"));
		assertTrue(list.add("two"));
		assertTrue(list.add("three"));
		assertTrue(list.add("four"));
		assertTrue(list.add("five"));

		assertTrue(list.remove("one"));
		assertEquals(4, list.size());

		assertFalse(list.remove("fuck"));
		assertEquals(4, list.size());
	}

	@Test
	public void testGet() {
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("five");

		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		assertEquals("four", list.get(3));
		assertEquals("five", list.get(4));

		/*
		 * int size = list.size();
		 * 
		 * for ( int i= 0; i < size; i++ ) { System.out.println( list.get( i )
		 * ); }
		 */

		try {
			list.get(-1);
		} catch (Exception e) {

		}
	}

	@Test
	public void testSet() {
		list.set(0, "one");
		list.set(1, "fuck1");
		list.set(1, "fuck2");
		list.set(1, "fuck3");
		assertEquals(4, list.size());

		assertEquals("fuck3", list.get(1));
		assertEquals("fuck2", list.get(2));
		assertEquals("fuck1", list.get(3));

		/*
		 * for (int i = 0; i < list.size(); i++) { System.out.println(i + "->" +
		 * list.get(i)); }
		 */
	}

	@Test
	public void testIndexOf() {
		list.set(0, "one");
		list.set(1, "fuck1");
		list.set(1, "fuck2");
		list.set(1, "fuck3");

		assertEquals(1, list.indexOf("fuck3"));
		assertEquals(2, list.indexOf("fuck2"));
		assertEquals(3, list.indexOf("fuck1"));
	}
}
