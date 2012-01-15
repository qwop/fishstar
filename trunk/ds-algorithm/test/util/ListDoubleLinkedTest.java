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
		assertEquals( "�յ�list", 0 , list.size() );
		
		list.add( "1" );
		list.add( "2" );
		list.add( "3" );
		list.add( "4" );
		list.add( "5" );
		
		assertEquals( "�����Ԫ�ص�list", 5 , list.size() );
	}

	@Test
	public void testIsEmpty() {
		assertTrue( "�յ�list", list.isEmpty() );
		
		list.add( "1" );
		list.add( "2" );
		list.add( "3" );
		list.add( "4" );
		list.add( "5" );
		
		assertFalse( "�����Ԫ�ص�list", list.isEmpty() );
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
		assertFalse( "��Ԫ��ɾ����", list.remove( null ) );
		assertFalse( "��Ԫ��ɾ������Ϊ fuck", list.remove( "fuck" ) );
		assertEquals( "δ���Ԫ�ص�list", 0 , list.size() );
		
		list.add( "1" );
		list.add( "2" );
		list.add( "3" );
		
		list.add( null );
		
		assertEquals( "���Ԫ�ص�list", 4 , list.size() );
		assertTrue( "ɾ������Ϊ1",  list.remove( "1" ) );
		assertTrue( "��Ԫ��ɾ����", list.remove( null ) );
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
		
		
		assertEquals( "��һ��Ԫ��", "one", list.get( 0 ) );
		assertEquals( "�ڶ���Ԫ��", "two", list.get( 1 ) );
		assertEquals( "������Ԫ��", "three", list.get( 2 ) );
		assertEquals( "���ĸ�Ԫ��", "four", list.get( 3 ) );
		assertEquals( "�����Ԫ��", "five", list.get( 4 ) );
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
		
		assertEquals( "��һ��Ԫ��", "zero", list.get( 0 ) );
		assertEquals( "�ڶ���Ԫ��", "one", list.get( 1 ) );
		assertEquals( "������Ԫ��", "two", list.get( 2 ) );
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
