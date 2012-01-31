package util.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Queue;

public class QueueSingleLinkedTest {
	private Queue queue;
	
	@Before
	public void setUp() throws Exception {
		queue = new QueueSingleLinked();
	}

	@After
	public void tearDown() throws Exception {
		queue = null;
	}

	@Test
	public void testSize() {
		
		assertEquals( "δ����Ԫ��֮ǰ", 0 , queue.size() );
		
		queue.enqueue( 1 );
		queue.enqueue( 2 );
		queue.enqueue( 3 );
		
		assertEquals( "����������Ԫ��", 3 , queue.size() );
	}

	@Test
	public void testIsEmpty() {
		assertTrue( "δ����Ԫ��֮ǰ",  queue.isEmpty() );
		
		queue.enqueue( 1 );
		
		assertFalse( "δ����Ԫ��֮ǰ",  queue.isEmpty() );
	}

	@Test
	public void testEnqueue() {
		queue.enqueue( 1 );
		queue.enqueue( "fuck" );
		
		Object obj = queue.peek();
		assertEquals( "���׵�Ԫ��", new Integer( 1 ), obj );
		
		queue.dequeue();
		
		
		queue.enqueue( "you" );
		queue.dequeue();
		
		queue.enqueue( "fuck" );
		queue.dequeue();
		
		
		obj = queue.peek();
		
		assertEquals( "���׵�Ԫ��", "fuck", obj );
	}

	@Test
	public void testDequeue() {
		for ( int i = 0; i < 100; i++ ) {
			queue.enqueue( i );
		}
		
		assertEquals( "���׵�Ԫ��", new Integer( 0 ), queue.dequeue() );
		assertEquals( "���׵�Ԫ��", new Integer( 1 ), queue.dequeue() );
		assertEquals( "���׵�Ԫ��", new Integer( 2 ), queue.dequeue() );
		assertEquals( "���׵�Ԫ��", new Integer( 3 ), queue.dequeue() );
	}

	@Test
	public void testPeek() {
		for ( int i = 0; i < 10; i++ ) {
			queue.enqueue( i );
		}
		
		
		assertEquals( "���׵�Ԫ��", new Integer( 0 ), queue.peek() );
		assertEquals( "���׵�Ԫ��", new Integer( 0 ), queue.peek() );
		assertEquals( "���׵�Ԫ��", new Integer( 0 ), queue.peek() );
		assertEquals( "���׵�Ԫ��", new Integer( 0 ), queue.peek() );
		assertEquals( "���׵�Ԫ��", new Integer( 0 ), queue.peek() );
	}

}
