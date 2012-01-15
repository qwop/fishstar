package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.impl.ListDoubleLinked;

public class ListDoubleLinkedTest {
	
	List list;
	
	@Before
	public void setUp() throws Exception {
		list = new ListDoubleLinked();
		
	}

	@After
	public void tearDown() throws Exception {
		
		list = null;
	}

	@Test
	public void testSize() {
		assertEquals( "空的list", 0 , list.size() );
		
		list.add( "1" );
		list.add( "2" );
		list.add( "3" );
		list.add( "4" );
		list.add( "5" );
		
		assertEquals( "添加了元素的list", 5 , list.size() );
	}

	@Test
	public void testIsEmpty() {
		assertTrue( "空的list", list.isEmpty() );
		
		list.add( "1" );
		list.add( "2" );
		list.add( "3" );
		list.add( "4" );
		list.add( "5" );
		
		assertFalse( "添加了元素的list", list.isEmpty() );
	}

	@Test
	public void testContains() {
		
	}

	@Test
	public void testIterator() {
		
	}

	@Test
	public void testToArray() {
		
	}

	@Test
	public void testToArrayObjectArray() {
		
	}

	@Test
	public void testAddObject() {
		
	}

	@Test
	public void testRemoveObject() {
		assertFalse( "空元素删除空", list.remove( null ) );
		assertFalse( "空元素删除数据为 fuck", list.remove( "fuck" ) );
		assertEquals( "未添加元素的list", 0 , list.size() );
		
		list.add( "1" );
		list.add( "2" );
		list.add( "3" );
		
		list.add( null );
		
		assertEquals( "添加元素的list", 4 , list.size() );
		assertTrue( "删除数据为1",  list.remove( "1" ) );
		assertTrue( "空元素删除空", list.remove( null ) );
	}

	@Test
	public void testContainsAll() {
		
	}

	@Test
	public void testAddAllCollection() {
		
	}

	@Test
	public void testAddAllIntCollection() {
		
	}

	@Test
	public void testRemoveAll() {
		
	}

	@Test
	public void testRetainAll() {
		
	}

	@Test
	public void testClear() {
		list.add( "one" ) ;
		list.add( "two" ) ;
		list.add( 1 ) ;
		list.add( 2 ) ;
		
		list.clear();
		
		assertEquals( 0 , list.size()  );
	}

	@Test
	public void testGet() {
		list.add( "one" ) ;
		list.add( "two" ) ;
		list.add( "three" ) ;
		list.add( "four" ) ;
		list.add( "five" ) ;
		
		
		assertEquals( "第一个元素", "one", list.get( 0 ) );
		assertEquals( "第二个元素", "two", list.get( 1 ) );
		assertEquals( "第三个元素", "three", list.get( 2 ) );
		assertEquals( "第四个元素", "four", list.get( 3 ) );
		assertEquals( "第五个元素", "five", list.get( 4 ) );
	}

	@Test
	public void testSet() {
		
		try { 
			// can not set value in the empty list.
			list.set( 0, "one" );
		} catch( Exception e ) {
			
		}
		
		list.add( "one" ) ;
		list.add( "two" ) ;
		list.add( "three" ) ;
		list.add( "four" ) ;
		list.add( "five" ) ;
		
		// when the linked list had values then set value .
		list.set( 0 , "zero" );
		list.set( 1 , "one" );
		list.set( 2 , "two" );
		list.set( 3 , "three" );
		list.set( 4 , "four" );
		
		assertEquals( "第一个元素", "zero", list.get( 0 ) );
		assertEquals( "第二个元素", "one", list.get( 1 ) );
		assertEquals( "第三个元素", "two", list.get( 2 ) );
	}

	@Test
	public void testAddIntObject() {
	}

	@Test
	public void testRemoveInt() {
		
	}

	@Test
	public void testIndexOf() {
		list.add( "one" ) ;
		list.add( "two" ) ;
		list.add( "three" ) ;
		list.add( "four" ) ;
		list.add( "five" ) ;
		
		assertEquals( "", 3, list.indexOf( "four" ) );
	}

	@Test
	public void testLastIndexOf() {
		
	}

	@Test
	public void testListIterator() {
		
	}

	@Test
	public void testListIteratorInt() {
		
	}

	@Test
	public void testSubList() {
		
	}

}
