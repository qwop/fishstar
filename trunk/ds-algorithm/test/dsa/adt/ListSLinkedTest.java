package dsa.adt;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListSLinkedTest {
	
	List list;
	
	@Before
	public void setUp() throws Exception {
		list = new ListSLinked();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSize() {
		assertEquals( 0 , list.getSize() );
		
		list.insert( "one" ) ;
		list.insert( "two" ) ;
		list.insert( "three" ) ;
		list.insert( "four" ) ;
		list.insert( "five" ) ;
		
		assertEquals( 5, list.getSize() );
	}

	@Test
	public void testIsEmpty() {
		assertTrue( list.isEmpty() );
		
		list.insert( "one" ) ;
		
		assertFalse( list.isEmpty() ) ;
	}

	@Test
	public void testContains() {
		assertFalse( list.contains( "fuck" ) );
	}

	@Test
	public void testIndexOf() {
		assertEquals( -1, list.indexOf( "one" ) );
		
		list.insert( "one" );

		assertEquals( 0, list.indexOf( "one" ) );
	}

	@Test
	public void testInsert() {
		list.insert( "one" );
	}

	@Test
	public void testInsertBefore() {
		
	}

	@Test
	public void testInsertAfter() {
		
	}

	@Test
	public void testRemoveInt() {
		
	}

	@Test
	public void testRemoveObject() {
		
	}

	@Test
	public void testReplace() {
		
	}

	@Test
	public void testGet() {
		
	}

}
